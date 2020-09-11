## ThreadLocal
    1.应用场景 需要一个static做全局变量 但是多线程下不同的线程会修改
    该变量 所以需要每个线程都拥有自己的该变量 所以把该变量放入ThreadLocal
    对象中 即每个线程都有该对象的独立拷贝
    ThreadLocal具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问到想要的值。

     1.<key,value> 中key是弱引用 会被回收 value 是强引用  不会被回收 出现内存泄漏

        2.key为ThreahLocal变量 value为set的变量副本

        3.为什么key是弱引用 value强引用？

            1.当ThreahLocal被回收的时候 强引用使得map里面的key不会被回收

            2.如果value是弱引用 那么其他线程回收ob 会让value变为null
         
    2.理解

        1.场景1 
        假设有I一个方法是线程安全有问题的
        使用synchronized的话，表示当前只有1个线程才能访问方法，其他线程都会被阻塞。当访问的线程也阻塞的时候，
        其他所有访问该方法的线程全部都会阻塞，这个方法相当地 "耗时"。
        使用ThreadLocal的话，表示每个线程的本地变量中都有SimpleDateFormat这个实例的引用，也就是各个线程之间完全没有关系，也就不存在同步问题了。

        综合来说：使用synchronized是一种 "以时间换空间"的概念， 而使用ThreadLocal则是 "以空间换时间"的概念。

        2. 场景2
            传递参数 在一个请求线程中 参数共享 
            在不同的请求线程中 参数不共享

            也就是说A B C 三个方法都共享这些参数 而不用在方法括号中传参

    2.在每个线程Thread内部有一个ThreadLocal.ThreadLocalMap类型的成员变量
    t.threadLocals，这个threadLocals就是用来存储实际的变量副本的，键值为当前
    ThreadLocal变量，value为变量副本（即要保存的变量）。
    意思就是，每个线程都有自己单独的容器来保存变量，键值为ThreadLocal变量名，
    这样可以一个线程可以有多个ThreadLocal变量被保存 先ThreadLocal.set(ob)
    保存一个ob  再ThreadLocal.get() 实际上是t.threadLocals.get(threadLocal)
    得到ob
    3.用完之后要remove 因为线程在线程池中会复用 避免之前保存了变量
    4.ThreadLocal变量一般被static修饰


# synchronized   可以重入

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
    blockingqueue 会阻塞当前线程

# 死锁条件
    1.
        互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。

        请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。

        不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。

        循环等待条件: 若干进程间形成首尾相接循环等待资源的关系
    
    2. 原因
        1.系统资源不足
        2.进程运行推进顺序不当
        3.资源分配不当
    
    4.定位分析
        D:\文档\后端学习之路\Java多线程\Java多线程代码\src\DeadLock

        1.在IDEA的terminal中输入jps -l   找到进程编号 20324 DeadLock.DeadLockDemo

        2.jstack 20324


# park unpark
    1.LockSupport中的方法
    2.先 park 再 unpark   
// 暂停当前线程 LockSupport.park(); 
 
// 恢复某个线程的运行 LockSupport.unpark(暂停线程对象)


# 线程状态转化
    new runnable blocked waiting timed_waiting terminated



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

## Sychronized 和 reentrantlock区别

    1.Sychronized是jvm层面   reentrantlock是API层面
    
    2.Sychronized不需要手动结束   reentrantlock需要unlock 释放

    3.Sychronized不可以中断  除非抛出异常或者是正常完成

      reentrantlock可中断 
        1.设置超时方法 trylock
        2.lockInterruptibly()放代码块中 调用interrupt方法可以中断

    4.加锁是否公平
        1.Sychronized 非公平锁
        2.reentrantlock两者都可以 默认非公平锁
    
    5.绑定多个条件
        1.Sychronized没有
        2.reentrantlock绑定多个条件，可以精确唤醒 不像Sychronized要么随机唤醒一个 要么唤醒全部线程




# JMM Java内存模型
    1.主存 工作内存
    2.多线程修改变量时 会把主存的变量拷贝会工作内存(栈空间) 工作内存是线程私有 只能通过主存来线程间通信
    多个线程并发访问时的可见性 有序性
    原子性 - 保证指令不会受到线程上下文切换的影响 
    可见性 - 保证指令不会受 cpu 缓存的影响 
    有序性 - 保证指令不会受 cpu 指令并行优化的影响

