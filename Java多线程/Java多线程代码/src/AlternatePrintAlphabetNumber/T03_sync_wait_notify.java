package AlternatePrintAlphabetNumber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class T03_sync_wait_notify {

    /*
    WAIT notify 顺序不能颠倒
    若还需保证t1先于t2执行 可以使用自旋或者countdownLatch
    await() 用来等待计数归零，countDown() 用来让计数减一
     */
    //private static volatile boolean t2Started = false;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o) {
                for (char c : aI){
//                    while (!t2Started){
//                        try {
//                            o.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();   //没有这句话 该线程不会结束
            }
        },"t1").start();

        new Thread(()->{
            synchronized (o) {
                for (char c : aC){
                    System.out.print(c);
                    //t2Started = true;
                    latch.countDown();
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
        },"t2").start();
    }

}
