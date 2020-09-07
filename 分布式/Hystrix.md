## 理论知识

    1.复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某个时候将不可避免的失败

    2.服务雪崩
        1.多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的 “ 扇出 ” 。
        如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A 的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的 “ 雪崩效应 ”。

        2.对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

        3.所以，通常当你发现一个模块下的某个实例失败后，这时候这个模块依然还会接受流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫 雪崩。
        要避免这样的级联故障，就需要有一种链路中断的方案：
        服务降级、服务熔断

    3.Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，已提高分布式系统的弹性。

    4. 断路器 本身是一种开关装置，当某个服务单元发送故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

## Hystrix重要概念

    1.服务降级 fallback
        1.服务器忙，请稍后再试，不让客户端等待并立刻返回一个友好提示
        2.发生情况：
            1.程序运行异常
            2.超时
            3.服务熔断出发服务降级
            4.线程池、信号量打满也会导致服务降级 

    2.服务熔断 break
        1.类似保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，
        然后调用[服务降级]的方法并返回友好提示
        2.步骤
            1.降级
            2.熔断
            3.恢复调用链路

    3.服务限流flowlimit
        1.秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行


## Hystrix支付微服务构建

    1.新建cloud-provider-hystrix-payment8001
    2.controller中增加两个业务逻辑 一个是正常访问 一个是需要等待3秒的访问
    3.高并发测试：
        1.D:\文档\64151-分布式中间件技术实战（Java版）_源代码+工具\源代码+工具\开发工具\apache-jmeter-5.1.1\apache-jmeter-5.1.1\bin\jmeter.bat
        2.20000个并发去访问timeout  此时一个访问ok也会出现卡顿
        3.因为他们处于同一个微服务中 tomcat的默认工作线程被打满了 没有多余的线程来分解压力
    

## Hystrix订单微服务调用支付服务

    1.新建模块cloud-consumer-feign-hystrix-order80
    2.pom yml
    3.写Paymentservice接口   
        1.加注解
        @Component
        @FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")  
        2.去8001的controller中复制方法

    4.写OrderController 

    5.配置ribbon超时时间 默认为1s 方法需要等待3s 所以要设置为5S

    6.测试出现bug 原因是因为 service和controller的方法中要获取请求参数作为方法参数 需要@PathVariable("id")

    7.高并发测试 有2万个线程访问8001 80再访问8001会出现故障

## 解决方法

    1.解决的要求
        1.超时导致的服务器变慢[转圈]     -----------超时不再等待
        2.出错                          -----------出错要有兜底
    
        2.
        对方服务(8001)超时了，调用者(80)不能一直卡死等待，必须有服务降级

        对方服务(8001)宕机了，调用者(80)不能一直卡死等待，必须有服务降级
        
        对方服务(8001)OK，调用者(80)自己出故障或有自我要求(自己的等待时间小于服务提供者)，自己处理降级
    
## 8001服务降级
    
    1.降级配置 @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler")

    2.8001先从自身找问题 设置自身调用超时时间的峰值 超时了需要兜底方法 作为服务降级fallback

    3.在会出现异常的方法上加@HystrixCommand并指定兜底的方法 paymentInfo_TimeoutHandler
        @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
        HystrixProperty表示该方法运行时间阈值的3S 超过3S就会降级
    
    4.新增一个注解 需要激活 在主启动类上加 @EnableCircuitBreaker

    5.测试会发现兜底方法和原方法不是使用的同一个线程池

    6.出现异常/或者超时 都是触发兜底方法paymentInfo_TimeoutHandler


## 80客户端服务降级

    1.对HystrixProperty的属性修改建议重启服务 热部署有时候失效

    2.yml中加
    #yml添加配置,开启 hystrix
    feign:
      hystrix:
        enabled: true

    3.主启动类上加@EnableHystrix    [这个和8001上加的 @EnableCircuitBreaker都能用]

    4.在controller中加@HystrixCommand及相应方法

    5.8001端方法运行3S返回 设置阈值5S 正常  但80端设置

