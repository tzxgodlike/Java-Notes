package memory;

/*
    演示死锁
 */
public class MyTest3 {

    /*
    1. 通过jconsole分析死锁  “检测死锁”

    2. 使用visualvm 会直接爆红色死锁 然后查看dump文件

    3. 也可以先命令行Jps找到进程号 再jstack 进程号 定位死锁
     */
    public static void main(String[] args) {
        new Thread(()-> A.method(),"Thread-A").start();

        new Thread(()-> B.method(),"Thread-B").start();

        try {
            Thread.sleep(50000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class A {
    public static synchronized void method() {
        System.out.println("method from A");
        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static synchronized void method() {
        System.out.println("method from B");

        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}