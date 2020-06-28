package com.tzx.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;   //返回结果
    private String para;      //参数

    /*
    调用顺序
    1.channelActive
    2.setPara
    3.call
    4.channelRead
    5.call
     */

    //被代理对象调用 发送数据给服务器  等待被channelread唤醒 wait 所以channelread和call方法要加synchronized
    @Override
    public synchronized Object call() throws Exception {

        context.writeAndFlush(para);

        //进行wait
        wait();   //等待被channelread唤醒 获取到服务器的结果

        return result;
    }

    //与服务器连接后 就会调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;  //因为在其他方法会用到这个ctx   所以用个变量保存一下

    }
    //收到服务器的数据后 会调用该方法
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        result = msg.toString();
        notify();               //唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void setPara(String para) {
        this.para = para;
    }
}
