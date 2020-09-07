## Netty介绍

    Netty 是由 JBOSS 提供的一个 Java 开源框架。Netty 提供异步的、基于事件驱动的网络应用程序框架，用以快速开发高性能、高可靠性的网络 IO 程序

    Netty 可以帮助你快速、简单的开发出一个网络应用，相当于简化和流程化了 NIO 的开发过程

    Netty 是目前最流行的 NIO 框架，Netty 在互联网领域、大数据分布式计算领域、游戏行业、通信行业等获得了广泛的应用，知名的 Elasticsearch 、Dubbo 框架内部都采用了 Netty。


## Netty优点

    Netty 对 JDK 自带的 NIO 的 API 进行了封装，解决了上述问题。
    设计优雅：适用于各种传输类型的统一 API 阻塞和非阻塞 Socket；基于灵活且可扩展的事件模型，可以清晰地分离关注点；高度可定制的线程模型 - 单线程，一个或多个线程池.

    使用方便：详细记录的 Javadoc，用户指南和示例；没有其他依赖项，JDK 5（Netty 3.x）或 6（Netty 4.x）就足够了。
    高性能、吞吐量更高：延迟更低；减少资源消耗；最小化不必要的内存复制。

    安全：完整的 SSL/TLS 和 StartTLS 支持。
    社区活跃、不断更新：社区活跃，版本迭代周期短，发现的 Bug 可以被及时修复，同时，更多的新功能会被加入

## 线程处理模型

    1.传统阻塞IO模型

        1.特点
            采用阻塞IO模式获取输入的数据
            每个连接都需要独立的线程完成数据的输入，业务处理,数据返回
        2.缺点  
            当并发数很大，就会创建大量的线程，占用很大系统资源
            连接创建后，如果当前线程暂时没有数据可读，该线程会阻塞在read 操作，造成线程资源浪费

    2. Reactor模式  见课件图 P67

        1.基于IO复用模型
            多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象等待，无需阻塞等待所有连接。当某个连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理Reactor 对应的叫法: 1. 反应器模式 2. 分发者模式(Dispatcher) 3. 通知者模式(notifier) [三种叫法一个意思]

        2.基于线程池复用线程资源 
            不必再为每个连接创建线程，将连接完成后的业务处理任务分配给线程进行处理，一个线程可以处理多个连接的业务。

    3. I/O 复用结合线程池，就是 Reactor 模式基本设计思想

        1.Reactor 模式，通过一个或多个输入同时传递给服务处理器的模式(基于事件驱动)
        
        2.服务器端程序处理传入的多个请求,并将它们同步分派到相应的处理线程， 因此Reactor模式也叫 Dispatcher模式
        
        3.Reactor 模式使用IO复用监听事件, 收到事件后，分发给某个线程(进程), 这点就是网络服务器高并发处理关键

    4.核心
        Reactor：Reactor 在一个单独的线程中运行，负责监听和分发事件，分发给适当的处理程序来对 IO 事件做出反应。 它就像公司的电话接线员，它接听来自客户的电话并将线路转移到适当的联系人；

        Handlers：处理程序执行 I/O 事件要完成的实际事件，类似于客户想要与之交谈的公司中的实际官员。Reactor 通过调度适当的处理程序来响应 I/O 事件，处理程序执行非阻塞操作
    

