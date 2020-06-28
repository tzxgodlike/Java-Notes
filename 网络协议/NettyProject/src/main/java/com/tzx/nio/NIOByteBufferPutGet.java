package com.tzx.nio;

import java.nio.ByteBuffer;

public class NIOByteBufferPutGet {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(100);
        byteBuffer.putLong(9);
        byteBuffer.putChar('唐');
        byteBuffer.putShort((short)4);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());

        //原因是put时放入short 取得时候取LONG 会超过分配的内存 出现异常
        System.out.println(byteBuffer.getLong());
    }
}
