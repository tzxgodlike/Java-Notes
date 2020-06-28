package com.tzx.nio;

import com.sun.nio.sctp.SctpServerChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception{

        //
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个selector对象
        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(7777));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector  关心事件为 OP_ACCEPT

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后得selectionkey 数量 = " +selector.keys().size());

        /*
        区别
            1.selector.keys().size()是所有监听到的key的数量
            2.selectionKeys.size()是当前发生事件的key的数量
         */
        //循环等待客户端连接
        while (true) {
            if(selector.select(1000)==0) {
                //阻塞1秒 没有任何事件发生 就继续
                System.out.println("服务器等待1秒，无连接...");
                continue;
            }
            //如果返回的大于0 就获取selectionKeys集合
            // 1.说明已经获取到关注的事件
            // 2. selector.selectedKeys()返回关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectedKeys 数量 = " +selectionKeys.size());

            //遍历Set<SelectionKey>
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件  做相应的处理

                if(key.isAcceptable()) {  //如果发生的是OP_ACCEPT事件
                    //说明有新的客户端链接我
                    //给该客户端生成一个sockeychannel

                    /*
                    此时selector中只有serverSocketChannel 所以发生的事件肯定是新建连接
                    去创建socketChannel
                     */
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    //设置为非阻塞
                    socketChannel.configureBlocking(false);

                    System.out.println("客户端连接成功 生成一个socketChannel "+socketChannel.hashCode());
                    /*
                    serverSocketChannel.accept()本身是阻塞的 在BIO中 并不知道有没有
                    socket来链接

                    而在NIO中已经获取到了key事件 说明一定会有连接来 所以不会阻塞
                     */

                    //将新创建的socketChannel注册到selector中去 并且关心读事件
                    //同时给该channel关联一个buffer

                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后，注册到selectionkey 数量 = " +selector.keys().size());
                }

                if( key.isReadable()) {  //发生的是可读事件

                    //通过key得到对应的channel
                    SocketChannel channel = (SocketChannel)key.channel();

                    //获取到该channel关键的buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel.read(buffer); //把channel中数据读取出 写入buffer

                    System.out.println("从客户端获取 "+new String(buffer.array()));

                }

                //手动从集合中移除当前的selectionkey  防止重复操作
                keyIterator.remove();
            }

        }
    }
}
