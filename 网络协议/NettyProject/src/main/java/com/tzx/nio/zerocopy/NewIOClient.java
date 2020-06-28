package com.tzx.nio.zerocopy;

import javax.xml.ws.WebServiceException;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",7001));

        String fileName = "D:\\文档\\后端学习之路\\网络协议\\NettyProject\\protoc-3.6.1-win32.zip";

        //得到文件channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        //准备发送

        long startTime = System.currentTimeMillis();

        //在Linux下 一个transferTo方法就可以完成
        //在windows下 一个transferTo调用只能发送8M  需要分段 而且要注意传输时位置
        //transferTo底层使用零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总字节数 = " +transferCount+" 耗时："+(System.currentTimeMillis()-startTime));

        fileChannel.close();

    }
}
