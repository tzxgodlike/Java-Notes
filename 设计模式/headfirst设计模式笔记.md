# 设计模式入门

**模拟鸭子应用**

鸭子超类superclass  各种鸭子subclass继承  现在要添加fly方法

 1. 继承 利用继承来提供鸭类的行为  在主类添加一个方法fly，但橡皮鸭子子类不能飞

 2. 接口 创建flyable接口 造成代码无法复用

 3. 第一个设计原则 **找出应用中可能需要变化之处，独立出来，不要和那些不需要变化的代码混在一起**

 4. 于是把鸭子超类中经常变化的部分fly和quack取出（因为每个子类的行为不一样），建立新类

 5. 第二个原则 **面对接口编程，而不是面对实现编程**

    - 以前的做法是，行为定义在子类继承父类中，或者继承某个接口再由子类实现。**依赖实现**

    - 新设计中，具体的行为编写在实现接口的类中

      *flywithwings和flynoway两个类实现flybehavior接口*

   6.整合鸭子类

   - 在Duck类中加入两个接口类型

     ```java
     public class Duck{
     
     		QuackBehavior quackBehavior;
     
     		FlyBehavior flyBehavior;
     
     		public void performQuack(){
     
                 		quackBehavior.quack();
     		}
     		public void performQuack(){
     
     `            		quackBehavior.quack();
     		}
     }
     ```

     

   - 将飞行和叫的胸围**委托****给（delegate）QuackBehavior 对象

			- 在子类中给行为接口实例化  可以通过构造器(不能动态改变) 可以通过set方法赋值 可以动态改变行为

   7.**多用组合 少用继承**

   8.把每组行为想象成一个算法簇(fly是一个组，quack是一个组，组内算法可以互换)，分别封装，此模式让算法的变化独立与使用算法的客户。



​                       



