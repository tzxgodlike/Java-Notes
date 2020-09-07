## 概念

    1.Feign是一个WebService客户端，使用Feign能让编写WebService更加简单
    2.使用方法是定义一个服务接口，然后在上面添加注解
    3.利用Ribbon维护了Payment 的服务列表信息，并且通过轮询实现了客户端的负载均衡。
    而与Ribbon不同的是，通过feign 只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。
    4.意识是在80order模块直接调用8001的PaymentService接口 
    之前是80去调用8001的controller 
    5.接口+@FeignClient
    6.OpenFeign是springcloud在Feign的基础上支持了SpringMVC的注解，如@RequestMapping等等。
    OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，并通过动态代理

    7.@EnableFeignClients激活
      @FeignClient使用


## 服务调用

    1.创建cloud-consumer-feign-order80模块
    2.主启动类上加@EnableFeignClients激活
    3.创建PaymentFeignService接口 加@Component@FeignClient
    4.@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  
    接口中加8001controller层中的方法
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
    5.写controller层


    6.就是封装了Ribbon和RestTemplate
    RestTemplate需要传(uri+参数)  

    不再需要在confi中配置@LoadBalanced 的RestTemplate
    

    7.测试成功 并能实现负载均衡功能
    service和controller要放在springcloud包下 不然会失败

## 超时控制

    1. 支付业务需要3秒 而订单模块等不了的话 可以设置时间限制

    2.演示
        1.8001中加个暂停 paymentFeignTimeout() 会等待3s
        2.然后把接口加到80的service和controller中

        3.feign底层是ribbon 一般默认等待1s
        
        4.测试 8001访问成功 80访问会报超时错误

        5.在yml配置中设置超时时间 由feign自带的ribbon控制
            #设置feign 客户端超时时间（openFeign默认支持ribbon）
            ribbon:
                #指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间
                ConnectTimeout: 5000
                #指的是建立连接后从服务器读取到可用资源所用的时间
                ReadTimeout: 5000

## 日志打印

    1.功能
        Feign 提供了日志打印功能，可以通过配置来调整日志级别，从而了解 Feign 中 Http 请求的细节。
    说白了就是对接口的调用情况进行监控和输出   

    2.日志级别
        NONE：默认的，不显示任何日志
        BASIC：仅记录请求方法、URL、响应状态码及执行时间
        HEADERS：除了 BASIC 中定义的信息之外，还有请求和响应的头信息
        FULL：除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据
    
    3.操作
        1.新建一个config.FeignConfig @Configuration  使用FULL级别
        2.在yml添加
            logging:
                level: 
                    # 以什么级别监控哪个接口
                    com.tzx.springcloud.service.PaymentFeignService: debug
