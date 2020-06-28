package com.tzx.netty.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/*
    1.自定义一个handler  需要继承netty规定好的某个HandlerAdapter(规范)

 */
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {

    //读取数据 (在这里可以读取客户端发送消息)
    /*
    1. ChannelHandlerContext 上下文对象 含有管道 通道  地址
    2. Object msg 就是客户端发送的数据
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, StudentPOJO.Student student) throws Exception {

        //读取客户端发送的Student.student
        //StudentPOJO.Student student = (StudentPOJO.Student)msg;

        System.out.println("客户端发送的数据 id = "+student.getId()+"名字：" +student.getName());
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
