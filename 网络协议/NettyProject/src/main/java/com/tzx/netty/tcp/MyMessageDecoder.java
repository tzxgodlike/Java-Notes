package com.tzx.netty.tcp;

import com.tzx.netty.tcp.protocoltcp.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {

    private int couunt;
    /*
    decode方法会被调用多次 直到没有新的元素被添加到list  或者bytebuf中没哟更多的可读字节
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder 被调用次数"+(++couunt));
        //System.out.println("这次收到的buf大小.....是"+ byteBuf.readableBytes());

        byte[] buffer = new byte[26];

        byteBuf.readBytes(buffer);



        list.add(new String(buffer, Charset.forName("utf-8")));
    }
}
