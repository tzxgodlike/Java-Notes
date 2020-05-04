package factory.absfactory.pizzastore.order;

import com.sun.org.apache.xpath.internal.operations.Or;

public class PizzaStore {


    /*
抽象工厂模式：定义了一个interface用于创建相关或有依赖关系的对象簇，而无需 指明具体的类
2) 抽象工厂模式可以将简单工厂模式和工厂方法模式进行整合。
3) 从设计层面看，抽象工厂模式就是对简单工厂模式的改进(或者称为进一步的抽象)。
4) 将工厂抽象成两层，AbsFactory(抽象工厂) 和 具体实现的工厂子类。
程序员可以 根据创建对象类型使用对应的工厂子类。这样将单个的简单工厂类变成了工厂簇， 更利于代码的维护和扩展。

    特点
    1. 用工厂来封装创建对象的行为

    2. 多态  把创建对象的实现下沉到子类工厂
 */


    public static void main(String[] args) {

        //北京口味
        new OrderPizza(new BJFactory());

        //伦敦口味
        new OrderPizza(new LDFactory());
    }
}
