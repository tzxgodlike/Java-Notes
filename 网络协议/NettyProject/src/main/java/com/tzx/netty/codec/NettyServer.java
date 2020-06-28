package com.tzx.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {


        //创建bossgroup和workergroup

        // 1.创建2个线程组 bossgroup和workergroup
        // 2.bossgroup只是处理连接请求 真正和客户端的业务处理 交给workergroup完成
        // 3.两个都是无限循环
        // 4.bossgroup和workergroup含有的子线程的个数 默认由cpu核数*2 jueding

        //调试发现 各自开了16个子线程  可以指定bossGroup 只开一个线程

        //如果有9个客户端连接 第9个会让8个线程中空闲的去完成

        //EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);


        try {
            //创建服务器端的启动对象 配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程来设置
            bootstrap.group(bossGroup, workerGroup)    //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //使用NioSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) //设置好线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象 (匿名对象)

                        //向管道设置一个handler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            //需要指定对哪种对象进行解码
                            socketChannel.pipeline().addLast("decoder",new ProtobufDecoder(StudentPOJO.Student
                                    .getDefaultInstance()));
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给我们得workergroup的eventLoop 对应的管道 设置处理器

            System.out.println(".....服务器 is ready...");

            //绑定一个端口并同步 生成一个ChannelFuture对象
            //启动服务器 (并绑定端口)
            ChannelFuture cf = bootstrap.bind(6668).sync();
            //sync方法 会让主线程等待cf异步方法执行完毕

            //给ChannelFuture注册监听器 监控关心的事件

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口6668成功");
                    }else {
                        System.out.println("监听6668失败");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            //异常跑出去后 把group优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
