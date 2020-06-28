package com.tzx.netty.tcp.protocoltcp;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer  extends ChannelInitializer <SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyMessageEncoder());          //加入编码器
        pipeline.addLast(new MyMessageDecoder());          //加入解码器
        pipeline.addLast(new MyClientHandler());
    }
}
