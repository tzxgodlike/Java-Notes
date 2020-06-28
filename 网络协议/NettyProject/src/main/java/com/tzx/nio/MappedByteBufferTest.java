package com.tzx.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
    1.MappedByteBuffer 可以让文件直接在堆外内存中修改  操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception{
        //MappedByteBuffer
        RandomAccessFile randomAccessFile = new RandomAccessFile(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\1.txt","rw");

        //获取通道

        FileChannel fileChannel = randomAccessFile.getChannel();

        /*
        参数1：  使用读写模式
        参数2：  可以直接修改的起始位置
        参数3：  映射到内存的大小[不是索引位置]，即可以将1.txt中5个字节映射到内存
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
