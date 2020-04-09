package DeadLock;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.HashMap;

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA,String lockB) {
        this.lockB = lockB;
        this.lockA = lockA;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(
                    Thread.currentThread().getName()+" 自己持有"+lockA+" 尝试获得" + lockB
            );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(
                        Thread.currentThread().getName()+" 自己持有"+lockB+" 尝试获得" + lockA
                );
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"T-AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"T-BBB").start();
    }
}
