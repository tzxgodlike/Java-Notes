import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable {
    @Override
    public void run() {

    }
}


class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        System.out.println("===================come in callable");
        return 1024;
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //两个线程 一个main 一个AAA FutureTask
        //FutureTask(Callable<V> callable)  它是一个Runnable接口实现类
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        Thread t1 = new Thread(futureTask,"AA");
        Thread t2 = new Thread(futureTask,"BB");

        t1.start();

        int result1 = 1024;

        while (!futureTask.isDone()) {

        }
        int reslut2 = futureTask.get();    //要求获得callable线程结果 如果没有计算完成就会阻塞 直到计算完成

        System.out.println("===============result:"+(reslut2+result1));

    }
}
