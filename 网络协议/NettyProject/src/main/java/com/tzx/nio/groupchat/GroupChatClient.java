package com.tzx.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupChatClient {

    /*
     客户端先生成SocketChannel 服务端再accept该SocketChannel
     */
    //定义相关属性
    private final String HOST = "127.0.0.1";  //服务器IP

    private final int PORT = 6667;            //服务器端口

    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //初始化
    public GroupChatClient() throws IOException {
        selector = Selector.open();
        //连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //注册
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username+"is ok...");
    }

    //向服务器发送消息
    public void sendInfo(String info) {

        info = username+" 说：" +info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {

        } finally {

        }
    }

    //读取服务器端回复的消息

    public void readInfo() {
        try {
            int readChannels = selector.select(2000);

            if (readChannels > 0) {

                //有事件处理
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    //取出key
                    SelectionKey key = iterator.next();

                    if (key.isReadable()) {
                        //通道可读状态
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (sc==socketChannel) System.out.println("是相同的socketChannel");
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        String msg = new String(buffer.array());

                        //删除头尾空白符的字符串。
                        System.out.println(msg.trim());

                    }


                    //处理完之后 删除当前的key
                    iterator.remove();
                }
            }else {
                //System.out.println("没有可用的通道......");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception{
        //启动客户端

        GroupChatClient chatClient = new GroupChatClient();

        //循环读取  每隔三秒从服务器读取数据
        new Thread(()->{
            while (true) {
               chatClient.readInfo();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        }).start();


        //发送数据给服务端
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            chatClient.sendInfo(s);
        }
    }
}
