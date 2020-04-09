package Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//代理对象
public class tzxCompany  implements InvocationHandler {

    //被代理的对象
    private  Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //前置增强
        doSomethingBefore();
        //用反射来调方法
        Object ret = method.invoke(factory,args);
        //factory.saleB(height);

        //后置增强
        doSomethingAfter();
        return ret;
    }

    private void doSomethingAfter() {
        System.out.println("售后分析");
    }

    private void doSomethingBefore() {
        System.out.println("包装商品");
    }
}
