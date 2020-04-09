### 代理模式
    1.定义 
        给目标对象提供一个代理对象，并由代理对象控制
        对目标对象的引用
    2.目的
        1.实现间接访问目标对象，防止直接访问对象对系统带来
        不必要的复杂性
        2.增强目标对象的业务
 
    3.组成
        1.接口Subject  真是对象和代理对象都要实现该接口
        2.真实对象 RealSubject
        3.Proxy对象包含了RealSubject
        
### 静态代理

    1.RealFacory要继承多个接口  
    
    2.违背了开闭原则 
        1.对扩展开放 对修改关闭 [增加功能需要修改类]


### 动态代理

    1.代理对象是一个动态的不固定的对象
    
    2.Proxy [生成员工] 和 InvocationHandler [生成业务]
    
    3,代理新的类是 不需要修改
    
### 基于JDK代理
    
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
    2.
        1.查看proxy对象的对象名 是$proxy0 这个类并没有.java文件

        2.代理对象proxy直接在内存中生成字节码 没有经过.java文件这一步

        3.$proxy0这个类是Proxy的子类 并实现了UserDao接口
    
    3.
