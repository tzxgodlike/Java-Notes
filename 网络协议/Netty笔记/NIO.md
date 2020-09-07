## 项目地址

    D:\文档\后端学习之路\网络协议\NettyProject

## 同步 异步 阻塞 非阻塞



1.同步与异步同步和异步关注的是消息通信机制 (synchronous communication/ asynchronous communication)所谓同步，就是在发出一个*调用*时，在没有得到结果之前，该*调用*就不返回。但是一旦调用返回，就得到返回值了。换句话说，就是由*调用者*主动等待这个*调用*的结果。而异步则是相反，*调用*在发出之后，这个调用就直接返回了，所以没有返回结果。换句话说，当一个异步过程调用发出后，调用者不会立刻得到结果。而是在*调用*发出后，*被调用者*通过状态、通知来通知调用者，或通过回调函数处理这个调用。典型的异步编程模型比如Node.js举个通俗的例子：你打电话问书店老板有没有《分布式系统》这本书，如果是同步通信机制，书店老板会说，你稍等，”我查一下"，然后开始查啊查，等查好了（可能是5秒，也可能是一天）告诉你结果（返回结果）。而异步通信机制，书店老板直接告诉你我查一下啊，查好了打电话给你，然后直接挂电话了（不返回结果）。然后查好了，他会主动打电话给你。在这里老板通过“回电”这种方式来回调。

2. 阻塞与非阻塞阻塞和非阻塞关注的是程序在等待调用结果（消息，返回值）时的状态.阻塞调用是指调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会返回。非阻塞调用指在不能立刻得到结果之前，该调用不会阻塞当前线程。还是上面的例子，你打电话问书店老板有没有《分布式系统》这本书，你如果是阻塞式调用，你会一直把自己“挂起”，直到得到这本书有没有的结果，如果是非阻塞式调用，你不管老板有没有告诉你，你自己先一边去玩了， 当然你也要偶尔过几分钟check一下老板有没有返回结果。在这里阻塞与非阻塞与是否同步异步无关。跟老板通过什么方式回答你结果无关。


3. 我的理解

    1.BIO中需要开多个线程之后，每个线程都会阻塞在read上 这样程序要不停的切换上下文 然后切换到该线程时 大概率是read阻塞的 浪费时间

    2. 在NIO中 没有accept阻塞了  只需要多路复用器去循环每个channel  看有没有事件到达 

    3. NIO中读写不阻塞 把读到的东西放在ByteBuffer中就直接返回


## BIO缺点
    缺点

        1. 每个请求要创建独立的线程 进行read 和 write
        2. 并发数较大时，会创建大量线程 占用资源
        3.  当前线程若没有数据读，会一直阻塞在read上 浪费资源

## NIO 与 BIO 比较

    1. BIO以流的方式处理数据， 而NIO以块的方式  效率高

    2. BIO是阻塞的 [在read时阻塞]  NIO非阻塞

    3. BIO基于字节流和字符流操作。 NIO基于CHANNEL和buffer 数据从管道中读取到缓冲区，或者从缓冲区写入通道
       [这样就可以不需要在read时阻塞，可以去做其他事，等到缓冲区积累一定数据再去取。]
      
       Selector用于监听多个管道的事件，因此可以实现单个线程监听多个客户端通道。
    
## BIO NIO AIO

    同步阻塞：到理发店理发，就一直等理发师，直到轮到自己理发。
    同步非阻塞：到理发店理发，发现前面有其它人理发，给理发师说下，先干其他事情，一会过来看是否轮到自己.
    异步非阻塞：给理发师打电话，让理发师上门服务，自己干其它事情，理发师自己来家给你理发

## NIO三大核心组件

    1.一个channel对应一个buffer

    2.一个selector对应一个线程。一个线程对应多个channel，多个channel注册到一个selector

    3.程序切换到哪个channel是由事件决定的

    4.selector会根据不同的事件，在各个通道上切换

    5.buffer是一个内存块。底层是一个数组

    6.数据的读取写入是通过buffer 这个和BIO [BIO要么是输入流要么是输入流 不能双向] 但是NIO是可以读也可以写 但是需要flip方法切换
                                                                                                  [flip方法是切换了指针头]
    7.channel是双向的，可以返回底层操作系统的情况，比如Linux底层的通道就是双向的


## Buffer

    1.buffer父类中定义了4个基本属性

        1.capacity  容量  初始化时定义 不能变
        2.limit     表示缓冲区当前的终点 不能对缓冲区超过极限的位置读写 这个极限是可以修改
        3.position  位置  表示下一个要被读或写的元素的索引
        4.mark      标记  表示是读状态还是写状态
    
    2.debug模式查看 flip函数之后 各属性的变化 及如何设置属性


## Channel

    1.NIO通道类似流  debug会发现是流中包装了channel

        1.通道可以同时读写 流不能
        2.通道可以异步读取数据
        3.通道可以从缓冲区中读写数据
    
    2.SeverSocketChannel  == SeverSocket
      SocketChannel       == socket

    3.FileChannel   文件IO
        1.read (Buffer dst)  从通道读取数据到缓冲区
        2.write (Buffer b)   把缓冲区的数据写到通道中
        3.transferFrom ()   transferTo()  从目标通道中复制数据 / 把数据从当前通道复制给目标通道

    4. 应用实例

        1.本地文件写数据
        2.本地文件读数据
        3.使用一个buffer完成文件的拷贝  [重要  复习]

            1.循环读取 
            2.每次读取前 clear  读取后 flip 
            
        4.用transferfrom 从通道拷贝文件到另一个通道
    

