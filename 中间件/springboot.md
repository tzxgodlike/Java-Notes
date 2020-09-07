## JAVA CONFIG技术

    1.代替springmvc中的web.xml的配置内容

    2.创建一个类实现WebApplicationInitializer接口

        1.web容器会在启动时区调用onStartUp()方法 会传入一个ServletContext[web上下文对象] 

        2.ServletContext可以实现组件的注册

        3.onStartUp()方法中 new一个基于注解的spring上下文环境对象ac  然后ac.register(AppConfig.class)

        4.AppConfig.class是继承WebMvcConfigurationSupport类 并加了@Configuration [声明自己是一个配置类]  在里面可以配置@Bean对象

        5.需要注册DispatcherServlet 

            1.new一个DispatcherServlet servlet = new DispatcherServlet(ac) 对象

            2.使用onStartUp()传入的ServletContext对象的addservlet("app",servlet)

            3.最后把setloadOnStartup(1) 并addMapping("*.do")


## 为什么tomcat会调用spring写的 WebApplicationInitializer 接口的onStartUp()方法？   [难点]

    1.servlet3.0版本以后提出的新规范SPI

    2.如果spring的项目里面有某些方法需要在启动的时候被tomcat调用的话，首先在spring项目的根目录的META-INF/services目录下建立一个文件

    3.在org-...-spring-web项目的META-INF/services目录找到这个文件 文件名是实现了名为文件内容接口的实现类的名字   [spi机制 service provider interface] 

        1.这个接口[ServletContainerInitializer]是servlet规范的接口  tomcat中也实现了这个接口 所以tomcat和spring就产生了关联

        ....复杂


## springboot启动过程

    1. @SpringBootApplication包括三个注解：
    
        @EnableAutoConfiguration：启用 SpringBoot 的自动配置机制
        @ComponentScan： 扫描被@Component (@Service,@Controller)注解的bean，注解默认会扫描该类所在的包下所有的类。
        @Configuration：允许在上下文中注册额外的bean或导入其他配置类

    2. 在run方法调用之前，也就是构造SpringApplication的时候会进行初始化的工作，初始化的时候会做以下几件事：

        1.把参数sources设置到SpringApplication属性中，这个sources可以是任何类型的参数。

        2.判断是否是web程序，并设置到webEnvironment这个boolean属性中。 

        3.找出所有的初始化器，默认有5个，设置到initializers属性中 。

        4.找出所有的应用程序监听器，默认有9个，设置到listeners属性中 。

        5.找出运行的主类(main class) 。