##  Reactor三种模式

    1.单Reactor单线程  图P71
        1.流程
            Select 是前面 I/O 复用模型介绍的标准网络编程 API，可以实现应用程序通过一个阻塞对象监听多路连接请求
            Reactor 对象通过 Select 监控客户端请求事件，收到事件后通过 Dispatch 进行分发
            如果是建立连接请求事件，则由 Acceptor 通过 Accept 处理连接请求，然后创建一个 Handler 对象处理连接完成后的后续业务处理
            如果不是建立连接事件，则 Reactor 会分发调用连接对应的 Handler 来响应
            Handler 会完成 Read→业务处理→Send 的完整业务流程
        
        2.和NIO很像

        3. 优缺点分析  [简而言之 就是处理某个连接上的请求时不能同时处理新来的连接 而且每个线程的任务是排队处理的 ]

            优点：模型简单，没有多线程、进程通信、竞争的问题，全部都在一个线程中完成

            缺点：性能问题，只有一个线程，无法完全发挥多核 CPU 的性能。Handler 在处理某个连接上的业务时，整个进程无法处理其他连接事件，很容易导致性能瓶颈
            缺点：可靠性问题，线程意外终止，或者进入死循环，会导致整个系统通信模块不可用，不能接收和处理外部消息，造成节点故障

            使用场景：客户端的数量有限，业务处理非常快速，比如 Redis在业务处理的时间复杂度 O(1) 的情况

    
    2. 单reactor多线程  图P74 [相比单线程 多个handler处理可以让处理客户端请求的业务剥离出来 用一个新线程解决 之后再把结果异步的返回给handler ]

        1. 流程  [重点： handler不再负责处理事件 只把数据传给线程池中的线程去完成 然后回调结果 这样多个客户端的业务请求不会排队 而是并发处理]

            Reactor 对象通过select 监控客户端请求事件, 收到事件后，通过dispatch进行分发
            如果建立连接请求, 则右Acceptor 通过accept 处理连接请求, 然后创建一个Handler对象处理完成连接后的各种事件
            如果不是连接请求，则由reactor分发调用连接对应的handler 来处理
            handler 只负责响应事件，不做具体的业务处理, 通过read 读取数据后，会分发给后面的worker线程池的某个线程处理业务
            worker 线程池会分配独立线程完成真正的业务，并将结果返回给handler
            handler收到响应后，通过send 将结果返回给client  
            
            ！！！[这里有个误区，会认为一个handler要等待线程回调结果 再去send 其实并不是 handler把数据给线程之后 就继续执行下一个客户端的业务请求了 相当于并发
                   之后handler收到结果 会生成相应的key写事件 通过相应的channel再传回去]

        2. 优缺点分析
            优点：可以充分的利用多核cpu 的处理能力
            缺点：多线程数据共享和访问比较复杂， reactor 处理所有的事件的监听和响应，在单线程运行， 在高并发场景容易出现性能瓶颈.
            [ ]

    3.主从 Reactor 多线程   图P76 [相当于2中加了一层  主连接 从业务]

        1.流程  [主线程 MainReactor负责连接  子Reactor负责处理业务 子Reactor有多个]

            Reactor主线程 MainReactor 对象通过select 监听连接事件, 收到事件后，通过Acceptor 处理连接事件
            当 Acceptor  处理连接事件后，MainReactor 将连接分配给SubReactor 
            subreactor 将连接加入到连接队列进行监听,并创建handler进行各种事件处理
            当有新事件发生时， subreactor 就会调用对应的handler处理
            handler 通过read 读取数据，分发给后面的worker 线程处理
            worker 线程池分配独立的worker 线程进行业务处理，并返回结果
            handler 收到响应的结果后，再通过send 将结果返回给client
            Reactor 主线程可以对应多个Reactor 子线程, 即MainRecator 可以关联多个SubReactor

        2.优缺点

            优点：父线程与子线程的数据交互简单职责明确，父线程只需要接收新连接，子线程完成后续的业务处理。
            优点：父线程与子线程的数据交互简单，Reactor 主线程只需要把新连接传给子线程，子线程无需返回数据。
            缺点：编程复杂度较高

    
    4.reactor模式优点

        响应快，不必为单个同步时间所阻塞，虽然 Reactor 本身依然是同步的
        可以最大程度的避免复杂的多线程及同步问题，并且避免了多线程/进程的切换开销
        扩展性好，可以方便的通过增加 Reactor 实例个数来充分利用 CPU 资源
        复用性好，Reactor 模型本身与具体事件处理逻辑无关，具有很高的复用性
    