## 注意事项

    1.bytebuffer put时放入什么类型 取就要按什么类型来取   实例：NIOByteBufferPutGet

        1.原因是put时放入short 取得时候取LONG 会超过分配的内存 出现异常 

    2. bytebuffer可以设置为只读       buffer.asReadOnlyBuffer

    3. NIO提供MappedByteBuffer 让文件直接在内存（堆外内存）中进行修改   实例 ： MappedByteBufferTest

    4.NIO支持通过多个BUFFER(即buffer数组)完成读写操作 即scattering 和 gathering    实例：ScatteringAndGatheringTest


## Selector

    1.简介
        1.Java 的 NIO，用非阻塞的 IO 方式。可以用一个线程，处理多个的客户端连接，就会使用到Selector(选择器)

        2.Selector 能够检测多个注册的通道上是否有事件发生(注意:多个Channel以事件的方式可以注册到同一个Selector)，
        如果有事件发生，便获取事件然后针对每个事件进行相应的处理。这样就可以只用一个单线程去管理多个通道，也就是管理多个连接和请求。【示意图】


        3.只有在 连接/通道 真正有读写事件发生时，才会进行读写，就大大地减少了系统开销，并且不必为每个连接都创建一个线程，不用去维护多个线程

        4.避免了多线程之间的上下文切换导致的开销
    
    2.select()  
        selector.select()//阻塞
        selector.select(1000);//阻塞1000毫秒，在1000毫秒后返回
        selector.wakeup();//唤醒selector
        selector.selectNow();//不阻塞，立马返还

    3.原理
        
        1.当客户端连接时，会通过ServerSocketChannel 得到 SocketChannel

        2.Selector 进行监听  select 方法, 返回有事件发生的通道的个数.

        3.将socketChannel注册到Selector上, register(Selector sel, int ops), 一个selector上可以注册多个SocketChannel

        4.注册后返回一个 SelectionKey, 会和该Selector 关联(集合)

        5.进一步得到各个 SelectionKey (有事件发生)

        6.在通过 SelectionKey  反向获取 SocketChannel , 方法 channel()

        7.可以通过  得到的 channel  , 完成业务处理 
        8.代码撑腰。。。                             [代码] NIOServer   NIOClient
    

## SelectionKey

    1.表示 Selector 和网络通道的注册关系, 共四种

        int OP_ACCEPT：有新的网络连接可以 accept，值为 16
        int OP_CONNECT：代表连接已经建立，值为 8
        int OP_READ：代表读操作，值为 1 
        int OP_WRITE：代表写操作，值为 4
    

## 基于NIO的群聊系统

    1. 实现客户端和服务端简单数据通讯 (非阻塞)
    2. 实现多人群聊
    3. 服务端
        1.能够检测用户上线 离线 和消息转发
    4. 客户端
        1.通过channel无阻塞的发送消息给其他所有用户 同时可以接收
    

## NIO 与 零拷贝  [复习操作系统！！！！！！！！！！]

    1.零拷贝是网络编程的关键，很多性能优化都离不开。 零拷贝从操作系统角度，是没有cpu 拷贝


    2.在 Java 程序中，常用的零拷贝有 mmap(内存映射) 和 sendFile。那么，他们在 OS 里，到底是怎么样的一个的设计？我们分析 mmap 和 sendFile 这两个零拷贝

    3. 传统IO 见课件图  [把文件写到通道中]

        1.四次拷贝 三次切换   [用户态————>内核态————>用户态]
            1.硬件拷贝到内核态的buffer [DMA拷贝 直接内存拷贝]
            2.内核buffer拷贝到用户态的buffer   [cpu拷贝]
            3.用户buffer拷贝到内核态的socket buffer   [CPU拷贝]
            4.socket buffer拷贝到协议和协议栈         [DMA]


    4. mmap优化 [内存映射优化]

        1.将文件映射到内核缓冲区，同时用户空间可以共享内核空间的数据。这样进行网络传输时，可以减少内核拷贝到用户态这一次拷贝

        2.三次拷贝 三次切换
    
    5.sendFile 优化

        1.sendFile函数不经过用户态 直接从内核buffer进入socket buffer 减少了一次状态切换

        2.三次拷贝 两次切换

        3.Linux新版修改

            1.Linux 在 2.4 版本中，做了一些修改，避免了从内核缓冲区拷贝到 Socket buffer 的操作，直接拷贝到协议栈，从而再一次减少了数据拷贝。具体如下图和小结：

                这里其实有 一次cpu 拷贝
                kernel buffer -> socket buffer
                但是，拷贝的信息很少，比如
                lenght , offset , 消耗低，可以忽略

            2.两次拷贝 两次切换
    
    6. 零拷贝的再次理解

        1.我们说零拷贝，是从操作系统的角度来说的。因为内核缓冲区之间，[没有数据是重复的]（只有 kernel buffer 有一份数据）。

        2.零拷贝不仅仅带来更少的数据复制，还能带来其他的性能优势，例如更少的上下文切换，更少的 CPU 缓存伪共享以及无 CPU 校验和计算。

    7. NIO零拷贝传输案例  [nio/zerocopy]

