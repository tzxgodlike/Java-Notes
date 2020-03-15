package AlternatePrintAlphabetNumber;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T05_lock_condition {
    /*
    ReentrantLock可以有多个条件变量
    一个条件变量对应一个条件队列
     */

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        char[] ac = "abcdefg".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                for (char c : aI){
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();   //t1进入1条件队列等待
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"t1").start();
        new Thread(()->{
            try {
                lock.lock();
                for (char c : aC){
                    System.out.print(c);
                    condition3.signal();
                    condition2.await();
                }
                condition3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"t2").start();
        new Thread(()->{
            try {
                lock.lock();
                for (char c : ac){
                    System.out.print(c);
                    condition1.signal();
                    condition3.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        },"t3").start();
    }
}