## Netty模型

    1. 简单模型 进阶模型 详细模型   P80 81 82 

    2. 详细版
        Netty抽象出两组线程池 BossGroup 专门负责接收客户端的连接, WorkerGroup 专门负责网络的读写
        BossGroup 和 WorkerGroup 类型都是 NioEventLoopGroup
        NioEventLoopGroup 相当于一个事件循环组, 这个组中含有多个事件循环 ，每一个事件循环是 NioEventLoop
        NioEventLoop 表示一个不断循环的执行处理任务的线程， 每个NioEventLoop 都有一个selector , 用于监听绑定在其上的socket的网络通讯
        NioEventLoopGroup 可以有多个线程, 即可以含有多个NioEventLoop
        每个Boss NioEventLoop 循环执行的步骤有3步
        轮询accept 事件
        处理accept 事件 , 与client建立连接 , 生成NioScocketChannel , 并将其注册到某个worker NIOEventLoop 上的 selector 
        处理任务队列的任务 ， 即 runAllTasks
        7) 每个 Worker NIOEventLoop 循环执行的步骤
        轮询read, write 事件
        处理i/o事件， 即read , write 事件，在对应NioScocketChannel 处理
        处理任务队列的任务 ， 即 runAllTasks
        8) 每个Worker NIOEventLoop  处理业务时，会使用pipeline(管道), pipeline 中包含了 channel , 即通过pipeline 可以获取到对应通道, 管道中维护了很多的 处理器


    3.Netty快速入门案例  [nio/simple]

        1.每个子线程NioEventLoop都有自己的selector

        2. ChannelHandlerContext 上下文中究竟包含什么

            1

    4. 任务队列taskqueue      [如果NettyServerHandler中channelRead会耗时很久 则需要异步执行]

        1.用户程序自定义的普通任务 
        
            [举例说明]   在NettyServerHandler的channelRead自定义一个耗时的业务 

            1. taskqueue中的任务不能并发执行 是顺序执行！！！

        2.用户自定义定时任务 [scheduleTaskQueue]

        3.非当前 Reactor 线程调用 Channel 的各种方法     [NettyServer中initChannel方法] 
        例如在推送系统的业务线程里面，根据用户的标识，
        找到对应的 Channel 引用，然后调用 Write 类方法向该用户推送消息，就会进入到这种场景。最终的 Write 会提交到任务队列中后被异步消费
    
    5. 异步模型  [Future-Listener  先返回个future 再去监听结果]
        
        1.异步的概念和同步相对。当一个异步过程调用发出后，调用者不能立刻得到结果。实际处理这个调用的组件在完成后，通过状态、通知和回调来通知调用者。
        2.Netty 中的 I/O 操作是异步的，包括 Bind、Write、Connect 等操作会简单的返回一个 ChannelFuture。
        3.调用者并不能立刻获得结果，而是通过 Future-Listener 机制，用户可以方便的主动获取或者通过通知机制获得 IO 操作结果
        4.Netty 的异步模型是建立在 future 和 callback 的之上的。callback 就是回调。重点说 Future，它的核心思想是：假设一个方法 fun，计算过程可能非常耗时，等待 fun返回显然不合适。那么可以在调用 fun 的时候，立马返回一个 Future，后续可以通过 Future去监控方法 fun 的处理过程(即 ： Future-Listener 机制)

        5. 代码演示

            1.给ChannelFuture注册监听器
            2.sync方法会让主线程等待cf异步方法执行完毕

## 快速入门 HTTP服务  [netty.http]

    1.简单的HTTP响应
    2.过滤HTTP请求

    3. 刷新一次HTTP请求 会产生新的管道和handler对应 因为HTTP连接用完就断掉[短链接]
 
