package Proxy;

import Proxy.DynamicProxy.tzxCompany;
import Proxy.StaticProxy.ProxyCompany;
import Proxy.util.*;

public class tzxBuy {
    public static void main(String[] args) {
        //静态代理
        //真实类
        AToolFactory factory = new AFacoryImpl();
        //放入代理工厂
        ProxyCompany proxyCompany = new ProxyCompany(factory);

        //proxyCompany.sale("22");


        //动态代理
        //A公司
        AToolFactory aToolFactory = new AFacoryImpl();
        //B公司
        BToolFactory bToolFactory = new BFactoryImpl();
        //动态代理公司
        tzxCompany tzxDynamic = new tzxCompany();
        //张三来找我买A
        tzxDynamic.setFactory(aToolFactory);
        // 为他安排一号员工服务 [代理对象]
        AToolFactory tzxProxy1 = (AToolFactory)tzxDynamic.getProxyInstance();
        //1号员工完成服务
        tzxProxy1.sale("D");

        System.out.println("---------------------------------------------");

        //李四买B公司
        tzxDynamic.setFactory(bToolFactory);
        // 为他安排一号员工服务
        BToolFactory tzxProxy2 = (BToolFactory)tzxDynamic.getProxyInstance();
        //1号员工完成服务
        tzxProxy2.saleB(22);

        //把$proxy0这个类输出到硬盘
        ProxyUtils.generateClassFile(aToolFactory.getClass(),tzxProxy1.getClass().getSimpleName());
    }
}
