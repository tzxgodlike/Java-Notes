package Proxy.StaticProxy;

import Proxy.util.AToolFactory;
import Proxy.util.BToolFactory;

//代理商
public class ProxyCompany implements AToolFactory, BToolFactory {

    //1.需要包含真实对象RealFactory

    //2. 公司变多了 需要继承多个接口  违反开闭原则

    //3. 真实对象功能被修改  其他的都要跟着修改
    public AToolFactory afacory ;
    public BToolFactory bfacory ;

    @Override
    public void saleB(int height) {
        //前置增强
        doSomethingBefore();
        bfacory.saleB(height);

        //后置增强
        doSomethingAfter();
    }

    public ProxyCompany (AToolFactory aFactory) {
        this.afacory = aFactory;
    }

    public ProxyCompany (BToolFactory bfacory) {
        this.bfacory = bfacory;
    }
    @Override
    public void sale(String size) {

        //前置增强
        doSomethingBefore();
        afacory.sale(size);

        //后置增强
        doSomethingAfter();
    }

    private void doSomethingAfter() {
        System.out.println("售后分析");
    }

    private void doSomethingBefore() {
        System.out.println("包装商品");
    }
}
