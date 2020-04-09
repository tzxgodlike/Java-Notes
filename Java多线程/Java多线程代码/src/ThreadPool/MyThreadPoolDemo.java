package ThreadPool;

import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(2,5,1L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        //模拟10个用户来办理业务 每个用户就是一个来自外部的请求线程
        try{
            for (int i = 0;i<8;i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"   办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

        /*
        JDK自带的生成线程池
         */
//        //一池5个处理线程
//        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        //一池1个处理线程
//        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
//
//        //一池N个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//
//
//        //模拟10个用户来办理业务 每个用户就是一个来自外部的请求线程
//        try{
//            for (int i = 0;i<10;i++) {
//                threadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+"   办理业务");
//                });
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            threadPool.shutdown();
//        }

    }
}
