package com.tzx.netty.dubborpc.publicinterface;


//这个借口是服务方和消费方都需要
public interface HelloService {

    String hello(String mes);
}
