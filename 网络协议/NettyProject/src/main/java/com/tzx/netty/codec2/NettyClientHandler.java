package com.tzx.netty.codec2;

import com.tzx.netty.codec.StudentPOJO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    //当通道就绪时  就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //随机发送student或者worker对象到服务器
        int random = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;

        if (0==random) {
            //发送学生
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.StudentType).setStudent(
                    MyDataInfo.Student.newBuilder().setId(5).setName("疾风剑豪").build()).build();
        }else {
            //发送一个worker对象
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.WorkerType).setWorker(
                    MyDataInfo.Worker.newBuilder().setAge(25).setName("盲僧").build()).build();
        }
        //StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(4).setName("唐知行").build();

        ctx.writeAndFlush(myMessage);
    }

    //通道有读取事件时会触发  读取服务器端回复的消息

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息  "+buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址：" +ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