## 出现的问题及解决

    1.代码膨胀 正常逻辑和错误逻辑的代码混在一块

    2.每个业务都要写一个兜底方法 有没有一个通用的？

    3.解决代码膨胀
        
        1.在类上加一个@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")

        2.普通方法只需加 @HystrixCommand 不需要指定具体方法

        3.写payment_Global_FallbackMethod方法
    
    4.抓主要矛盾 80调用某个微服务 必须通过一个接口 接口包含了该服务下的所有方法 直接在那配置默认的fallback方法
    意思就是现在客户端与服务端关系紧紧耦合，客户端能跑是因为接口调用了微服务的业务逻辑方法，我们如果针对客户端接口做一些处理，
    把它调用的所有微服务方法进行降级，就可以解决耦合问题。

    5.问题：客户端碰上服务端，碰上服务端宕机或者关闭

        1.本次案例服务降级处理是在客户端80实现完成，与服务端8001没有关系，只需要为Feign客户端定义的接口
        添加一个服务降级处理的实现类就可以实现解耦

        2.未来会面对的异常：超时 运行异常 宕机

        3.根据80已有的FeignClient对应的接口，重新新建一个类(PaymentFallbackService)实现该接口 统一为接口里面的方法进行异常处理  
        别忘了加@Component

        4.把PaymentFallbackService.class配到PaymentHystrixService的@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)中

        5.启动 80  访问http://localhost/consumer/payment/hystrix/ok/31 然后关掉8001 fallback测试成功



## 服务熔断

    1.熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，
    会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。
    当检测到该节点微服务调用响应正常后，恢复[调用链路]。

    2.在SpringCloud框架里，熔断机制通过Hystrix实现，Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是@HystrixCommand

    3.案例： 8001

        1.在service中添加方法paymentCircuitBreaker 和 paymentCircuitBreaker_fallback

        [hutool工具类的使用]

        2.paymentCircuitBreaker上加注解
         @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//短路多久之后开始尝试恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
             })

        意思就是在某段时间内[默认10S]达到10次请求 若有6次失败  就开启断路器 10S后尝试恢复

        3.测试 controller中加入方法 输入负数会发生异常 调用fallback方法 失败10次之后 即使输入整数 也会调用fallback方法

        4.close - half open - open
        断路器开启或关闭的条件

            1.当满足一定的阈值的时候（默认10秒内超过20个请求次数） [19次全错也不会触发断路器]
            2.当失败率达到一定的时候（默认10秒内超过50%的请求失败）
            3.到达以上阈值，断路器将会开启
            4.当开启的时候，所有请求都不会进行转发
            5.一段时间后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5。

        断路器打开之后

            1.再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。

            2.原来的主逻辑要如何恢复呢？
            对于这一问题，hystrix也为我们实现了自动恢复功能
            当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，熔断器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。
        
        5.@HystrixCommand中服务熔断的参数 存在文件夹中
    
## 服务限流

    1.使用线程隔离

    2.当我们依赖的服务是极低延迟的，比如访问内存缓存，就没有必要使用线程池的方式，那样的话开销得不偿失，而是推荐使用信号量这种方式

## Hystrix 图形化Dashboard搭建

    1.新建cloud-consumer-hystrix-dashboard9001
    2.主启动类上加@EnableHystrixDashboard
    3.所有微服务提供类都需要配置actuator
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    4.访问http://localhost:9001/hystrix成功

    5.9001监控8001  要图形化 8001依赖中一定要有web和actuator

    6.新版本Hystrix需要在主启动类中指定监控路径

    7.启动8001

    8.在DashBoard中填写stream http://localhost:8001/hystrix.stream

    9.看图 七种颜色对应七种错误 
    圈代表请求量  曲线代表2分钟的请求变化率
    