## Netty核心模块
    
    1.Bootstrap serverbootstrap 
        1.Bootstrap 意思是引导，一个 Netty 应用通常由一个 Bootstrap 开始，主要作用是配置整个 Netty 程序，串联各个组件，Netty 中 Bootstrap 类是客户端程序的启动引导类，ServerBootstrap 是服务端启动引导类

        2.handler() 是对应bossgroup  childHandler()对应workergroup
    
    2.Future ChannleFuture 

        1.Netty 中所有的 IO 操作都是异步的，不能立刻得知消息是否被正确处理。但是可以过一会等它执行完成或者直接注册一个监听，具体的实现就是通过 Future 和 ChannelFutures，他们可以注册一个监听，当操作执行成功或失败时监听会自动触发注册的监听事件
        2.Channel channel()，返回当前正在进行 IO 操作的通道
        ChannelFuture sync()，等待异步操作执行完毕

    3.Channel 

        1. Netty 网络通信的组件，能够用于执行网络 I/O 操作。
            通过Channel 可获得当前网络连接的通道的状态
            通过Channel 可获得 网络连接的配置参数 （例如接收缓冲区大小）

        2.  Channel 提供异步的网络 I/O 操作(如建立连接，读写，绑定端口)，异步调用意味着任何 I/O 调用都将立即返回，并且不保证在调用结束时所请求的 I/O 操作已完成
            调用立即返回一个 ChannelFuture 实例，通过注册监听器到 ChannelFuture 上，可以 I/O 操作成功、失败或取消时回调通知调用方

    4.Selector
        
        1.Netty 基于 Selector 对象实现 I/O 多路复用，通过 Selector 一个线程可以监听多个连接的 Channel 事件。
        当向一个 Selector 中注册 Channel 后，Selector 内部的机制就可以自动不断地查询(Select) 这些注册的 Channel 是否有已就绪的 I/O 事件（例如可读，可写，网络连接完成等），这样程序就可以很简单地使用一个线程高效地管理多个 Channel 

    5.ChannelHandler

        1.ChannelHandler 是一个接口，处理 I/O 事件或拦截 I/O 操作，并将其转发到其 ChannelPipeline(业务处理链)中的下一个处理程序。
        ChannelHandler 本身并没有提供很多方法，因为这个接口有许多的方法需要实现，方便使用期间，可以继承它的子类

        2.SimpleChannelInboundHandler中可以用泛型表示 接受数据的对象  而ChannelInboundHandler只能用object包装

    6.Pipeline 和 ChannelPipeline

        1.ChannelPipeline 是一个 Handler 的集合，它负责处理和拦截 inbound 或者 outbound 的事件和操作，相当于一个贯穿 Netty 的链。(也可以这样理解：ChannelPipeline 是 保存 ChannelHandler 的 List，用于处理或拦截 Channel 的入站事件和出站操作)

        2.ChannelPipeline 实现了一种高级形式的拦截过滤器模式，使用户可以完全控制事件的处理方式，以及 Channel 中各个的 ChannelHandler 如何相互交互

        3.addFirst addLast

    7.ChannelHandlerContext

        1.保存 Channel 相关的所有上下文信息，同时关联一个 ChannelHandler 对象
        即ChannelHandlerContext 中 包 含 一 个 具 体 的 事 件 处 理 器 ChannelHandler ， 同 时ChannelHandlerContext 中也绑定了对应的 pipeline 和 Channel 的信息，方便对 ChannelHandler进行调用.

    8.ChannelOption

        1.
    
    9.EventLoopGroup

        1.EventLoopGroup 是一组 EventLoop 的抽象，Netty 为了更好的利用多核 CPU 资源，一般会有多个 EventLoop 同时工作，每个 EventLoop 维护着一个 Selector 实例。

        2.EventLoopGroup 提供 next 接口，可以从组里面按照一定规则获取其中一个 EventLoop来处理任务。在 Netty 服务器端编程中，我们一般都需要提供两个 EventLoopGroup，例如：BossEventLoopGroup 和 WorkerEventLoopGroup。

        3.通常一个服务端口即一个 ServerSocketChannel对应一个Selector 和一个EventLoop线程。BossEventLoop 负责接收客户端的连接并将 SocketChannel 交给 WorkerEventLoopGroup 来进行 IO 处理
    
    10.Unpooled

        1.Netty 提供一个专门用来操作缓冲区(即Netty的数据容器)的工具类

        2.public static ByteBuf copiedBuffer(CharSequence string, Charset charset)

        3.[案例演示] netty.buf

            1.创建一个对象 该对象包含一个数组arr 是一个byte[10]
            2. 不需要使用flip
            3. 因为底层维护了 readindex 和writeindex
                0~readindex 已经读取的区域
                readindex~writeindex 可读的区域
                writeindex~capcity  可写的区域

## Netty群聊

    1.群聊  使用channelGroup 保存所有channel
    2.私聊 使用hashmap 管理
 

## Netty心跳机制案例  [netty/heartbreak]

    1. 有时候客户端断开连接 服务端并不能感知到

    2. 逻辑
        1.服务端启动后，等待客户端连接，客户端连接之后，向服务端发送消息。如果客户端在“干活”那么服务端必定会收到数据，
        如果客户端“闲下来了”那么服务端就接收不到这个客户端的消息，既然客户端闲下来了，不干事，那么何必浪费连接资源呢？
        所以服务端检测到一定时间内客户端不活跃的时候，将客户端连接关闭。

    3.实现心跳检测 客户端断开重连


## Netty websocket长连接

    1.Http协议是无状态的, 浏览器和服务器间的请求响应一次，下一次会重新创建连接.

    2.要求：实现基于webSocket的长连接的全双工的交互

    3. 是通过101状态码升级为ws协议

    4.HTTP长连接： 伪长连接
      ws ： 真长连接


        HTTP1.1通过使用Connection:keep-alive进行长连接，HTTP 1.1默认进行持久连接。在一次 TCP 连接中可以完成多个 HTTP 请求，但是对每个请求仍然要单独发 header，Keep-Alive不会永久保持连接，它有一个保持时间，可以在不同的服务器软件（如Apache）中设定这个时间。这种长连接是一种“伪链接”
        websocket的长连接，是一个真的全双工。长连接第一次tcp链路建立之后，后续数据可以双方都进行发送，不需要发送请求头。
    
        keep-alive双方并没有建立正真的连接会话，服务端可以在任何一次请求完成后关闭。WebSocket 它本身就规定了是正真的、双工的长连接，两边都必须要维持住连接的状态。


