package BlockingQueue;

import com.sun.deploy.security.MozillaMyKeyStore;
import sun.rmi.transport.proxy.RMIHttpToCGISocketFactory;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {

    private volatile boolean FLAG = true;  //默认开启 进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    //参数都是传接口 不传类
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myPro() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            //i++
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);

            if (retValue) {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        //flag被置为false
        System.out.println(Thread.currentThread().getName()+"\t 被main叫停 生产结束");
    }

    public void myCon() throws Exception {
        String reslut = null;
        boolean retValue;
        while (FLAG) {

            reslut = blockingQueue.poll(2L, TimeUnit.SECONDS);



            if (null==reslut||reslut.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过2秒没有取到蛋糕 消费退出");

                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+reslut+"成功");


        }

    }

    public void stop() throws Exception {
        this.FLAG = false;
    }
}
public class Pro_Con_BlockingQueueDemo {

    public static void main(String[] args) {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myPro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Pro").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myCon();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5s时间到 main线程叫停生产");
        try {
            myResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //List list = null;
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));  //队列已满  抛出异常

    }
}
