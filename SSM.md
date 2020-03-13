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
    4.五种隔离级别
        1.默认 [为以下四种其中一种]
        2.读未提交 [不可重复读 脏读 幻读] 能读到未提交的事务
        3.读已提交 [不可重复读 幻读]    只能读到提交的事务
        4.可重复读 [幻读]  事务开始后 不允许修改操作 但是可能有添加操作 出现幻读
        5.序列化   