# volatile关键字
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
    3.volatile加Automic类实现原子性
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
    AtomicInteger 的cas方法配合volatile关键字获取最新值 才能实现 如果CAS成功 就实现自增
        public final int incrementAndGet() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }

    compareAndSet(current, next)  当current与于AtomicInteger中volatile修饰的value相等时  即旧值与当前的value相等时 才更新 不然一直自旋


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

        1. 多个put出现死循环

        jdk7 解决冲突时拉链法 新加入节点会加入链表头部
        https://coolshell.cn/articles/9606.html 死链  1.8已解决
        问题就在于 有两个线程要添加值 都出发了扩容 第一个线程完成扩容之后 链表的先后顺序发生了变化(因为是头插法)A-B变成B-A 而第二个线程中断前记录了A-B 所以移动A 之后 继续移动B 而B此时的后继是A 所以下一步又会移动A 造成了死循环A-B-A

        1.8解决死链  是因为对 resize做了优化
        因为在 JDK1.7 中采取的是头插法，遍历一个节点就插入一个节点到新的哈希桶数组，所以才会导致出现循环链表。但 JDK1.8 中是采用**两个头结点来保持旧链表的引用，直到该索引处对应的链表全部遍历完之后再分别把头结点放在新的哈希桶数组对应的位置。**而不是遍历一个节点就插入一个节点到新的哈希桶数组。所以不会出现死链。

        2.多线程的put导致元素的丢失

            1.put操作会执行 p.next = newNode(hash, key, value, null);  A线程put进keyA后 B线程再put进keyB p.next会与keyA断开与keyB连接 导致keyA丢失

        3. put和get并发时，可能导致get为null

            1.线程1执行put时，因为元素个数超出threshold而导致rehash，会创建一个新的table，此时还未搬运元素，是空表。线程2此时执行get，得到null。

    6.blockingqueue
        当队列容器已满，生产者线程会被阻塞，直到队列未满；当队列容器为空时，消费者线程会被阻塞，直至队列非空时为止。
        使用ReentrantLock 创建两个条件变量 
  
 
# 读写锁
    1. ReentrantReadWriteLock 
    当读操作远远高于写操作时，这时候使用 读写锁 让 读-读 可以并发，提高性能。 类似于数据库中的 select ... from ... lock in share mode

 

## BlockingQueue  

    1.Thread1--put-->BlockingQueue---take-->thread2

        1.阻塞队列空时，take操作的线程会被阻塞
        2.阻塞队列满时，put操作的线程被阻塞
        3.之前 用的是synchronize和wait notify实现

    2.BlockingQueue好处：不用关心什么时候需要阻塞线程，什么时候需要唤醒线程，BlockingQueue包办

        1.换句话说 不用程序员来写wait和notify 

    3. BlockingQueue接口的实现类

        1.ArrayBlockingQueue  数组结构的有界阻塞队列
        2.LinkedBlockingQueue  链表结构的有界[大小默认为21亿 Integer.MAX_VALUE]阻塞队列

        3.SynchronousQueue  不存储元素的阻塞队列 即单个元素的队列

            1.每一个put操作必须要等待一个take操作 否则不能继续添加
            代码演示
    
    4.队列操作api

        1. 抛出异常组 [不满足边界时抛出异常]
            
            1.插入 add 移除 remove 检查element [判断空不空 返回队首元素]

        2. 返回布尔值 [不满足边界时 返回true或false]

            1.插入 offer[true或false ] 移除 poll [元素或null] 查看 peek
        
        3. 阻塞

            1.插入 put 满了会阻塞 
            2.移除 take  空了会阻塞 
        
        4. 超时

            1.offer(e,time,unit)  满了的话阻塞2s
            2.poll(e,time,unit)   

    5. 使用场景

        1.生产者消费者模式 

            1.传统版
                1.sync wait notify
                2.lock await signal
            
            2.口诀
            
                1.线程 操作(方法) 资源类   该变量就是资源类  操作应该为资源类自带

                2.判断 干活 通知    wait方法都放在while循环里面

                3.防止虚假唤醒机制  wait方法要用在while循环中

            3.阻塞队列版本    D:\文档\后端学习之路\Java多线程\Java多线程代码\src\BlockingQueue

                1.[MyResource中 构造器中参数都是传接口 不传类  为了适配]

                2.不自己写wait notify 了  自己控制的只有 FLAG 是否开启生产

