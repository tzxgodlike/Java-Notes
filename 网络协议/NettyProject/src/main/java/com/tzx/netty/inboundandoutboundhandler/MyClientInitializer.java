package com.tzx.netty.inboundandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer  extends ChannelInitializer <SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new MyLongToByteEncoder());   //out

        //pipeline.addLast(new MyByteToLongDecoder());  //in
        pipeline.addLast(new MyByteToLongDecoder2());

        pipeline.addLast(new MyClientHandler());   //in
    }
}
