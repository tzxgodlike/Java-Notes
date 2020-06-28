package com.tzx.netty.websocket;

import com.tzx.netty.heartbeat.MyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))  //在bossGroup增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();

                            //因为是基于HTTP协议 适应HTTP的编解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写  添加chunkedWrite处理器
                            pipeline.addLast(new ChunkedWriteHandler());

                            /*
                            说明
                                1，HTTP在传输过程中是分段的 HttpObjectAggregator就是可以将多个段聚合起来
                                2. 这就是为什么当浏览器发送大量数据时 就会发出多次HTTP请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /*
                            说明
                            1.对于websocket 数据是以帧的形式传递
                            2. 可以看到websocket下面有6个子类
                            3. 浏览器请求时 ws://localhost:7000/xxx 表示请求的uri  xxx对应下面括号中的参数
                            4. WebSocketServerProtocolHandler将http协议升级为ws协议 保持长连接
                            5. 是通过101状态码升级为ws协议  uri需要对应
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            //自定义的handler 处理业务
                            pipeline.addLast(new MyTextWebSocketHandler());
                        }
                    });

            //启动服务器
            ChannelFuture sync = serverBootstrap.bind(7000).sync();
            sync.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
