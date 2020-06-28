package com.tzx.netty.dubborpc.provider;

import com.tzx.netty.dubborpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    //当有消费方调用该方法时 就返回一个字符串
    @Override
    public String hello(String mes) {

        System.out.println("收到客户端消息="+mes);

        if (mes!=null) {
            return "你好客户端，我已经收到你的消息 ["+mes+"]";
        }else {
            return "你好客户端，我已经收到你的消息 ";
        }
    }
}
