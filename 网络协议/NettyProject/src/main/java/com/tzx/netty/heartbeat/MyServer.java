package com.tzx.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {
    public static void main(String[] args) throws InterruptedException {

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
                            //加入netty提供的IdleStateHandler

                            /*
                                1.IdleStateHandler是netty提供的处理空闲状态的处理器
                                2.readerIdleTime 表示有多长时间没有读 就会发送一个心跳检测包
                                3.writerIdleTime 表示有多长时间没有写操作  就会发送一个心跳检测包
                                4.allIdleTime     表示多长时间即没读也没写 就会发送一个心跳包

                                5.文档说明
                                    当有一段时间没有触发读、写、或读写 就会触发一个事件
                                6. 当IdleStateHandler触发后 就会传递给管道的下一个handler处理
                                通过回调下一个handler的userEventTiggered 在该方法中去处理IdleStateHandler
                                （读空闲 写空闲 读写空闲）
                             */
                            pipeline.addLast(new IdleStateHandler(3,5,7,
                                    TimeUnit.SECONDS));

                            //加入一个对空闲检测进一步处理的handler(自定义)
                            pipeline.addLast(new MyServerHandler());
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
