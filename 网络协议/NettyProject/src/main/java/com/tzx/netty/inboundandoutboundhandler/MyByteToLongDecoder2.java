package com.tzx.netty.inboundandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        System.out.println("ReplayingDecoder被调用次数.....");
        //long 8个字节   ReplayingDecoder不需要判断数据是否足够读取 内部会进行处理
        list.add(byteBuf.readLong());
    }
}
