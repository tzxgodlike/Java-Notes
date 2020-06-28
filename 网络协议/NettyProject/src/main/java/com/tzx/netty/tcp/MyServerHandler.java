package com.tzx.netty.tcp;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private  int count;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] buffer = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(buffer);

        //将buffer转成字符串

        String message = new String(buffer, Charset.forName("utf-8"));
        System.out.println("服务器端接收到数据 "+message);

        System.out.println("服务器收到到消息量= "+(++this.count));

        //服务器回送数据给客户端

        ByteBuf response = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("utf-8"));

        //ByteBuf response = Unpooled.copiedBuffer("hello,client" , CharsetUtil.UTF_8);
        channelHandlerContext.writeAndFlush(response);
        //Thread.sleep(1000);
    }
}
