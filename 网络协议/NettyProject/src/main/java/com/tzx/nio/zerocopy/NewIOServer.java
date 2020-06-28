package com.tzx.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress(7001);

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ServerSocket serverSocket = ssc.socket();

        serverSocket.bind(address);

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {

            SocketChannel socketChannel = ssc.accept();
            int readCount = 0;

            while (-1!=readCount) {
                try {

                    readCount = socketChannel.read(byteBuffer);

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }

                byteBuffer.rewind();   //倒带  可以继续复用buffer
            }
        }
    }
}