## 使用多线程的四种方式 [123加线程池]

    1.继承Thread类

    2.实现runnable接口

    3.实现callable<V>接口      D:\文档\后端学习之路\Java多线程\Java多线程代码\src\CallableDemo.java

        1.带返回值 类型为V

        2.会带有异常

        3.构造方法
            1.如何构造？ Thread类中并没有传callable接口的构造方法

            2.适配器模式 若一个类实现了runnable接口和并和callable扯上关系 可以传入  [面向接口编程] 

            3.RunnableFuture接口继承了runnable接口 FutureTask是RunnableFuture的一个实现类 它也实现了runnable接口
            
            同时FutureTask的有一个构造方法中传入了callable接口 

        4.调用futureTask.get() 得到返回值
    
        5.适用场景

            1.1000个线程 运行成功返回444 失败返回000 可以找出失败的

            2.比方说 main线程要算4个任务 某个任务耗时很长 main线程就会新开一个线程去算这个任务 最后再用FutureTask.get()把结果总和 这个 FutureTask.get() 会阻塞

            可以用一个自旋锁的思想 等FutureTask算完了 再执行主线程 while (!futureTask.isDone()) { 等待 }
        
        6. 一个futureTask只会算一次 把同一个futureTask放到两个线程中 不会重复计算 需要再new一个新的futureTask



## 线程池
    1.自定义线程池 
        是一个生产者消费者模型 threadpool是消费者 task是生产者 
        thread用worker类封装 线程池的线程执行完一个任务后会找队列中的任务
        继续执行
        当thread可用数量不够时，把task放入blockingqueue  blockingqueue和threadpool都需要线程加锁 
        超时阻塞添加
        当队列满时，把方法抽象出一个接口，不写死在线程池中，由调用者来决定
        rejectpolicy

    1.线程池的特点 

        1. 线程复用  2.控制最大并发数  3.管理线程
    
    2.线程池的优点
        
        1.降低资源消耗 通过重复利用已创建的线程降低线程创建和销毁造成的消耗  [和spring思想类似 减少new对象带来的回收消耗]
        2.提高响应速度 当任务到达时，不需要等待线程创建就能直接执行
        3.提高线程的可管理性 线程是稀缺资源 如果无限制的创建 不仅会消耗系统资源 还会降低系统的稳定性 使用线程池可以进行统一的分配 调优和监控
    
    3. 接口Executor  工具类Executors  ExecutorService.execute()方法传入runnable来执行线程
        由工具类Executors来生成线程池

        1.一池5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);  

        2.一池1个处理线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        3.一池N个处理线程 由任务数量来决定
        ExecutorService threadPool = Executors.newCachedThreadPool();

    
    4. Executors创建线程池的底层实现是由线程池实现类 ThreadPoolExecutor

    5.七大参数

        1.corePoolSize 常驻核心线程数

            1.当线程池中的线程数目达到corePoolSize后，会把到达的任务放到缓存队列中去
        
        2.maximumPoolSize 线程池能够容纳同时执行的最大线程数 此值必须大于等于1

            1.当缓存队列的任务放满之后，会创建救急线程 此时线程总数就是maximumPoolSize
            
            2.原缓存队列中的任务去救急线程 新来的线程去缓存队列
        
        3.keepAliveTime  多余的空闲线程的存活时间

            1.创建救急线程之后，若空闲时间到达该值 则销毁到只剩下corePoolSize个数

        4.TimeUnit unit   keepAliveTime的单位

        5.workQueue 阻塞队列

        6.threadFactory 线程工厂   用来创建线程 一般默认 

        7.RejectedExectionHandler 拒绝策略  [maximumPoolSize都满了 使用拒绝策略]
    
    6.底层工作原理

        1.任务提交进来，先看corePoolSize是否满 若满 再看缓存队列是否满 若满 再看线程池是否满 若满 按照拒绝策略执行

        2.线程完成任务 会去队列中取
    
    7.拒绝策略

        1.AbortPolicy[默认] 直接抛出异常阻止系统正常运行
        2.CallerRunsPolicy 将某些任务回退到调用者   例如让main线程去执行 
        3.DiscardOldestPolicy 抛弃队列中等待最久的任务 
        4.DiscardPolicy 直接丢弃 不处理也不抛出异常
    
    8.工作中Executors提供的线程池 一个都不能用 必须使用自定义的

        1.newFixedThreadPool和 newSingleThreadExecutor允许请求队列长度为Integer.MAX  会堆积大量请求 导致OOM

        2.newCachedThreadPool 和SchedeledThreadPool 允许创建的线程数量为Integer.MAX 会创建大量的线程 导致OOM
    
    9. 线程池配置合理的线程数

        1.CPU密集型 即该任务需要大量运算 没有阻塞 配置尽可能少的线程数量
            公式：CPU核数 + 1个线程的线程池

        2.IO密集型  即有大量阻塞  单线程会浪费时间在等待上
            1.尽可能配置多 如CPU核数*2
            2.参考公式 CPU核数/(1-阻塞系数)   阻塞系数一般为0.8-0.9
   