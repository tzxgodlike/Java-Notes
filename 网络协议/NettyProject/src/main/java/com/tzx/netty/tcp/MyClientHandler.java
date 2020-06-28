package com.tzx.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        System.out.println(new String(bytes, Charset.forName("utf-8")));
        System.out.println("客户端收到到消息量= "+(++this.count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送十条数据 hello  server
        for (int i = 0;i<10;i++) {
            ByteBuf buffer = Unpooled.copiedBuffer("helloserver" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(buffer);
        }
    }
}
