package com.tzx.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyByteBuf02 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!", Charset.forName("utf-8"));

        //使用api

        if (byteBuf.hasArray()) {

            byte[] content = byteBuf.array();
            System.out.println(new String(content,Charset.forName("utf-8")));
            System.out.println(byteBuf.toString(CharsetUtil.UTF_8));

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());

            System.out.println(byteBuf.readableBytes()); //可以读出来的长度

            //读取某一段
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));
        }
    }
}
