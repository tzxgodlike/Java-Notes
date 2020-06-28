package com.tzx.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {

    /*
    用transferfrom 从通道拷贝文件到另一个通道
     */
    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\tzx.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\tzx2.jpg"
        );

        //获取channel

        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用transfer完成拷贝

        destCh.transferFrom(sourceCh,0,sourceCh.size());

        sourceCh.close();
        destCh.close();

    }
}
