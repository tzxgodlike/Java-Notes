## 微服务

    1.概念
        将单一的应用程序划分为一组小的服务，运行在独立的进程中，服务于服务间采用轻量级的通信机制相互协作
        每个服务都围绕具体的业务独立构建，并且能够被独立的部署到生产环境中。
    2.功能
        1.服务注册与发现
            1.eureka 已停更
            2.zookeeper
            3.consul
            4.nacos
        2.服务调用
            1.Ribbon 为来会被取代
            2.LoadBalancer
            3.Feign会被OpenFeign取代
        3.服务熔断
        4.负载均衡
        5.服务降级
            1.Hystrix 会被停更
            2.resilience4j国外
            3.Sentinel 国内
        6.服务消息队列
        7.配置管理中心
            1.Config
            2.apollo 携程
            3.nacos 阿里
        8.服务网关
            1.Zuul  被取代
            2.gateway
        9.服务监控
        10.全链路追踪
        11.自动化部署
        12.服务定时任务调度操作

        服务总线 bus被nacos取代
    3.springcloud是分布式微服务的一站式解决方案
    4.版本选择 
        1.springcloud用A-Z命名版本 不同版本对应不同springboot
        2.start.spring.io/actuator/info 查看json串返回版本对应信息
        3.版本选择 2.2.2.RELEASE + Hoxton.SR1
        4.mysql-java用5版本可适配mysql5和8

## springcloud父工程

    1.步骤
        1.父工程名字
            maven-[maven-archetype-site]-com.tzx.springcloud.cloud2020
        2.maven版本
            3.5.2
        3.工程名字
        4.字符编码
            约定>配置>编码
            fileencoing 三个设置utf-8
        5.注解生效激活
            settings-annotation processors-enable annotation processing
        6.JAVA编译版本选8
        7.filetype过滤
    
    2.POM
        1.父工程的pom.xml中加入<packaging>pom</packaging>
        2.删掉src文件夹
        3.粘贴pom
        4. <!--dependencyManagement一般用在父工程子模块继承之后，提供作用：锁定版本+子module不用写groupId和version 会往上找
            直到找到有dependencyManagement的pom-->
            <dependencyManagement>只是声明依赖 并不实现引入 所以子项目需要显示的声明需要的依赖
            这样修改的话，只需要修改父pom 如果子模块想用别的 可以再子Pom覆盖
    
    3.跳过maven test
        IDEA右边maven 点下闪电图标 
    
    4.父工程创建完成执行mvn:install将父工程发布到仓库中方便子工程继承

## 微服务模块构建步骤

    1.建module
    2.改pom
    3.写yml
    4.主启动
    5.业务类
        1.建表SQL
        2.ENTITES
        3.DAO
        4.service
        5.controller
    6.测试

## 支付模块和订单模块

    1.客户端消费者 port:80 order
    2.微服务提供者 port:8001 payment
   
## 构建支付模块
    cloud2020-cloud-provider-payment8001
    1.创建子模块cloud-provider-payment8001 观察父pom多了一个子模块
    2.  使用lomback创建实体类 注解实现setget 全参空参
        @Data                        
        @AllArgsConstructor
        @NoArgsConstructor
    3.实体类继承serializable 分布式会用到
    4.Json封装体 CommonResult 和前端传输的类
    5.dao接口上最好用@Mapper 
    6.@Param用来给参数命名 对应sql语句中的参数名
    7.写mapper.xml 细节 <resultMap>中 主键用<id > 非主键用<result  >
        <insert id="creat" parameterType="Payment" useGeneratedKeys="true" keyProperty="id">
        这句中useGeneratedKeys="true" keyProperty="id" 是把数据库中新增对象的主键赋给Java对象中的id
    8.service实现类上加@service
    9.service实现类中会引用dao 上面加@AutoWired[按类型注入]或者@Resource[按名字注入]
    10.Controller类中加@RestController
    11.在Controller中给前端返回的是约定好的CommonResult
    12.由于是对数据库的写操作 所以用@PostMapping(value = "/payment/creat") 等价于@RequestMapping(value = "/payment/creat",method = RequestMethod.POST)
    13.读数据库操作可以用浏览器 写数据库操作一般用postman工具 因为浏览器一般不支持post操作
    14.controller会把传来的参数放入对象中再传到方法中 ？ 需要复习springmvc
    15.模块多了 IDEA会使用run DashBoard

## 热部署
    1.添加devtools依赖到子模块中
        '''
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        '''
    2.添加插件到pom.xml中
    '''
    <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
    </build>
    '''
    3.开启自动编译选项 bulid-compiler 全打勾
    4.注册
        ctrl+shif+alt+/   选registy 
        compiler.automake.allow.when.app.running  打勾
        actionSystem.assertFocusAccessFromEdt 打勾

## 构建订单模块
    cloud-consumer-order80
    1.Port设置为80 因为80是默认的 顾客访问时不需要加端口号
    2.订单模块只有controller层 调用支付模块的dao service层
    3.创建相同的实体类 entities
    4.restTemplate提供多重便捷访问远程HTTP服务的方法  使用(url,requestMap,ResponseBean.class)代表rest请求地址 请求参数 HTTP响应转换被转换的对象
    5.config类  @Configuration
         @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
    6.弹幕说@LoadBalance这个注解加入注册中心时 80访问url改成服务名时 一定要加
    7.controller中调用restTemplate.postForObject 就相当于模拟了一个http://localhost:8001/payment/create请求
    8.@GetMapping
    9.运行报错 没有配置数据源 注释掉pom中的mysql jdbc等依赖即可
    10. get数据库成功 create数据库失败 可以插入数据 但是值为null
        因为restTemplate传递的数据是json 因此在支付模块接受时 要用@RequestBody 成功
    
## 工程重构
    1.两个模块都有实体类 重复
    2.新建模块 cloud-api-commons
    3.cn.hutool
    4.删除各自模块的实体类
    5. mvn clean再install 然后在模块的pom中引用该依赖
    6.