## 编码与解码

      1.java 自带 诸多缺点
      
      2. Google发布的protoBuf [读protou]  适合做数据存储或者RPC数据交换格式

        1.目前很多公司http+json  转成tcp+protobuf 效率变高

        2.使用 protobuf 编译器能自动生成代码，Protobuf 是将类的定义使用.proto 文件进行描述。
        说明，在idea 中编写 .proto 文件时，会自动提示是否下载 .ptotot 编写插件. 可以让语法高亮。

        然后通过 protoc.exe 编译器根据.proto 自动生成.java 文件

      3. 实例1 [netty/codec]    [报错的话 改编译器版本]

        1.具体 看老师演示步骤
        第1步：在Maven 项目中引入 Protobuf 坐标，下载相关的jar包

        pom.xml中 

        <dependencies>

            <dependency>
                <groupId>com.google.protobuf</groupId>
                <artifactId>protobuf-java</artifactId>
                <version>3.6.1</version>
            </dependency>

        </dependencies>

        2.第 2 步： 编写proto 文件 Student.proto


        syntax = "proto3"; //版本
        option java_outer_classname = "StudentPoJO"; //设置生成的Java类

        //内部类的名称，是真正的PoJo 类
        message Student{ // message 的规定的
        int32 id = 1; //PoJo 类的属性数据类型类型和 序号(不是属性值)
        string name = 2;
        }

        第 3 步：通过 protoc.exe 根据描述文件生成 Java 类，具体操作如下所示
        说明：protoc-3.6.1-win32 是从网上下载的 google 提供的文件.

        

        C:\Users\Administrator\Desktop\Netty资料\我的\资料\protoc-3.6.1-win32\bin>protoc.exe --java_out=. Student.proto



        生成了 
        StudentPoJO.java

        这里主要是看两点:
        DO NOT EDIT!
        public  static final class Student extends
            com.google.protobuf.GeneratedMessageV3 implements // 说明真正的PoJo 类是Student

        第四步：把生成的 StudentPoJo.java 拷贝到自己的项目中打开，如下图所示

        第 5 步：在 Netty 中使用

    
    4. 实例2 

        1.客户端发送多钟对象， 服务端判断是哪种
    

## Netty入站和出站机制

    1. 解码器是入站 编码器是出站  [把客户端和服务端方看成站就行]

    2. 解码器 ByteToMessageDecoder

        1.这个例子，每次入站从ByteBuf中读取4字节，将其解码为一个int，然后将它添加到下一个List中。当没有更多元素可以被添加到该List中时，它的内容将会被发送给下一个ChannelInboundHandler。int在被添加到List中时，会被自动装箱为Integer。在调用readInt()方法前必须验证所输入的ByteBuf是否具有足够的数据

## Netty的handler调用机制        [ 服务器把数据writeandflush之后并不会直接发送到客户端  而是发送到下一个handler 其他的handler可以修改发送的内容]

    1.案例  客户端发送long -> 服务器服务端发送long -> 客户端  netty.inboundandoutboundhandler

    2. 服务端 客户端 都需要编码器和解码器

    3. 
        1.发送123456L成功

        2.发送abcdabcdacbdacbd字符串
byte[] buffer = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(buffer);

        ** ！！！！ 重要 **
        
        3. 说明   [所以decode方法中不需要while 用的是if   还挺智能的  ]

            list集合把解码后得数据 传给下一个handler
        
            decode方法会被调用多次 直到没有新的元素被添加到list  或者bytebuf中没哟更多的可读字节
        
            同时list将数据给下一个handler的业务处理方法也会被调用相同的次数

            list中数据是什么类型 下一个handler中的泛型就是什么类型


    4. 业务逻辑的handler都是inbound

    5. inbound和outbound都可以加入管道 但是调用的时候 会分成两条路径 不会同时生效

    6.不论解码器handler 还是 编码器handler 即接收的消息类型必须与待处理的消息类型一致，否则该handler不会被执行

    在解码器 进行数据解码时，需要判断 缓存区(ByteBuf)的数据是否足够 ，否则接收到的结果会期望结果可能不一致

