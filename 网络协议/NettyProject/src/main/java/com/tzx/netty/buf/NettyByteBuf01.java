package com.tzx.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {

    public static void main(String[] args) {
        //创建一个bytebuf
        //1.创建一个对象 该对象包含一个数组arr 是一个byte[10]
        //2. 不需要使用flip
        //3. 因为底层维护了 readindex 和writeindex
        /*
            0~readindex 已经读取的区域
            readindex~writeindex 可读的区域
            writeindex~capcity  可写的区域
                     */
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0;i<10;i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity：" + buffer.capacity());
        //输出1  用坐标读 readindex不会变化
//        for (int i = 0;i<buffer.capacity();i++) {
//            System.out.println(buffer.getByte(i));
//        }


        //输出2 readindex会变化
        for (int i = 0;i<buffer.capacity();i++) {
            System.out.println(buffer.readByte());
        }

        //

    }
}
