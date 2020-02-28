# synchronized 

 1.monitor(锁对象)
 每个Java对象都关联一个monitor
 2.轻量级锁
 多线程没有竞争时
 轻量级锁没有使用monitor对象 当有竞争时 才进入锁膨胀 变为monitor
 之前轻量级锁的解锁流程也会失败 转为重量级锁解锁
 3.自旋优化 重量级锁竞争
 重量级锁竞争的时候，还可以使用自旋来进行优化，如果当前线程自旋成功
 （即这时候持锁线程已经退出了同步 块，释放了锁），这时当前线程就可以避免阻塞。

# wait notify 虚假唤醒
 sleep不会释放对象锁  wait会释放锁 notify随机叫醒
 synchronized只支持一个条件 当有两个线程对应两个条件变量时，room.notify只会唤醒一个线程 但那个线程可能不是对应条件变量 造成虚假唤醒 
 所以使用notifyall 把所有等待的都唤醒 再在两个线程中的判断语句从if改为while
 于是即使被唤醒 也需要再判断一次条件变量是否被置为true 

# 同步模式之保护性暂停  
用一个线程等待另一个线程的执行结果
即 Guarded Suspension，用在一个线程等待另一个线程的执行结果
要点
有一个结果需要从一个线程传递到另一个线程，让他们关联同一个 GuardedObject 
如果有结果不断从一个线程到另一个线程那么可以使用消息队列（见生产者/消费者） 
JDK 中，join 的实现、Future 的实现，采用的就是此模式 因为要等待另一方的结果，因此归类到同步模式

    join原理

# 生产者消费者 
    用wait notify来实现
# park unpark

# 线程状态转化
    new runnable blocked waiting timed_waiting terminated

# 多把锁 

# 活跃性
    1.死锁
    先jps找出端口 再jstack定位死锁
    两个线程按相同顺序加锁 解决死锁
    2.哲学家问题
    3.活锁 两个线程互相改变结束条件
      解决：执行时间交错开 增加一些随机的睡眠时间
    4.饥饿
        顺序加锁会出现饥饿问题

# reentrantLock  可重入锁
    1.可重入的概念
    2.可打断  可以避免死锁
    3.锁超时   
        trylock方法 解决哲学家问题
        关键在于synchronized在右边筷子被占有时没有释放左边筷子
        所以trylock为false时，unlock
        筷子类继承reentrantlock \
    4.公平锁 会降低并发度
    5.条件变量
        创建condition    lock.newCondition
        使用await signal方法
        reentrantLock支持多个条件变量 不同condition的await对应相对的signal
        也得使用while循环再判断一次

# JMM Java内存模型
    多个线程并发访问时的可见性 有序性
    原子性 - 保证指令不会受到线程上下文切换的影响 
    可见性 - 保证指令不会受 cpu 缓存的影响 
    有序性 - 保证指令不会受 cpu 指令并行优化的影响

# 可见性
    1.退不出的循环  main线程修改run变量对t线程不可见
        1.初始状态， t 线程刚开始从主内存读取了 run 的值到工作内存。
        2.因为 t 线程要频繁从主内存中读取 run 的值，JIT 编译器会将 run 的值
        缓存至自己工作内存中的高速缓存中， 减少对主存中 run 的访问，提高效率
        3. 1 秒之后，main 线程修改了 run 的值，并同步至主存，而 t 是从自己
        工作内存中的高速缓存中读取这个变量 的值，结果永远是旧值
        4.解决方法：
            volatile关键字
            它可以用来修饰成员变量和静态成员变量，他可以避免线程从自己的工作缓存中查找变量的值，必须到主存中获取它的值，线程操作 volatile 变量都是直接操作主存 
            synchronized也可以保证可见性 但是会创建monitor 
            在死循环鸿加sout 也能可见性
    2.可见性对比原子性
        1.volatile可以保证一个写线程 多个读线程时的可见性 但不能保证原子性
            对 volatile 变量的写指令后会加入写屏障 前面的代码就不会重排到后
            对 volatile 变量的读指令前会加入读屏障 后面的代码就不会重排到前

        多个线程读写时，指令交错问题仍然出现
        2.synchronized 语句块既可以保证代码块的原子性，也同时保证代码块内变量的可见性。
        但缺点是 synchronized 是属于重量级操作，性能相对更低
         
