package memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/*
    方法区产生内存溢出错误：

    1.7之前是永久代 永久代是和堆连在一起的一段连续内存
    其垃圾回收和老年代的垃圾回收是绑定在一起的
    当进行一次FULLGC时，永久代的数据很可能发生移动

    所以1.8后把永久代剥离出来 放在与堆不相连的区域 可以动态增加元空间的容量
    内存管理：
        1.之前需要不同的垃圾回收器处理
        2.只需要执行元空间的C++代码
        3.类和其元数据的生命周期和对应的类加载器是相同的
 */
public class MyTest4 {

    /*
    运行期产生类[如动态代理] 其类信息会放在元空间 造成溢出
     */

    /*
    导入cglib包  cglib-nodep-3.2.8
     */

    /*
    默认的元空间到最大值21M后会扩容
    设置-XX:MaxMetaspaceSize=10m 来不让其扩容  1.8的新参数

    改为200M 便于监控

    使用jconsole 可以看到元空间内存变化

    使用jvisiualvm监控
     */
    public static void main(String[] args) {
        for(;;) {
            Enhancer enhancer = new Enhancer();
            //设置父类 cglib通过生成父类的子类来进行代理
            enhancer.setSuperclass(MyTest4.class);
            enhancer.setUseCache(false);
            //设置回掉方法 调用代理对象的方法就等价于调用该方法
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy)->
                    proxy.invokeSuper(obj,args1));

            System.out.println("hello  world");
            //每一次循环都创建一个子类
            //子类的类信息就存放在元空间中
            enhancer.create();
        }
    }
}
