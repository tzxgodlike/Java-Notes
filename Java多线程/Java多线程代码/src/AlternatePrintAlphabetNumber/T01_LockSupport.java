package AlternatePrintAlphabetNumber;

import java.util.concurrent.locks.LockSupport;

public class T01_LockSupport {

    /*
    LockSupport.park(); 阻塞当前线程
    LockSupport.unpark(t) 叫醒指定线程
    效率很高

    1.想让哪个线程后执行，就先让它park
    2.可以先unpark 再park的时候也不会阻塞 继续执行
     */
    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(()->{
            for (char c : aI){
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒t2
                LockSupport.park();     //阻塞t1
            }
        },"t1");

        t2 = new Thread(()->{
            for (char c : aC){
                LockSupport.park();   //阻塞t2
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
