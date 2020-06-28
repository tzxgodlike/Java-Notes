package com.tzx.netty.codec2;

import com.tzx.netty.codec.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/*
    1.自定义一个handler  需要继承netty规定好的某个HandlerAdapter(规范)

 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    //读取数据 (在这里可以读取客户端发送消息)
    /*
    1. ChannelHandlerContext 上下文对象 含有管道 通道  地址
    2. Object msg 就是客户端发送的数据
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        //根据dataType 显示不同信息
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if (dataType==MyDataInfo.MyMessage.DataType.StudentType) {

            MyDataInfo.Student student = msg.getStudent();
            System.out.println("学生 id = "+student.getId()+"名字：" +student.getName());

        }else if (dataType==MyDataInfo.MyMessage.DataType.WorkerType){

            MyDataInfo.Worker worker = msg.getWorker();
            System.out.println("工人 id = "+worker.getAge()+"名字：" +worker.getName());

        }else {
            System.out.println("传输的类型不对...........");
        }
        //System.out.println("客户端发送的数据 id = "+student.getId()+"名字：" +student.getName());
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
