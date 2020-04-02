## GateWay概念

    1.Gateway是在Spring 生态系统之上构建的API网关服务，基于Spring 5，SpringBoot 2和Project Reactor等技术。
    Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能，例如：熔断、限流、重试等。

    2.Spring Cloud Gateway使用的是Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架

    3.一般企业架构 
        外部请求--->负载均衡(Nginx)--->网关--->微服务

    4.功能
        反向代理
        鉴权
        流量控制
        熔断
        日志监控
    
    5.特性
        基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
        动态路由：能够匹配任何请求属性
        可以对路由指定 Predicate（断言）和Filter（过滤器）
        集成Hystrix的断路器功能
        集成Spring Cloud 的服务发现功能
        易于编写的Predicate（断言）和Filter（过滤器）
        请求限流功能
        支持路径重写

    6.Zuul1模型
        zuul 1.x模型

        SpringCloud中所集成的Zuul版本，采用的是Tomcat容器，使用的是传统的Servlet IO处理模型。

        Servlet生命周期？
        servlet 由 servlet container 进行生命周期管理
        container 启动时构造 servlet 对象并调用 servlet init() 进行初始化；
        container 运行时接受请求，并为每个请求分配一个线程（一般从线程池中获取空闲线程）然后调用service()；
        container 关闭时调用 servlet destory() 销毁servlet；

        缺点:
        servlet是一个简单的网络IO模型，当请求进入servlet container时，servlet container就会为其绑定一个线程，在并发不高的场景下这种模型是适用的。但是一旦高并发（比如用jmeter压测），线程数量就会涨，而线程资源代价是昂贵的（上下文切换，内存消耗大）严重影响请求的处理时间。在一些简单业务场景下，不希望为每个request分配一个线程，只需要1个或几个线程就能应对极大并发的请求，这种业务场景下servlet模型没有优势

    7.Gateway模型

        Gateway支持 Reactor 和 WebFlux

        传统的Web框架，比如说：struts2，springmvc等都是基于Servlet API与servlet容器基础之上运行的。
        但是Servlet3.1之后有了异步非阻塞的支持，而WebFlux是一个典型非阻塞异步的框架，它的核心是基于Reactor的相关API实现的。相对与传统的Web框架来说，它可以运行在诸如Netty，Undertow及支持Servlet3.1的容器上。非阻塞式+函数式编程

        Spring WebFlux 是 Spring 5.0引入的新的响应式框架，区别于Spring MVC，它不需要依赖Servlet API，它是完全异步非阻塞的，并且基于 Reactor 来实现响应式流规范

## 三大核心概念 

    1.路由 Route 路由是构建网关的基本模块，它由ID、目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由

    2.断言 Predicate 参考的是Java8的java.util.function.Predicate
    开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数)，如果请求与断言相匹配则进行路由

    3.过滤 Filter 指的是Spring框架中GatewayFilyter的实例，使用过滤器，可以在请求被路由前或者之后进行修改
    匹配方式就叫断言，实现这个匹配方式就叫filter，对外表现出来就是路由的功能。

    4.web 请求，通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。
    predicate 就是我们的匹配条件，而filter，就可以理解为一个无所不能的拦截器。有了这两个元素再加上目标uri，就可以实现一个具体的路由。

    5.工作流程

        1.客户端向Spring Cloud Gateway发出请求。然后在Gateway Handler Mapping中找到与请求相匹配的路由，将其发送到Gateway Web Handler。

        2.Handler再通过指定的过滤器链来讲请求发送到我们实际的服务执行业务逻辑，然后返回。
        过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（“pre”）或之后（“post”）执行业务逻辑
        
        3.Filter 在 “pre” 类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等；在 “post” 类型的过滤器中可以做响应内容、响应头的修改，日志的输出，流量监控等，有着非常重要的作用

    6.核心逻辑  ：  路由转发+执行过滤链

## 入门配置 

    1.新建cloud-gateway-gateway9527
    2.pom yml 主启动类
    
    3.路由映射
        1.看cloud-provider-payment8001的controller访问地址
        2.不想暴露8001端口 希望在8001外面套一层9527

        3.在9527yml中新增配置 
            spring:
                cloud:
                    gateway:
                    routes:
                        - id: payment_routh #payment_routh    #路由的ID，没有固定规则但要求唯一，简易配合服务名
                        uri: http://localhost:8001         #匹配后提供服务的路由地址
                        predicates:
                            - Path=/payment/get/**          #断言，路径相匹配的进行路由

                        - id: payment_routh2 #payment_routh   #路由的ID，没有固定规则但要求唯一，简易配合服务名
                        uri: http://localhost:8001          #匹配后提供服务的路由地址
                        predicates:
                            - Path=/payment/lb/**             #断言，路径相匹配的进行路由
    
        4.启动 7001 8527 8001
            1.有个bug  gateway不需要web和actuator依赖 移除 
            2.添加网关前 访问http://localhost:8001/payment/get/31
            添加网关后 访问http://localhost:9527/payment/get/31

            3.gateway根据配置里面的断言 判断为TRUE 则可以访问  不是暴露真实的端口
    
    4，网关配置两种方法
        1.yml配置
        2.代码中注入RouteLocator 的Bean
            1.Config中写
    
    5. 和之前Ribbon负载均衡的区别

        1.之前80客户端发送请求访问8001/8002，通过ribbon负载均衡，将请求分散，现在服务提供者如果是多台，
        就需要将ribbon替换为gateway，只暴露gateway，客户端请求统一发到gateway9527，gateway将请求转发给8001/8002。
        

    6.通过微服务名实现动态路由       [之前9527只能对应8001 现在可以对应微服务名下面的多个]

        1.默认情况下Gateway会根据注册中心注册的服务列表，以注册中心上微服务名为路径创建动态路由进行转发，从而实现动态路由的功能。
        
        2.启动7001 8001 8002

        3.修改9527pom 注入进eureka client 
            修改yml 
            1.需要注意的是uri的协议为lb，表示启用Gateway的负载均衡功能。
            lb://serviceName是springcloud gateway在微服务中自动为我们创建的负载均衡uri。
        
        4.测试 访问http://localhost:9527/payment/lb  实现负载均衡轮询


## 常用Predicate

    1. After....  在某某时间之后访问才有效
    测试类T2 美国时间转为上海时间

    2.Cookie 
        1.- Cookie=username,tzx
        2.cmd 打开 使用curl发送HTTP请求  
            1.不带cookie  curl http://localhost:9527/payment/lb   返回404
            2.带cookie  curl http://localhost:9527/payment/lb --cookie "username=tzx"  返回8001或者8002 
        
    3.请求头 Header=X-Request-Id, \d+    #要求请求头有X-Request-Id属性且值为整数的正则表达式
    4.方法 Method
    5.Path
    6.Qurey   Query=username, \d+   curl http://localhost:9527/payment/lb?username=31

    7.Predicate就是为了实现一组匹配规则，让请求过来找对应的Route进行处理


## Filter

    1.概念
        路由过滤器可用于修改进入的HTTP请求和返回的HTTP响应，路由过滤器只能指定路由进行使用
    
    2.SpringCloud Gateway的 Filter，生命周期有 pro 和 post，
    种类有GatewayFilter [单一的]和 GlobalFilter[全局的]

    3.自定义过滤器
        1.类MyLogGateWayFilter implements GlobalFilter, Ordered
        2.exchange相当于 request response那些东西 chain 相当于过滤链
        3.功能 判断请求是否带了 uname 
    
    4.其实filter可以代替Predicate   
        1.Predicate拦截到的报404  filter可以自定义状态码