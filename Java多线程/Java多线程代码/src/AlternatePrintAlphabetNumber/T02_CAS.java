package AlternatePrintAlphabetNumber;

import java.util.concurrent.atomic.AtomicInteger;

public class T02_CAS {

    enum ReadyToRun{
        T1,T2
    }
    /*
    这个枚举类的条件变量也可以用AtomicInteger实现
     */
    //1 内部是被volatile修饰的
    static AtomicInteger threadNo = new AtomicInteger(1);

    /*
    自旋是在用户态 Synchronized可能涉及内核

     */
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{

            for(char c : aI) {
                while (r != ReadyToRun.T1) {}    // 自旋锁 空转
                    System.out.print(c);
                    r = ReadyToRun.T2;
//                while (threadNo.get()!=1) {}    // 自旋锁 空转
//                System.out.print(c);
//                threadNo.set(2);
            }
        },"t1").start();

        new Thread(()->{

            for(char c : aC) {
                while (r != ReadyToRun.T2) {}    //自旋
                    System.out.print(c);
                    r = ReadyToRun.T1;

            }
        },"t2").start();
    }
}
