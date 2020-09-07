## 介绍

    1.微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，
    因此系统中会出现大量的服务。由于每个服务都需要有配置信息才能运行，所以一套集中式的、
    动态的配置管理设施是必不可少的。SpringCloud提供了ConfigServer来解决这个问题。

    2.SpringCloud Config分为服务端和客户端两部分

        1.服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端
        提供获取配置信息，加密/解密信息等访问接口

        2.客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候
        从配置中心获取和加载配置信息，配置服务器默认采用git来存储配置信息，这样就有助于对环境
        配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。
    
    3.功能
        集中管理配置文件
        不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release
        运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
        当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
        将配置信息以REST接口的形式暴露，post/curl访问刷新即可

    4.与github配合使用

## 搭建Config配置总控中心 [服务端 3344]

    1.github新建仓库cloud-config 然后git clone到本地 C:\Users\Lenovo\IdeaProjects\springcloud-config\cloud-config 添加三个yml文件

    2.创建cloud-config-center-3344模块  Pom spring-cloud-config-server  yml   主启动类加注解 @EnableConfigServer

    3.windows下修改hosts文件 增加映射  127.0.0.1	  config-3344.com       这样访问config-3344.com:3344就可以访问127.0.0.1:3344

    4.访问 config-3344.com:3344/master/config-dev.yml  访问github成功

    5.读取配置文件规则

        1./{label}/{application}-{profile}.yml label是分支[master/dev] 

        2. ...

## 搭建Config客户端 [3355]

    1.新建cloud-config-center-3355
    2.pom spring-cloud-starter-config
    3.创建bootstrap.yml  它的优先级比application.yml更高 
        1.bootstrap.yml与3344相连 所以会先从3344读取配置 再读自己的配置
        2.#Config客户端配置
            config:
                label: master #分支名称
                name: config #配置文件名称
                profile: dev #读取后缀名称 上述3个综合：master分支上config-dev.yml的配置文件被读取 http://config-3344.com:3344/master/config-dev.yml
                uri: http://localhost:3344 #配置中心地址 表示通过这个服务端访问
            
    4.因为config-server是以REST接口的形式暴露配置信息下，所以我们可以用rest风格读取配置信息。
    通过读取配置文件中的config.info来判断配置文件是否已经读取成功。因为在git仓下的config-dev.yml文件中有config.info

    写个ConfigClientController

    意思就是把读取到的配置信息通过@Value("${config.info}")再读出来

    5.手动刷新 [改配置-post刷新]

        1.修改3355模块，引入actuator图形化监控 意思是说 我自己发生变化了能被别人监控到
        <!--PS：gateway是不能加actuator的-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
        2.修改yml，暴露监控端口
            #添加配置，暴露监控端点
                management:
                endpoints:
                    web:
                    exposure:
                        include: "*"
        3.业务类controller加@RefreshScope，刷新功能

        4.此时修改github信息 3344可以访问新值  3355还是旧值

        5.此时需要运维人员发送Post请求刷新3355，@RefreshScope的作用就是自动获悉刷新的内容
        必须是Post请求，使用curl命令，稍等一下，出现下面界面，激活3355  
        curl -X POST "http://localhost:3355/actuator/refresh"
    
    6.待解决问题

        1.
        假设有多个微服务客户端3355/3366/3377。。。每个微服务都需要执行一次post请求，可以写一个脚本，批量执行。但是还有没有更优化的方法？
        我们想大范围的自动刷新，可否广播？一次通知，处处生效？
        所以引入了SpringCloud Bus消息总线
