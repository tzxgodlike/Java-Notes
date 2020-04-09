package BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class ShareData {
    private  int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();

        try {
            //1. 判断
            while (number != 0) {
                //等待 不能生产
                condition.await();
            }
            //2. 干活
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();

        try {
            //1. 判断
            while (number == 0) {
                //等待 不能生产
                condition.await();
            }
            //2. 干活
            number --;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class Pro_Con_TraditionDemo {

    /*
    生产者消费者模型 传统实现
     */

    /*
    题目 一个初始值为0的变量 两个线程对其交替操作 一个加1 一个减1 来5轮

    理解： 生产一个 就消费一个


    口诀
    1.线程 操作(方法) 资源类   该变量就是资源类[缓存队列]   操作应该为资源类自带

    2.判断 干活 通知    wait方法都放在while循环里面

    3.防止虚假唤醒机制  用while不用if
     */

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0;i<5;i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0;i<5;i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
