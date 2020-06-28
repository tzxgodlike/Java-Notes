package com.tzx.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    /*
    流包装了Chanel  本地文件写数据
     */
    public static void main(String[] args) throws Exception {
        String str = "hello world";

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\file01.txt");

        //通过输出流获取对应的文件channel   其真实类型是FileChannelImpl
        FileChannel filechannel = fileOutputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(str.getBytes());

        //改变buffer状态
        byteBuffer.flip();

        //将buffer数据写入channel
        filechannel.write(byteBuffer);

        //关闭流
        fileOutputStream.close();
    }
}
