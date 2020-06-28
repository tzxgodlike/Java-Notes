package com.tzx.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {
        /*
        举例说明buffer的使用

        客户端不直接把数据写入通道而是放在buffer，这样就可以实现非阻塞
         */

        //创建一个buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //放入数据
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);

        //从buffer读取数据
        //将buffer状态转换 读写切换

        /*
        limit = position;
        position = 0;
        mark = -1;
        return this;
         */
        intBuffer.flip();
        intBuffer.position(1);  //只输出11~14  不输出10
        intBuffer.limit(3);     //只输出11 12 读不到边界值

        while(intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }
}
