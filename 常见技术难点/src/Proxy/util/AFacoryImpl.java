package Proxy.util;

public class AFacoryImpl implements AToolFactory {
    @Override
    public void sale(String size) {
        System.out.println("根据要求定制了一个size为"+size+"的工具");
    }
}
