package gc;

public class Mytest1 {

    public void method1() {
        /*
        1.  obj这个引用变量是方法内的变量，放到stack里面
            真正object class实例对象放到heap里面

        2. 方法结束后 对应stack中的变量马上回收 但是heap中的对象等到GC来回收
         */
        Object obj = new Object();

        /*
        垃圾判断算法
        1.引用计数法  有地方引用时+1 失效时-1 无法解决对象循环引用
        2.可达性分析法  通过GC ROOT点向下搜索
            gc root包括
            1.在栈帧本地变量中的引用
            2.方法区中的静态引用
            3.native方法中的引用
         */

        /*
        方法区：
        1.可以不要求在该区域实现GC，性价比较低
        2.在堆中，GC一次一般可以回收70%-95%的空间 方法区的效率远低于此
        3.商业JVM都有实现方法区的GC ： 主要回收废弃常量和无用类

            1.类回收需要满足：该类所有的实例都被GC，也就是JVM不存在该class的任何实例
            2.加载该类的classloader已经被GC
            3.该类对应的class对象没有在任何地方被引用 即不能用反射访问该方法
         */
    }
}