## 解码器-ReplayingDecoder   [不需要判断数据是否足够读取]
    
    1.public abstract class ReplayingDecoder<S> extends ByteToMessageDecoder

        ReplayingDecoder扩展了ByteToMessageDecoder类，使用这个类，我们不必调用readableBytes()方法。参数S指定了用户状态管理的类型，其中Void代表不需要状态管理

        应用实例：使用ReplayingDecoder 编写解码器，对前面的案例进行简化 [案例演示]

        ReplayingDecoder使用方便，但它也有一些局限性：

        并不是所有的 ByteBuf 操作都被支持，如果调用了一个不被支持的方法，将会抛出一个 UnsupportedOperationException。
        ReplayingDecoder 在某些情况下可能稍慢于 ByteToMessageDecoder，例如网络缓慢并且消息格式复杂时，消息会被拆成了多个碎片，速度变慢

## 整合log4j到netty

    1.在Maven 中添加对Log4j的依赖 在 pom.xml

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.25</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.25</version>
            <scope>test</scope>
        </dependency>


    2.配置 Log4j , 在 resources/log4j.properties


## TCP粘包拆包

    1.TCP是面向连接的，面向流的，提供高可靠性服务。收发两端（客户端和服务器端）都要有一一成对的socket，因此，发送端为了将多个发给接收端的包，更有效的发给对方，使用了优化方法（Nagle算法），将多次间隔较小且数据量小的数据，合并成一个大的数据块，然后进行封包。这样做虽然提高了效率，但是接收端就难于分辨出完整的数据包了，因为面向流的通信是无消息保护边界的

    2.由于TCP无消息保护边界, 需要在接收端处理消息边界问题，也就是我们所说的粘包、拆包问题, 看一张图

        1.  假设客户端分别发送了两个数据包D1和D2给服务端，由于服务端一次读取到字节数是不确定的，故可能存在以下四种情况：
            服务端分两次读取到了两个独立的数据包，分别是D1和D2，没有粘包和拆包

            服务端一次接受到了两个数据包，D1和D2粘合在一起，称之为TCP粘包

            服务端分两次读取到了数据包，第一次读取到了完整的D1包和D2包的部分内容，第二次读取到了D2包的剩余内容，这称之为TCP拆包

    3. 案例 [netty/tcp]

        1，客户端循环发数据来 服务端会可能分多次接受 

        2. 但是服务端回复消息 客户端总是一次接受 把服务端回复消息加一个休眠1s 会发现客户端也变成多次接受
    
    4. 解决方案

        1.使用自定义协议 + 编解码器 来解决

        2.关键就是要解决 服务器端每次读取数据长度的问题, 这个问题解决，就不会出现服务器多读或少读数据的问题，从而避免的TCP 粘包、拆包 。

        3.具体案例  [netty/tcp]

            1. 要求客户端发送 5 个 Message 对象, 客户端每次发送一个 Message 对象
            2. 服务器端每次接收一个Message, 分5次进行解码， 每读取到 一个Message , 会回复一个Message 对象 给客户端.

                1.先定义协议包

                2.解码器的decode方法会自动调用多次 把buf中的数据解码完

                3.定义解码器 解码器一次读取一个Message对象  放入list 然后传给serverhandler处理 
    
## 源码分析  [暂时跳过]



## Netty实现RPC

    1.RPC（Remote Procedure Call）— 远程过程调用，是一个计算机通信协议。该协议允许运行于一台计算机的程序调用另一台计算机的子程序，而程序员无需额外地为这个交互作用编程

    2.两个或多个应用程序都分布在不同的服务器上，它们之间的调用都像是本地方法调用一样(如图)

    3.步骤
        1.服务消费方(client)以本地调用方式调用服务
        client stub 接收到调用后负责将方法、参数等封装成能够进行网络传输的消息体
        client stub 将消息进行编码并发送到服务端
        server stub 收到消息后进行解码
        server stub 根据解码结果调用本地的服务
        本地服务执行并将结果返回给 server stub
        server stub 将返回导入结果进行编码并发送至消费方
        client stub 接收到消息并进行解码
        服务消费方(client)得到结果

    4. 公有接口 public interface HelloService
    
    4. 服务器端  provider

        1. HelloServiceImpl实现类  
           ServerBootstrap  启动一个服务提供者

           NettyServer
           NettyServerHandler
    
    5.客户端 customer

        1.ClientBootstrap类  启动一个消费者 NettyClient

          NettyClientHandler

        2. 核心就是设计一个代理对象  [设计模式 ]