## Ribbon是什么

    1.客户端的负载均衡的工具 也就是说在80order模块上
    2.负载均衡
        1.将用户请求分摊到多个服务上 从而达到系统的高可用[HA]
        2.和Nginx负载均衡的区别
            1.[集中式LB]
                Nginx是服务端负载均衡 客户端所有请求都会交给服务端 然后由Nignx实现转发请求
                相当于进医院 告诉你进哪个科
            2.[本地式LB]
                Ribbon是本地负载均衡，在调用微服务接口时，会在注册中心上获取注册信息服务列表之后
            缓存到JVM本地，从而在本地实现RPC远程服务调用技术
                相当于找到科之后 告诉你找哪个大夫
    3.负载均衡+RestTemplate调用
    4.一个软负载均衡的客户端组件

## 负载均衡

    1.工作步骤
        1.先选择EurekaServer，它优先选择同一个区域内负载较少的server
        2.根据用户指定的策略，在从server取到的服务注册列表中选择一个地址
    
    2.pom中eureka-clinet依赖包中已经有ribbon 所以不需要再次引用

    3.RestTemlate的使用
        1.getForEntity和getForObject区别
            1.getForObject返回的对象为响应体中数据转化的对象 可以理解为json
            2.getForEntity为ResponseEntity对象 包含了响应头、状态码、响应体等信息
    
    4.负载规则

        1.核心组件IRule 
            1.有七种自带的策略  轮询 随机 等
            2.如何替换
        
        2.
            1.
            Springboot主启动类的@SpringBootApplication中有@ComponentScan 所以会扫描com.tzx.springcloud下的所有包
            官方文档明确给出了警告：
            这个自定义配置类不能放在 @CommpomentScan 所扫描的当前包下以及子包下，
            否则我们自定义的这个规则类会被所有的 Ribbon 客户端共享，达不到特殊定制化的目的。

            2.新建com.tzx.myrule.MyselfRule
                @Bean
                public IRule myRule() {
                    return new RandomRule(); //定义为随机
                }
            3.在主启动类上加@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class) name值必须和eureka服务中心的一致[大写]
    
    5.原理
        1.轮询算法：rest接口第几次请求数%服务器集群总数量 = 实际调用服务器位置下标 每次服务重新启动rest接口计数 从1开始
        2.源码
            1.private int incrementAndGetModulo(int modulo) {
                int current;
                int next;
                do {
                    current = this.nextServerCyclicCounter.get();
                    next = (current + 1) % modulo;
                } while(!this.nextServerCyclicCounter.compareAndSet(current, next));  //如果current还没被改变 就把nextServerCyclicCounter更新为update

                return next;
            }
            有一个自旋锁 nextServerCyclicCounter是原子整数 把当前调用的服务器位置下标存在里面
        3.手写
            1.在8001 8002controller中加一个获得serverPort的函数 上面@GetMapping(value = "/payment/lb")
            2.com.tzx.springcloud.config.ApplicationContextConfig中restTemplate去掉@LoadBalance 保证使用的负载均衡是自己写的
            3.创建接口LoadBanlancer 方法 实现类MyLB 不要忘了加@@Component 把类对象加入容器
            4.实现一个自增器 并通过DiscoveryClient的serviceinstance来获取要去访问的服务是哪个
            5.return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class); 去获取8001的serverPort
            6.注意：
                @Component加入容器后 使用时不要忘了@Resource或者@AutoWired注入

