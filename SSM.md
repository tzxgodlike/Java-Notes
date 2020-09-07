## Spring

1.Spring IOC把创建对象的权利交给框架
<context:component-scan base-package="com.itheima"></context:component-scan> 创建容器要扫描的包
2.xml配置<bean> 来把对象加入到spring容器中 容器是map 有<key,value>
3.
    1.在bean.xml中设置要扫描注解的包
    2.@Component 把当前类创建一个对象加入容器中 并用value属性设置对象的Id（即map的key）
    3.根据使用层的不同 又分为@Service @Controller @Repository

        1.@Controller
        在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
        若返回json等内容到页面，则需要加@ResponseBody注解

        @RestController = @Controller + @ResponseBody

        2.@Service 
        服务（注入dao）

        3.@repository
        @repository dao（实现dao访问）


        @Value 是读取配置文件的值 

        spring 不同包下相同类名会注入报错 因为spring管理bean是通过beanName 而不是加了包名  


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

    
[spring事务注解实现的原理]

    1.注入的不是接口实现类 而是一个动态代理对象 同时生成一个JdkDynamicAopProxy对象 它继承InvocationHandler

    2.JdkDynamicAopProxy的invoke方法中 有一系列的拦截器[责任链模式]  加了 @Transactional 就会被事务的拦截器增强

    3.事务的拦截器中有一个invoke方法  它会先获得 @Transactional 中的参数 如事务管理器 隔离级别之类 

    4.然后开启事务 关闭自动提交 执行方法本身  异常时回滚 最后提交事务


[为什么mybatis可以直接用mapper访问数据库]




7.动态代理技术
     
    1.对实现了接口的类的代理   JDK代理

    动态代理 可以代理任何对象 且不用修改代理类

        1.查看proxy对象的对象名 是$proxy0 这个类并没有.java文件

        2.代理对象proxy直接在内存中生成字节码 没有经过.java文件这一步

        3.$proxy0这个类是Proxy的子类 并实现了UserDao接口 通过InvocationHandler来增强
            1.所以proxy对象可以被强转为UserDao对象
        
        4.Proxy.newProxyInstance()参数是接口的一个实现类的加载器 和实现类的父接口 并在invoke中调用了userdao这个真实对象

        所以它具备了
            1.实现真实对象的接口  2.包含了真实对象   
        这两个必备条件

    2.Object result = method.invoke(userDao, args);的理解  [反射的思想]

        1.在InvocationHandler的invoke函数中 还并不知道proxy会调用哪个方法 所以
        也就不知道被增强的方法是哪个 所以要用method把方法传进来 
        method.invoke(userDao, args)这句等价于 userDao调用自身名为method，参数为args的方法

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


    8.
        1.@RestController用于标注控制类，是一个组合注解相当于@Controller+@ResponseBody
        其中[类]上添加注释@Controller请求方法时，返回的是方法返回值对应的html页面
        当[方法]上加上@ResponseBody注释后，返回的是方法返回值的json格式或者文本格式
        当控制类加上@RestController时相当于对[该类的每一个方法]加上了@ResponseBody注解，
        该注释将方法返回的数据类型（基本类型，实体，封装信息）转成json或文本数据，具体如何展示交由前端处理

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
    3.指定配置 需要实现新功能 想自己设置日志配置 
        可以在类路径下面放配置文件 就不会使用日志的默认配置



##  mybatis是如何防止SQL注入的   用#{}可以防止注入 

1、首先看一下下面两个sql语句的区别：
<select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = #{username,jdbcType=VARCHAR}
and password = #{password,jdbcType=VARCHAR}
</select>
<select id="selectByNameAndPassword" parameterType="java.util.Map" resultMap="BaseResultMap">
select id, username, password, role
from user
where username = ${username,jdbcType=VARCHAR}
and password = ${password,jdbcType=VARCHAR}
</select>

    mybatis中的#和$的区别：

1、#将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。
如：where username=#{username}，如果传入的值是111,那么解析成sql时的值为where username="111", 如果传入的值是id，则解析成的sql为where username="id".　
2、$将传入的数据直接显示生成在sql中。
如：where username=${username}，如果传入的值是111,那么解析成sql时的值为where username=111；
如果传入的值是;drop table user;，则解析成的sql为：select id, username, password, role from user where username=;drop table user;
3、#方式能够很大程度防止sql注入，$方式无法防止Sql注入。
4、$方式一般用于传入数据库对象，例如传入表名.
5、一般能用#的就别用$，若不得不使用“${xxx}”这样的参数，要手工地做好过滤工作，来防止sql注入攻击。
6、在MyBatis中，“${xxx}”这样格式的参数会直接参与SQL编译，从而不能避免注入攻击。但涉及到动态表名和列名时，只能使用“${xxx}”这样的参数格式。所以，这样的参数需要我们在代码中手工进行处理来防止注入。
【结论】在编写MyBatis的映射语句时，尽量采用“#{xxx}”这样的格式。若不得不使用“${xxx}”这样的参数，要手工地做好过滤工作，来防止SQL注入攻击。

