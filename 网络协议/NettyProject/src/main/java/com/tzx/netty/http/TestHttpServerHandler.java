package com.tzx.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/*
    1.SimpleChannelInboundHandler 是ChannelInboundHandlerAdapter的子类
    2.HttpObject表示客户端和服务器端相互通讯的数据被封装成httpobject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler <HttpObject>{

    /*
    读取客户端数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //判断msg是不是 httprequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg类型 = "+msg.getClass());
            System.out.println("客户端地址 "+ctx.channel().remoteAddress());
            //回复消息给浏览器 [HTTP格式]

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了favicon,不做响应");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);

            //构造HTTP响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/palin;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //将构建好的response返回
            ctx.writeAndFlush(response);

        }
    }
}
