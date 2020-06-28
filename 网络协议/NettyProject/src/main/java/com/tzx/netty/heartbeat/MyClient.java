package com.tzx.netty.heartbeat;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.util.concurrent.TimeUnit;

public class MyClient {

    /*
    读空闲时候 服务端关闭channel
    要实现重连 则在客户端的handler中的channelInactive方法 可以检测到断开连接
    在该方法中 重新连接服务端
     */
    //属性
    private final String host;
    private final int port;

    public MyClient(String host,int post) {
        this.host = host;
        this.port = post;
    }

    public void run() throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();

                            //加入一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //加入一个编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            //加入自己的业务处理器
                            pipeline.addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("开始重连....");
                                    new MyClient("127.0.0.1",7000).run();
                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();



            channelFuture.channel().closeFuture().sync();



        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new MyClient("127.0.0.1",7000).run();
    }
}
