package com.tzx.netty.tcp.protocoltcp;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private  int count;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {

        //接受到数据并处理
        int len = msg.getLen();
        byte[] content = new byte[len];
        content = msg.getContent();
        //
        System.out.println();

        System.out.println();

        System.out.println();


        System.out.println("服务端接受信息如下 :");
        System.out.println("长度 = "+len);
        System.out.println("内容 = "+new String(content,Charset.forName("utf-8")));

        System.out.println("服务器接收到消息包数量 = "+(++this.count));

        //回复消息
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes("utf-8").length;
        //构建一个协议包

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent.getBytes("utf-8"));

        channelHandlerContext.writeAndFlush(messageProtocol);


    }
}
