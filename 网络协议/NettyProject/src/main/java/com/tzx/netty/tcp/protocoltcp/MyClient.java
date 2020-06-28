package com.tzx.netty.tcp.protocoltcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {

    public static void main(String[] args) throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        //客户端启动对象、
        //客户端使用的不是ServerBootstrap 而是Bootstrap
        try {
            Bootstrap bootstrap = new Bootstrap();

            //设置相关参数
            bootstrap.group(group)       //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类(反射)
                    .handler(new MyClientInitializer());
            System.out.println("客户端 is ok!!!!!!!!");

            //启动客户端去连接服务器端
            //关于ChannelFuture涉及到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 7000).sync();

            //给关闭通道进行监听 这是一个异步 并不是马上关闭
            channelFuture.channel().closeFuture().sync();

        }finally {

            group.shutdownGracefully();
        }
    }
}
