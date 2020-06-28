package com.tzx.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

//TextWebSocketFrame 表示一个文本帧
public class MyTextWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {

        System.out.println("服务器端收到消息"+msg.text());

        //回复
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("服务器时间"+ LocalDateTime.now()+
                " "+msg.text()));


    }

    //当web客户端连接后 就会触发这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        //id表示唯一的值 asLongText是唯一   asShortText不唯一
        System.out.println("handlerAdded被调用"+ctx.channel().id().asLongText());
        System.out.println("handlerAdded被调用"+ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("handlerRemoved被调用"+ctx.channel().id().asLongText());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生"+cause.getMessage());
        ctx.close();
    }
}