2、什么是sql注入
　　 sql注入解释：是一种代码注入技术，用于攻击数据驱动的应用，恶意的SQL语句被插入到执行的实体字段中（例如，为了转储数据库内容给攻击者）

　　SQL注入，大家都不陌生，是一种常见的攻击方式。攻击者在界面的表单信息或URL上输入一些奇怪的SQL片段（例如“or ‘1’=’1’”这样的语句），有可能入侵参数检验不足的应用程序。所以，在我们的应用中需要做一些工作，来防备这样的攻击方式。在一些安全性要求很高的应用中（比如银行软件），经常使用将SQL语句全部替换为存储过程这样的方式，来防止SQL注入。这当然是一种很安全的方式，但我们平时开发中，可能不需要这种死板的方式。

3、mybatis是如何做到防止sql注入的
　　MyBatis框架作为一款半自动化的持久层框架，其SQL语句都要我们自己手动编写，这个时候当然需要防止SQL注入。其实，MyBatis的SQL是一个具有“输入+输出”的功能，类似于函数的结构，参考上面的两个例子。其中，parameterType表示了输入的参数类型，resultType表示了输出的参数类型。回应上文，如果我们想防止SQL注入，理所当然地要在输入参数上下功夫。上面代码中使用#的即输入参数在SQL中拼接的部分，传入参数后，打印出执行的SQL语句，会看到SQL是这样的：

select id, username, password, role from user where username=? and password=?
　　不管输入什么参数，打印出的SQL都是这样的。这是因为MyBatis启用了预编译功能，在SQL执行前，会先将上面的SQL发送给数据库进行编译；执行时，直接使用编译好的SQL，替换占位符“?”就可以了。因为SQL注入只能对编译过程起作用，所以这样的方式就很好地避免了SQL注入的问题。

　　【底层实现原理】MyBatis是如何做到SQL预编译的呢？其实在框架底层，是JDBC中的PreparedStatement类在起作用，PreparedStatement是我们很熟悉的Statement的子类，它的对象包含了编译好的SQL语句。这种“准备好”的方式不仅能提高安全性，而且在多次执行同一个SQL时，能够提高效率。原因是SQL已编译好，再次执行时无需再编译。

复制代码
//安全的，预编译了的
Connection conn = getConn();//获得连接
String sql = "select id, username, password, role from user where id=?"; //执行sql前会预编译号该条语句
PreparedStatement pstmt = conn.prepareStatement(sql); 
pstmt.setString(1, id); 
ResultSet rs=pstmt.executeUpdate(); 
......



//不安全的，没进行预编译
private String getNameByUserId(String userId) {
    Connection conn = getConn();//获得连接
    String sql = "select id,username,password,role from user where id=" + id;
    //当id参数为"3;drop table user;"时，执行的sql语句如下:
    //select id,username,password,role from user where id=3; drop table user;  
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    ResultSet rs=pstmt.executeUpdate(); 
    ......
}




【 结论：】

#{}：相当于JDBC中的PreparedStatement
${}：是输出变量的值
简单说，#{}是经过预编译的，是安全的；${}是未经过预编译的，仅仅是取变量的值，是非安全的，存在SQL注入。
如果我们order by语句后用了${}，那么不做任何处理的时候是存在SQL注入危险的。你说怎么防止，那我只能悲惨的告诉你，你得手动处理过滤一下输入的内容。如判断一下输入的参数的长度是否正常（注入语句一般很长），更精确的过滤则可以查询一下输入的参数是否在预期的参数集合中。


 ##  dao接口工作原理
 Dao 接口的工作原理是 JDK 动态代理，Mybatis 运行时会使用 JDK 动态代理为 Dao 接口生成代理 proxy 对象，代理对象 proxy 会拦截接口方法，转而执行MappedStatement所代表的 sql，然后将 sql 执行结果返回。

 ## Mybatis 是如何将 sql 执行结果封装为目标对象并返回的？都有哪些映射形式？


答：第一种是使用<resultMap>标签，逐一定义列名和对象属性名之间的映射关系。第二种是使用 sql 列的别名功能，
将列别名书写为对象属性名，比如 T_NAME AS NAME，对象属性名一般是 name，小写，但是列名不区分大小写，
Mybatis 会忽略列名大小写，智能找到与之对应对象属性名，你甚至可以写成 T_NAME AS NaMe，Mybatis 一样可以正常工作。

有了列名与属性名的映射关系后，Mybatis 通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。