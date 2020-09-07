## 服务概念

    1.服务治理
        管理服务与服务之间的依赖关系 可以实现服务调用，负载均衡，容错等，实现服务发现与注册。
    2.服务注册
        1.系统架构
            service consumer对应订单系统 service provider对应支付系统[集群] Eureka Server[注册中心集群] 组成三角形

        2.系统中其他的微服务使用Eureka的客户端连接到Eureka Server并维持心跳连接 可以监控系统中各个微服务是否正常运行

        3.服务启动时，把信息以别名的方式注册到注册中心上 消费者以别名的方式去注册中心上获取服务通讯地址 
           再实现本地RPC调用

    3.Eureka的两个组件
        1.EurekaServer提供服务注册
        2.EurekaClient通过注册中心访问
            是一个Java客户端 ，具备一个内置的使用轮询负载算法的负载均衡器 在应用启动后 会向Server发送心跳(默认30秒)
            如果server在多个心跳周期内没有接受到某个节点的心跳，Server会在中心把这个服务节点移除(90秒)
    
## 单机Eureka搭建

    1.IDEA生成eurekaServer服务注册中心 [类似物业公司] cloud-eureka-server7001
    2.pom依赖18版和20版不一样
    3.写application.yml
    4.@EnableEurekaServer 写在主启动类上 代表这是eureka服务注册中心

    5.支付微服务入驻服务中心 eurekaClient端把支付模块注册成功服务提供者provider 类似学校对外提供授课服务
        1.引入eureka-client依赖
        2.修改yml
        3.主启动类上添加@EnableEurekaClient
        4.微服务名称就是application.yml中的spring-application-name
        5.eureka中红色字符是自我保护机制

    6.把cloud-consumer-order80注册进成为服务消费者 类似上课的同学
        1.改pom
        2.yml中添加spring-application-name 和 eureka信息

    7.过程
        1.启动注册中心 2.启动服务提供者payment 3.支付服务启动后会把自身信息注册 4.消费者order服务在需要调接口时 
        使用服务别名去注册中心获取实际的RPC远程调用地址 5.消费者获得调用地址后 底层实际利用HttpClient技术实现远程调用
        6.消费者获得服务地址会缓存到本地JVM内存中 默认每隔30秒更新一次地址
    
    8.实质：存key服务名 取value调用地址
        


## 集群Eureka搭建

    1.实现负载均衡 故障容错
    2.原理：互相注册 相互守望 7001和7002和7003相互注册
    3.步骤：
        1.新建cloud-eureka-server7002 .
        2. pom
        3.yml 
            1.先去C:\Windows\System32\drivers\etc\hosts 
                添加127.0.0.1     eureka7001.com
                    127.0.0.1     eureka7002.com
            2.hostname不再是loaclhost 分别改成eureka7001.com eureka7002.com
            3.7002的defaultZone改成7001: http://eureka7001.com:7001/eureka/ 7001一样
            4.浏览器访问eureka7001.com:7001成功 若失败 关闭chrome代理设置为直接连接

## 微服务注册到eureka集群

    1.将支付模块注册
        1.yml defaultZone设置为两个url

## 支付模块集群 [订单模块直接调用服务]

    1.创建cloud-provider-payment8002
    2.复制各种文件  spring-application-name不变
    3.如何知道访问时集群中的哪个模块？
        1.在8001的controller类中添加  来获取被访问的模块的端口号 server.port
            @Value("${server.port}")
            private String serverPort;
        2.8002同样
        
    4.测试
        1.80订单模块中 payment_url中写死了是访问8001 需要修改 直接访问微服务的名称
        String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
        2.会出bug 因为不知道是8001还是8002来提供服务 所以在RestTemplate那里加@LoadBalance实现负载均衡
        3.再测试 实现了轮询的负载均衡机制   [之后会学Ribbon]
    
    5.整合之后 直接调用服务 不用再关心地址和端口号[之前是在order的controller中写死了访问8001:] 而且服务也具有负载均衡功能

## actuator微服务信息完善
    
    1.spring-boot-starter-web和spring-boot-starter-actuator是标配 最好一起导入依赖
    2.主机名称：服务名称修改 
        1.在8001的yml中的eureka下添加
            instance:
                instance-id: payment8001
        2.修改完后 eureka中心下CLOUD-PAYMENT-SERVICE的status中不再会看到主机名
            1.没修改的80是 localhost:cloud-order-service:80
            2.修改完的8001是 payment8001
    3.在instance-id下加一行
        prefer-ip-address: true      #访问路径可以显示IP
        再把鼠标放在eureka中心下CLOUD-PAYMENT-SERVICE的status中的服务上 会看到IP
    

## 服务发现Discovery   [DiscoveryClient]

    1.对于注册进eureka中的微服务，可以通过服务发现来获得该服务的信息
    2.8001中修改cloud-provider-payment8001中的controller  注入DiscoveryClient对象 
    3.主启动类上加@EnableDiscoveryClient
    4.测试

## eureka自我保护机制
    
    1.某时刻一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存
    2.属于CAP理论的AP分支 [保障分区容错性和高可用性]
    3.场景：
        1.默认情况下，90秒服务中心没有收到微服务实例的心跳，Eureka会注销该实例
        但是当网络分区故障发生(延时、卡顿、拥挤时)微服务与注册中心之间无法正常
        通信。那此时不应该注销这个服务[因为服务本身是健康的]
        2.自我保护机制，当短时间内丢失过多客户端时，节点进入自我保护模式。
        3.宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。
    
    4.禁止自我保护
        1.在eurekaserver7001的yml中添加
        server:
        #禁止自我保护
            enable-self-preservation: false
            #心跳间隔时间2s
            eviction-interval-timer-in-ms: 2000