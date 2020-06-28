package com.tzx.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    /*
    用一个buffer完成文件拷贝
     */
    public static void main(String[] args) throws  Exception{

        //创建文件的输入流

        FileInputStream fileInputStream = new FileInputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\file01.txt");

        //获取channel
        FileChannel filechannel1 = fileInputStream.getChannel();

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream(
                "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\file02.txt");

        //通过输出流获取对应的文件channel   其真实类型是FileChannelImpl
        FileChannel filechannel2 = fileOutputStream.getChannel();

        //(int)file.length
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);

        while(true) {
            //循环读取  因为buffer一次性只能读固定大小  可能读不完文件

            //这里有个重要操作  清空数据 重置标志位

            /*
             position = 0;
            limit = capacity;
            mark = -1;
            return this;
             */
            byteBuffer.clear();

            int read = filechannel1.read(byteBuffer);

            System.out.println("read = "+read);
            if (read==-1) {
                //表示读完
                break;
            }
            //buffer中数据写入02
            byteBuffer.flip();
            filechannel2.write(byteBuffer);
        }




        //关闭流
        fileOutputStream.close();
        fileInputStream.close();
    }
}
