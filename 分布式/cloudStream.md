## Stream为什么被引入

    1.如果系统里同时存在多种MQ，可以使用使用Cloud Stream，只需要和Stream交互就可以进行管理。
    一句话，屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型

    2.应用程序通过inputs和outputs[消费者生产者]来与stream中的binder对象交互
    通过配置来绑定 与消息中间件交互

    3.通过Spring Intergration来链接消息代理中间件以实现消息事件驱动

    引入了 [发布-订阅][消费组][分区]三个概念

    4.传统MQ
    生产者/消费者之间靠消息媒介传递信息内容——Message
    消息必须走特定的通道——消息通道MessageChannel
    消息通道里的消息如何被消费呢，谁负责收发处理——消息通道MessageChannel的子接口SubscribableChannel，由MessageHandler消息处理器所订阅

    5.cloudstream
        1.绑定器作为中间层 binder 对应exchange
        2..input对应生产者 output对应消费者

        3.发布-订阅模式

## 标准流程

    1.cloud-stream-rabbitmq-provider8801，作为生产者进行发消息模块
    cloud-stream-rabbitmq-consumer8802，作为消息接收模块
    cloud-stream-rabbitmq-consumer8803，作为消息接收模块

    2.创建cloud-stream-rabbitmq-consumer8801

        2.pom spring-cloud-starter-stream-rabbit  
        yml  [重要]  output 输出消息
        3.主启动类

        4.接口类 @EnableBinding(Source.class)    //定义消息的推送管道 

        5.启动8801 
            1.报错 c.r.c.impl.ForgivingExceptionHandler     : An unexpected connection driver error occured
                是因为rabbitmq的tzx用户权限不够  解决方法https://blog.csdn.net/caidingnu/article/details/90648569
            2.查看exchange 和yml中对应 
            3.访问http://localhost:8801/sendMessage

    3.创建cloud-stream-rabbitmq-consumer8802
        1.yml中output变为input
        2.cotroller  
            1.@StreamListener(Sink.INPUT)
            2.@Component 这里相当于@controller
            @EnableBinding(Sink.class) 实现监听
    
## 分组消费和持久化

    1.创建cloud-stream-rabbitmq-consumer8803  8801发送的消息会被8801  8802重复消费

    2.使用消费分组解决 
        1.在Stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次。
        不同组是可以全面消费的（重复消费），同一组内会发送竞争关系，只有其中一个可以消费

        2.在rabbitmq中看到studyexchange中有两条队列  类似于广播模式 

        3.默认的分组是不同的 要把8802 8803分到一个组

        4.分为两组 tzxA  tzxB

            1.修改yml group: tzxA

            2.查看studyexchange中队列名为studyExchange.tzxA  studyExchange.tzxB

            3.把8802 8803组都设为tzxA 8801发四条消息 分别被8802 8803消费两条
    
    3.其实就是设置了rabbitmq的queue 

    4.持久化

        关于自定义分组

        如果8802去掉分组，而8803不去掉，当8802/8803都关闭服务，8801这时候发送消息，8802再启动的时候不会重新获得未曾获得的消息并消费，而8803重启后会获得8801之前发送的消息并消费。

        所以group分组属性在消息重复消费和消息持久化消费 避免消息丢失是非常重要的属性

        就是[默认的分组不会保留未曾获得的消息，自定义的分组会保留]。
