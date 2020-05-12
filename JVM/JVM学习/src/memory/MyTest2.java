package memory;

/*
    演示栈溢出    使用递归
 */

/*
    用-Xss100K 设置堆栈大小为100K
 */
/*
    使用jconsole C:\Program Files\Java\jdk1.8.0_201\bin  监控
 */
public class MyTest2 {

    private int len;

    public int getLen() {
        return len;
    }

    public void test() {
        this.len++;
        //加睡眠 便于监控
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test();
    }
    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();

        try{
            myTest2.test();
        }catch (Throwable ex){
            System.out.println(myTest2.getLen());
            ex.printStackTrace();
        }
    }
}
