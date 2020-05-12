package memory;

public class MyTest5 {

    /*
    dump文件  记录某一个时刻 内存的信息+
     */
    /*
    使用jmap命令行

    1.jps查看进程号
     jps -l可以打印包名+类名

    2.jmap -clstats 11552 查看类加载器信息
    bootstrap                         启动类加载器
    Launcher$ExtClassLoader           扩展类加载器
    Launcher$AppClassLoader           应用类加载器
    /ResourceBundle$RBClassLoader     系统类加载器
                自定义加载器


    3.jmap -heap id 可以看到堆的信息


    使用jstat命令

    1.  jstat -gc id 查看gc情况  可以看到JVM[堆和元空间]的变化情况

    jcmd 也可以查看java进程

        1. jcmd pid VM.flags 查看Java进程启动时的JVM参数信息

        2. jcmd pid help 列出当前运行的java进程可以执行的操作

        3. jcmd pid help JFR.dump 查看具体命令的选项

        4. jcmd 5328 PerfCounter.print 查看JVM性能相关的参数

        5. jcmd pid VM.uptime 查看JVM的启动时长

        6. jcmd pid Thread.print 查看线程堆栈

        7. jcmd pid GC.heap_dump filename 到处heap dump文件 可以通过jvisualVM查看

        8. jcmd pid VM.system_properties 查看JVM的属性信息


    使用jstack ： 可以查看Java程序中进程的堆栈信息

        1.jstack pid  定位死锁

     jmc : Java MISSON CONTROL  图形化的监控程序 功能强大

     jfr: 飞行记录器  可以实时获得Java运行情况

     jhat ： 先生成dump文件   然后jhat .hprof 就可以通过http端口查看该文件

     */
    public static void main(String[] args) {
        for(; ;){
            System.out.println("hello world");
        }
    }
}
