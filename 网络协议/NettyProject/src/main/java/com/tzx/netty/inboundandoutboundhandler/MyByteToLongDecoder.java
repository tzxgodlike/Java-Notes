package com.tzx.netty.inboundandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    //list集合把解码后得数据 传给下一个handler

    //decode方法会被调用多次 直到没有新的元素被添加到list  或者bytebuf中没哟更多的可读字节

    //同时list将数据给下一个handler的业务处理方法也会被调用相同的次数
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //long 8个字节
        if (byteBuf.readableBytes()>=8) {
            list.add(byteBuf.readLong());
        }
    }
}
