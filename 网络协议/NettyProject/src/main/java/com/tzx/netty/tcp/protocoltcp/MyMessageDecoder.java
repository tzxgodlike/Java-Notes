package com.tzx.netty.tcp.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {
    /*
    decode方法会被调用多次 直到没有新的元素被添加到list  或者bytebuf中没哟更多的可读字节
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder 被调用");
        //System.out.println("这次读取的buf总大小.....是"+ byteBuf.readableBytes());

        //将二进制字节码 转成 MessageProtocol 数据包

        int len = byteBuf.readInt();
        byte[] content = new byte[len];

        //System.out.println("这次读取的大小是"+content.length);

        byteBuf.readBytes(content);

        //封装成MessageProtocol 放入list 传给下一个handler

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(content);

        list.add(messageProtocol);
    }
}
