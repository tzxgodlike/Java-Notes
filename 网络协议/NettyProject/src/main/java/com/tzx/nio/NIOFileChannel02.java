package com.tzx.nio;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {

    /*
    从文件中读数据
     */
    public static void main(String[] args) throws Exception{
        //创建文件的输入流

        FileInputStream fileInputStream = new FileInputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\file01.txt");

        //获取channel
        FileChannel filechannel = fileInputStream.getChannel();

        //(int)file.length
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);

        //将channel数据读入buffer
        filechannel.read(byteBuffer);

        //buffer转为String
        System.out.println(new java.lang.String(byteBuffer.array()));


    }
}
