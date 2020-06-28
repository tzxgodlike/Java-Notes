package com.tzx.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组 管理所有的channel  是静态变量
    //GlobalEventExecutor.INSTANCE 是一个单例的全局事件执行器
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //私聊  用hashmap保存

    public static ConcurrentHashMap<String,Channel> channels = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<User,Channel> channels2 = new ConcurrentHashMap<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //handlerAdded 表示连接建立 一旦连接   第一个执行
    //将当前连接加入到ChannelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        //将该客户加入聊天的信息推送给其他的在线的客户端
        //该方法会将channelGroup中所有的channel遍历 并发送该消息  我们无需自己遍历
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天"
                +sdf.format(new java.util.Date())+"\n");
        channelGroup.add(channel);

        channels.put("id-100",channel);
        channels2.put(new User(100,"1234"),channel);
    }

    //断开连接 会被触发  将某某客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开聊天"+
                sdf.format(new java.util.Date())+"\n");
        //channelGroup会自动移除该channel 无需自己操作
    }

    //表示channel处于活动状态 提示xx上线  只给服务端看就行
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 上线了~~~"+
                sdf.format(new java.util.Date())+"\n");
    }

    //当channel处于非活动状态  提示xx下线了

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 离线了~~~"+sdf.format(new java.util.Date())+"\n");
    }

    //读取数据 并转发
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

        //获取到当前的channel
        Channel channel = ctx.channel();
        //这时遍历channelGroup  根据不同情况 回复不同的消息

        channelGroup.forEach(ch->{
            if(channel!=ch) {
                //不是当前的channel 直接转发
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送了消息"+s+
                        sdf.format(new java.util.Date())+"\n");
            }else {
                //不用给自己转发
                ch.writeAndFlush("自己发送了一条消息..."+sdf.format(new java.util.Date())+"\n");
            }
        });
    }

    //发生异常


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
