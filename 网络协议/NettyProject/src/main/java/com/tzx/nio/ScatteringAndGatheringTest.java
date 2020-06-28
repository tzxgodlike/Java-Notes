package com.tzx.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {

    /*
    Scattering：将数据写入buffer时，可以使用buffer数组 依次写入
    Gathering： 从buffer读取数据时，可以使用buffer数组 依次读取

     */

    /*
    实验过程：

        1.telnet先发送6个字节 观察输出
     */

    public static void main(String[] args) throws Exception{

        //使用ServerSocketChannel 和SocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket 并启动

        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接(telnet)

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLen = 8;  //假定从客户端接受8个字节
        //循环读取
        while (true) {
            int btyeRead = 0;

            while (btyeRead<messageLen) {

                long l = socketChannel.read(byteBuffers);
                btyeRead+=l;
                System.out.println("btyeRead= "+btyeRead);
                //使用流打印 看看当前buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(buffer->"position="
                +buffer.position()+",limit= "+buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(buffer->buffer.flip());

            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite<messageLen) {
                long l = socketChannel.write(byteBuffers);
                byteWrite+=l;
            }
            //clear
            Arrays.asList(byteBuffers).forEach(buffer->{
                buffer.clear();
            });

            System.out.println("btyeRead= "+btyeRead+"byteWrite="+byteWrite+",messageLen"+messageLen);
        }


    }

}
