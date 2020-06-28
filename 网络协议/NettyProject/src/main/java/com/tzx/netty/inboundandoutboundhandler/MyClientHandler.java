package com.tzx.netty.inboundandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {

        System.out.println("从服务端"+channelHandlerContext.channel().remoteAddress()+
                "读取到long "+aLong);
    }

    //重写channelActive 发送数据

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据.....");

        //测试decode调用次数
//        for (int i = 0;i<15;i++) {
//            ctx.writeAndFlush(123456L);
//        }
        ctx.writeAndFlush(123456L);

        //发送16个字节
        //发送的是字符串时  MyLongToByteEncoder<Long>不会被调用
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdacbdacbd", CharsetUtil.UTF_8));
    }
}
