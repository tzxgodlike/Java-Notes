package Proxy.util;

public class BFactoryImpl implements BToolFactory {

    @Override
    public void saleB(int height) {
        System.out.println("根据要求定制了一个高为"+height+"的娃娃");
    }
}