# 有序性
    JMM在不影响结果的情况下 会把两个执行语句重新排列
    不影响单线程 但影响多线程
    使用压测工具 可以检测出问题
    解决办法 加volatile
# 两阶段终止
    1.interrupt 可以打断正在执行的线程，无论这个线程是在 sleep，wait，还是正常运行
    interrupt打断sleep时会出现异常 需要try catch之后重置打断标识
    2.volatile 关键字停止标记 直接break 不需要打断标识、
# 犹豫模式 balking 
    保证多线程的原子性

# 无锁模式保护线程安全
    1.CAS 比较并设置 
    AtomicInteger 的cas方法配合volatile关键字获取最新值 才能实现
    2.无锁效率比synchronized高
        因为cas失败后继续while循环 不会线程阻塞 不会上下文切换
    3.线程数太多了 效率也会变低
    4.
        CAS：适合线程少，多核CPU 乐观锁 体现无锁并发 无阻塞并发
        synchronized：悲观锁思想 
    
# JUC工具类
    1. AtomicInteger 有volatile修饰的value 用于修改账户不用锁
        1.AtomicInteger.compareAndSet(A,B)  A与value对比 若相同则更新为B
        2.简洁操作
    2.原子引用类型
        1.AtomicReference<T> a   
    3.ABA问题
        1.使用AtomicStampedReference 带有版本号 每CAS一次 stamp就+1
        static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
        ref.compareAndSet(A,B,stamp,stamp+1)
        2.若不关心变量被改过多少次 只关心有没有被修改过 可以使用
        AtomicMarkedReference
        static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", true);
        a.compareAndSet(A,B,true,false)
    4.原子数组
    5.字段更新器
    6.原子累加器

# 线程状态切换 

    待完成

# 线程池
    1.自定义线程池 
        是一个生产者消费者模型 threadpool是消费者 task是生产者 
        thread用worker类封装 线程池的线程执行完一个任务后会找队列中的任务
        继续执行
        当thread可用数量不够时，把task放入blockingqueue  blockingqueue和threadpool都需要线程加锁 
        超时阻塞添加
        当队列满时，把方法抽象出一个接口，不写死在线程池中，由调用者来决定
        rejectpolicy



# AQS 
    1.AQS是一个用来构建锁和同步器的框架，使用AQS能简单且高效地构造出应用广泛的大量的同步器，比如我们提到的ReentrantLock，Semaphore，其他的诸如ReentrantReadWriteLock，SynchronousQueue，FutureTask等等皆是基于AQS的。当然，我们自己也能利用AQS非常轻松容易地构造出符合我们自己需求的同步器。
    2.定义资源的共享方式：
        1.Exclusive（独占）：只有一个线程能执行，如ReentrantLock。又可分为公平锁和非公平锁：
            公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
            非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
        2.Share（共享）：多个线程可同时执行，如Semaphore/CountDownLatch。Semaphore、CountDownLatch、 CyclicBarrier、ReadWriteLock 我们都会在后面讲到。
    3.semaphore
        停车位相当于共享资源 汽车相当于线程 semaphore就相当于空闲的车位
        先初始化信号量为3 再初始化10个线程 每个线程先获得semaphore 之后再释放
        没有信号量时，10个线程会同时结束，有信号量之后，第4个线程在信号量释放之前会阻塞
        使用 Semaphore 限流，在访问高峰期时，让请求线程阻塞，高峰期过去再释放许可，当然它只适合限制单机 线程数量，并且仅是限制线程数，而不是限制资源数
    4.countdownlatch 倒计时锁
        用来进行线程同步协作，等待所有线程完成倒计时。
        其中构造参数用来初始化等待计数值，await() 用来等待计数归零，countDown() 用来让计数减一
        1.应用 等待多线程准备完毕  例如LOL等待5个玩家都加载到100才开始游戏
         * "\r"可以让后一次打印覆盖上一次打印的值的位置
         * 匿名内部类只能引用局部常量 不能引用局部变量 所以一般把循环变量i赋给k
        2.应用2 等待多个远程调用结束 
    4.CyclicBarrier  与countdownlatch类似
    循环栅栏，用来进行线程协作，等待线程满足某个计数。构造时设置『计数个数』，每个线程执 行到某个需要“同步”的时刻调用 await() 方法进行等待，当等待的线程数满足『计数个数』时，继续执行
    调用await时，计数减一
    计数为0时，下次调用await，又会变为初始值。所以可以重复使用
    线程池的线程数与CyclicBarrier初始个数相等

