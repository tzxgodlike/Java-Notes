## Spring

1.Spring IOC把创建对象的权利交给框架
<context:component-scan base-package="com.itheima"></context:component-scan> 创建容器要扫描的包
2.xml配置<bean> 来把对象加入到spring容器中 容器是map 有<key,value>
3.
    1.在bean.xml中设置要扫描注解的包
    2.@Component 把当前类创建一个对象加入容器中 并用value属性设置对象的Id（即map的key）
    3.根据使用层的不同 又分为@Service @Controller @Repository
    4.@AutoWired 按类型自动注入 如果容器中有多个该类型 再找id为变量名称的
    对象注入 
4.@Bean 把当前方法的返回值创建对象加入容器 key默认是当前方法的名称
    1.用注解配置方法时，若方法有对象做参数，spring会在容器中去查找有没有

5.AOP 不修改源码 对方法进行增强  使用动态代理技术
    1.使用动态代理 来让service的方法运行前后来运行TransactionManager的beginTransaction、commit(); rollback() release()等操作
    2.使用配置实现上述功能
    3.基于注解
        1.@Component("txManager")  
          @Aspect //声明为切面类
         public class TransactionManager 注解通知类
         在类中的方法上加
         @Before("execution(* com.itheima.service.impl.*.*(..))")
         beginTransaction(){}  //当该类中方法执行时提前执行before
         //提交事务 后置通知  
         @AfterReturning("execution(* com.itheima.service.impl.*.*(..))") 
         commit(){}
         //回滚事务  
         @AfterThrowing("execution(* com.itheima.service.impl.*.*(..))") 
         rollback(){}
         //@After("execution(* com.itheima.service.impl.*.*(..))") 最终通知
         release(){} 
        2.当com.itheima.service.impl.*.*(..))类中方法执行时 上述方法都会对应执行

6.事务 
    1.上面AOP中beginTransaction、commit(); rollback() release()都是在里面调用别的数据源的方法
    spring自己也提供了事务方法
    @Transactional
    2.该注解的属性和 xml 中的属性含义一致。该注解可以出现在接口上，类上和方法上。 出现接口上，
    表示该接口的所有实现类都有事务支持。 
    出现在类上，表示类中所有方法有事务支持 
    出现在方法上，表示方法有事务支持。 
    以上三个位置的优先级：方法>类>接口 
    加注解之后就无需自己写事务的各种方法了
    3.要配置事务管理器
    <bean id="transactionManager" 
class="org.springframework.jdbc.datasource.DataSourceTransactionManager">   <property name="dataSource" ref="dataSource"></property>  </bean> 
    4.也可以用注解配置 
        1.方法上标注 @Transactional
        2.EnableTransactionManager 开启事务的注解支持
        3.配置事务管理器 并加入容器@Bean
    5.五种隔离级别
        1.默认 [为以下四种其中一种]
        2.读未提交 [不可重复读 脏读 幻读] 能读到未提交的事务
        3.读已提交 [不可重复读 幻读]    只能读到提交的事务
        4.可重复读 [幻读]  事务开始后 不允许修改操作 但是可能有添加操作 出现幻读
        5.序列化   

7.动态代理技术
     
    1.对实现了接口的类的代理   JDK代理
    public void test1() {

        final UserDao userDao = new UserDaoImpl();
        // newProxyInstance的三个参数解释：
        // 参数1：代理类的类加载器，同目标类的类加载器
        // 参数2：代理类要实现的接口列表，同目标类实现的接口列表
        // 参数3：回调，是一个InvocationHandler接口的实现对象，当调用代理对象的方法时，执行的是回调中的invoke方法
        //proxy为代理对象
        UserDao proxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(), new InvocationHandler() {

                    @Override
                    // 参数proxy:被代理的对象
                    // 参数method:执行的方法，代理对象执行哪个方法，method就是哪个方法
                    // 参数args:执行方法的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("记录日志");
                        Object result = method.invoke(userDao, args);
                        return result;
                    }
                });
        //代理对象执行方法
        proxy.saveUser();
    }

    2.对没有实现类的代理  CGLib代理  实际上是生成了目标类的子类来增强
     public void test2() {
        final LinkManDao linkManDao = new LinkManDao();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(linkManDao.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 当你调用目标方法时，实质上是调用该方法
             * intercept四个参数：
             * proxy:代理对象
             * method:目标方法
             * args：目标方法的形参
             * methodProxy:代理方法
            */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                System.out.println("记录日志");
                 Object result = method.invoke(linkManDao, args);
                return result;
            }
        });
        // 创建代理对象
        LinkManDao proxy = (LinkManDao) enhancer.create();
        proxy.save();
    }

## springboot日志

    1.日志框架选择slf4j+logback
    2.使用
        1.不应该调用实现类 应该调用抽象层(slf4j)
        2.配置实现还是使用日志实现框架的配置文件
        3.系统中很多框架，使用的默认日志框架都不一样 需要整合 如何同一为slf4j？
            1.使用了适配器模式
            2.将系统中其他的日志框架先排除
            3.用中间包来替换原有的日志框架
            4.使用slf4j实现
        4.springboot能适配所有日志，底层实现了其他日志框架转化为slf4j 唯一需要做的
        就是在引入新框架时去掉默认的日志依赖[避免默认的日志包名和springboot的转换包名冲突，最新版本不需要去掉，因为转换包名变了]
    3.指定配置 在类路径下面放配置文件 就不会使用日志的默认配置