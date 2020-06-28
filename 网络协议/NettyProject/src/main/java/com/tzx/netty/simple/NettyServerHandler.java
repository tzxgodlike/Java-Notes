package com.tzx.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/*
    1.自定义一个handler  需要继承netty规定好的某个HandlerAdapter(规范)

 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据 (在这里可以读取客户端发送消息)
    /*
    1. ChannelHandlerContext 上下文对象 含有管道 通道  地址
    2. Object msg 就是客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


//        System.out.println("服务器读取线程 " +Thread.currentThread().getName());
//        System.out.println("server ctx=" + ctx);
//        System.out.println("看看channel和pipeline的关系");
//        Channel channel = ctx.channel();
//        ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向链表
//
//
//        //将msg转成一个btyebuf
//        //btyebuf是netty提供的 和nio中的ByteBuffer不一样 性能更高
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("客户端发送消息是："+buf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址是："+ctx.channel().remoteAddress());

        //比如这里有一个耗时的业务 所以需要异步执行  提交到该channel对应的NIOEventLoop的taskQueue中

//        Thread.sleep(10*1000);
//
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端 qaq2！",CharsetUtil.UTF_8));
//
//        System.out.println("go on ...");
        //调试发现服务端会阻塞在上面10s

        /*
        解决方案1. 用户自定义的普通任务
         */
        //打印发现NettyServerHandler和TaskQueue都是用的同一个线程
        System.out.println("NettyServerHandler的线程是"+Thread.currentThread().getName());

        ctx.channel().eventLoop().execute(()->{
            try {
                System.out.println("TaskQueue的线程是"+Thread.currentThread().getName());
                Thread.sleep(10*1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端 qaq2！",CharsetUtil.UTF_8));

            } catch (InterruptedException e) {
                System.out.println("发生异常" +e.getMessage());
            }

        });

        /*
        再加一个任务 注意这个任务会在30s后执行 而不是20S后

        因为任务是存放在一个队列中的 是在一个线程中顺序执行
         */
        ctx.channel().eventLoop().execute(()->{
            try {
                System.out.println("TaskQueue的线程是"+Thread.currentThread().getName());
                Thread.sleep(20*1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端 qaq3！",CharsetUtil.UTF_8));

            } catch (InterruptedException e) {
                System.out.println("发生异常" +e.getMessage());
            }

        });

        /*
        解决方案2：用户自定义定时任务 该任务提交到scheduleTaskQueue中
         */
        ctx.channel().eventLoop().schedule(()->{
            //5s后执行
            try {
                System.out.println("TaskQueue的线程是"+Thread.currentThread().getName());
                Thread.sleep(5*1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端 qaq4！",CharsetUtil.UTF_8));

            } catch (InterruptedException e) {
                System.out.println("发生异常" +e.getMessage());
            }
        },5, TimeUnit.SECONDS);
        System.out.println("go on ...");
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //writeAndFlush 将数据写入缓冲并刷新(即写入管道)
        //一般讲  对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello ,客户端 qaq1！",CharsetUtil.UTF_8));
    }

    //处理异常 一般是需要关闭通道

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}