# 线程安全集合类
    1.遗留的线程安全集合如 Hashtable ， Vector  内部使用synchronized
    2.使用 Collections 装饰的线程安全集合 使用装饰器模式 用synchronized在内部装饰和1差不多
    3.java.util.concurrent 有三类
        Blocking 大部分实现基于锁，并提供用来阻塞的方法 
        CopyOnWrite  之类容器修改开销相对较重 
        Concurrent 性能较高 内部很多操作使用 cas 优化，一般可以提供较高吞吐量 弱一致性 遍历时弱一致性，例如，当利用迭代器遍历时，如果容器发生修改，迭代器仍然可以继续进行遍 历，这时内容是旧的 求大小弱一致性，size 操作未必是 100% 准确 读取弱一致性
    4.concurrentHashMap 细粒度锁
         1.当两个hash方法组合时，也是非原子性的
            应用：put和get方法组合时，即使是concurrentHashMap也会出问题 所以关键在于这两个方法要成原子性
         Integer counter = map.get(word);            
         int newValue = counter == null ? 1 : counter + 1;            map.put(word, newValue); 
        组合成一个方法
        LongAdder value = map.computeIfAbsent(word, (key) -> new LongAdder())
        value.increment(); 
        LongAdder是累加器 value.increment()是自增1 
        相同的key返回相同的累加器
        2.
            1. 1.7
            首先将数据分为一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据时，其他段的数据也能被其他线程访问。
            2. 1.8
            ConcurrentHashMap取消了Segment分段锁，采用CAS和synchronized来保证并发安全。数据结构跟HashMap1.8的结构类似，数组+链表/红黑二叉树。Java 8在链表长度超过一定阈值（8）时将链表（寻址时间复杂度为O(N)）转换为红黑树（寻址时间复杂度为O(log(N))）
            synchronized只锁定当前链表或红黑二叉树的首节点，这样只要hash不冲突，就不会产生并发，效率又提升N倍。
    5.hashmap的并发问题
        jdk7 解决冲突时拉链法 新加入节点会加入链表头部
        https://coolshell.cn/articles/9606.html 死链  1.8已解决
        问题就在于 有两个线程要添加值 都出发了扩容 第一个线程完成扩容之后 链表的先后顺序发生了变化(因为是头插法)A-B变成B-A 而第二个线程中断前记录了A-B 所以移动A 之后 继续移动B 而B此时的后继是A 所以下一步又会移动A 造成了死循环A-B-A
    6.blockingqueue
        当队列容器已满，生产者线程会被阻塞，直到队列未满；当队列容器为空时，消费者线程会被阻塞，直至队列非空时为止。
        使用ReentrantLock 创建两个条件变量 
  
 
# 读写锁
    1. ReentrantReadWriteLock 
    当读操作远远高于写操作时，这时候使用 读写锁 让 读-读 可以并发，提高性能。 类似于数据库中的 select ... from ... lock in share mode

 







