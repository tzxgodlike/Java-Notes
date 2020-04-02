## 应用场景

1.异步通信和服务解耦
    注册流程中把邮箱确认从流程中解耦
    https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/data-communication/message-queue.md

2.接口限流和消息分发

3.业务延迟处理

4.登录成功写入日志
    1.登录模块和日志模块异步

项目代码

C:\Users\Lenovo\IdeaProjects\itcast-rabbitmq

## 五种消息模型 [简单 工作 fanout direct topic]

    1.简单模式
        1.生产者-消费者  队列可以存储在内存或者磁盘中
        2.ACK概念
            // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, true, consumer);消费者出现异常，还是会接受消息
        // 把上面第二个参数置为false 手动进行ACK 在消费函数执行末尾加一句
        channel.basicAck(envelope.getDeliveryTag(), false);
        若有异常 不会拿出消息
    2.工作模型
        1.创建工作队列，在多个工作者之间分配耗时任务 以免内存中积累过多任务导致溢出
        2.设计两个消费者，一个处理快，一个处理慢 若无设置 两个消费者会处理同样多的消息
        // 设置每个消费者同时只能处理一条消息
        channel.basicQos(1);
        即一个消费者处理完一个消息后才能拿下一个 这样处理快的就会拿更多的任务
        3.特点： 
            1.消费者集群
            2.能者多劳
            3.一个消息只被一个消费者消费
    
    3.pub/sub 发布/订阅模型

        1.生产者发送消息给交换机exchange 交换机再决定把消息发送给哪个消息队列 每个消费者
        都有自己对应的消息队列 只能从自己队列中获得消息 [类似于一跟网线练一个计算机，
        通过交换机可以练多个计算机上网]

        2.交换机类型  交换机只负责转发 不负责存储

            一个交换机 多个队列 一个channel创建一个队列 一个队列可以绑定多个key 
            
            1.广播 fanout
                1.send创建了交换机 recv创建队列并绑定交换机 若先启动send 由于交换机不会存储
            消息，所以第一条消息会被丢弃 

            2.定向 direct [路由模式]
                1.队列与交换机的绑定不再是任意的 而是要指定一个routingKey
                2.消息发送方再向交换机发送消息时，也需要指定routingKey

            3.通配符 topic模式
                1.队列在绑定交换机时使用通配符
                2. # 匹配一个或多个单词
                   * 不多不少匹配一个词

## 持久化
    1.如何避免消息丢失
        1.使用ACK
    2.但如果再消费之前MQ宕机，消息就没了
    3.消息持久化durable的前提  需要将队列 exchange都持久化
    channel.exchangeDeclare(EXCHANGE_NAME, "topic"，true); //加个参数 true
    把队列声明时持久化也改成true
    之后再消息发布时，加入MessagePropertites.PERSISIENT_TEXT_PLAIN参数
    4.创建持久化的exchange 要删除之前同名的未持久化的exchange

## springAMOP
    1.listener 作为消费者
    2.生产者 AmqpTemplate对象 使用convertAndSend将消息转为为二进制
     参数为(exchange,key,message)

## 实现数据同步

    1.数据库增删改查产生消息 静态页面和其他服务接收消息进行修改
    2.在service层 数据新增或删改时 用AmqpTemplate发送消息 参数有key
    注意用trycatch 避免异常引发数据库事务回滚
    3.出现异常往上抛 ACK由spring的AOP实现 
    4.消费者用@RabbitListener
          