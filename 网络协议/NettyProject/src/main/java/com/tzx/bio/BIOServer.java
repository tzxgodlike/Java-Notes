package com.tzx.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        //使用线程池机制实现BIO

    /*
    1. 创建一个线程池
    2. 如果有客户端连接 就创建一个线程 与之通信
    3. cmd 使用telnet客户端与该端口通信
        telnet 127.0.0.1 15473
        ctrl + ] 开启发送
        send hello world

    4. 调试程序可以发现  socket的accept和read方法都会阻塞
         [在while循环中发现只有新来了连接或新来了消息 才会打印信息]

    5. 缺点

        1. 每个请求要创建独立的线程 进行read 和 write
        2. 并发数较大时，会创建大量线程 占用资源
        3.  当前线程若没有数据读，会一直阻塞在read上 浪费资源

    6. 适合连接数目较小且固定的架构
     */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //监听6666端口
        ServerSocket serverSocket = new ServerSocket(15473);

        while(true) {
            //监听 等待客户端连接

            System.out.println("等待连接，阻塞中...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到客户端");

            //起一个线程和她通信

            //一个telnet客户端对应的是相同的线程
            newCachedThreadPool.execute(()->{
                handler(socket);
            });

        }
    }

    //编写一个handler方法 和客户端通信
    public static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        //通过socket获取输入流
        try {
            System.out.println("线程ID： "+Thread.currentThread().getId()+"  线程名字： "
            +Thread.currentThread().getName());
            InputStream stream = socket.getInputStream();

            //循环读取客户端发送的数据
            while(true) {
                System.out.println("线程ID： "+Thread.currentThread().getId()+"  线程名字： "
                        +Thread.currentThread().getName());
                System.out.println("等待read，阻塞中...");
                int read = stream.read(bytes);
                if(read!=-1) {  //还可以继续读
                    System.out.println("输出客户端发送的数据： "+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
