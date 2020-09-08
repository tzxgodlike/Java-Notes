## Bus
    1.实现分布式自动刷新配置  
    2.Bus配合Config 实现

    3.支持RabbitMQ Kafka

    4.总线
        在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个共用的消息主题，并让系统中所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，所以称它为消息总线。在总线上的各个实例，都可以方便的广播一些需要让其他连接在该主题上的实例都知道的消息。
    
    5.原理
    ConfigClient 实例都监听MQ中同一个topic（默认是springcloubus），当一个服务刷新数据的时候，它会把这个信息放入到Topic中，这样其他监听统一topic的服务就能得到通知，然后去更新自身的配置。

## RabbitMQ配置

    1.D:\文档\rabbitmq-server-windows-3.7.7\rabbitmq_server-3.7.7\sbin
    2.http://localhost:15672/     用户名tzx 密码 tzx

## 实现

    1.以3355为模板创建3366
    2.pom yml controller
    
    3.设计思想
        1.利用消息总线触发一个客户端/bus/refresh，而刷新所有客户端的配置
        2.利用消息总线触发一个服务端ConfigServer的/bus/refresh端点，而刷新所有客户端的配置

        3.
            3.2的架构显然更加合适，3.1不合适的原因如下：

            打破了微服务的职责单一性，因为微服务本身是业务模块，它本不应该承担配置刷新的职责
            破坏了微服务各节点的对等性
            有一定的局限性。例如，微服务在迁移时，它的网络地址常常会发生变化，此时如果想要做到自动刷新，那就好增加更多的修改

    4.3344服务端配置  [需要配置bus刷新的端点]
        <!--添加消息总线RbbitMQ支持-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bus-amqp</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>

        #rabbit相关配置
        rabbitmq:
            host: localhost
            port: 5672  
            username: guest
            password: guest

        #rabbitmq相关配置，暴露bus刷新配置的端点
        management:
        endpoints:  #暴露bus刷新配置的端点
            web:
            exposure:
                include: 'bus-refresh'  #凡是暴露监控、刷新的都要有actuator依赖，bus-refresh就是actuator的刷新操作
    
    5.3355 3366客户端配置
        #rabbit相关配置 15672是web管理界面的端口，5672是MQ访问的端口
        rabbitmq:
            host: localhost
            port: 5672
            username: guest
            password: guest	#这是客户端，不需要刷新
    
    6.启动 3344 3355 3355 7001 
        1.修改github上version为6
        2.运维发送 curl -X POST "http://localhost:3344/actuator/bus-refresh" 
        刷新3344[http://config-3344.com:3344/master/config-dev.yml]  之后3355[http://localhost:3355/configInfo] 3366读取的也是6

    7.打开RaabitMQ exchanges中有一个springCloudBus 


    8.定点通知 只通知3355 不通知3366
        1.公式 http://localhost:配置中心端口号/actuator/bus-refresh/{destination}
        2.curl -X -POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"    //通过微服务名加端口号
        3.修改github version为8 
        4.3355访问为8 3366访问为6
