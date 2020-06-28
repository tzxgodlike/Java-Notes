package com.tzx.nio.groupchat;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //初始化
    public GroupChatServer() {
        try{
            //得到选择器
            selector = Selector.open();
            //得到ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //注册
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen() {

        try {

            //循环监听
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    //有事件处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                    while (iterator.hasNext()) {
                        //取出key
                        SelectionKey key = iterator.next();

                        //监听accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            //设置非阻塞
                            sc.configureBlocking(false);
                            //注册  关注读事件
                            sc.register(selector,SelectionKey.OP_READ);
                            //提示
                            System.out.println(sc.getRemoteAddress()+" 上线");
                        }
                        if (key.isReadable()) {
                            //通道可读状态
                            //处理读 (专门写方法)

                            readData(key);
                        }


                        //处理完之后 删除当前的key
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //读取客户端消息
        }
    }


    //读取客户端消息
    private void readData(SelectionKey key) {

        //用key找到对应的channel
        SocketChannel channel = null;

        try {
            //取到关连的channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取到的长度
            int count = channel.read(buffer);
            //根据count的值做处理
            if (count>0) {
                //把缓冲区的数据转成字符串
                String msg = new String(buffer.array());
                //输出该消息
                System.out.println("form 客户端：" + msg);

                //向其他的客户端转发消息 （排除自己） 专门写一个方法处理
                sendInfoToOtherClients(msg,channel);
            }
        } catch (IOException e) {
            //发生异常 则说明该客户端离线  需要取消注册
            try {
                System.out.println(channel.getRemoteAddress()+" 离线了.....");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } finally {

        }
    }

    //转发消息给其他客户(通道)
    private void sendInfoToOtherClients(String msg,SocketChannel self) throws IOException{

        System.out.println("服务器转发消息中.....");
        //遍历 所有的注册到selector上的socketChannel 并排除self

        //注意selector.keys()和selector.selectedKeys()的差异
        //一个是所有的key  一个是当前发生事件的key
        for(SelectionKey key: selector.keys()) {
            //通过key  取出对应的socketChannel

            Channel targetChannel = key.channel();
            //排除自己 并且排除ServerSocketChannel
            if (targetChannel instanceof SocketChannel && targetChannel != self) {

                //转型
                SocketChannel dest = (SocketChannel) targetChannel;

                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

                //将buffer的数据写入通道
                dest.write(buffer);
            }
        }
    }


    public static void main(String[] args) {
        //创建一个服务器对象
        GroupChatServer groupChatServer = new GroupChatServer();

        groupChatServer.listen();
    }
}
