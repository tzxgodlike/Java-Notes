package factory.factorymethod.pizzastore.order;

import factory.factorymethod.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class OrderPizza {

    /*
    工厂方法模式：

    工厂方法模式设计方案：将披萨项目的实例化功能抽象成抽象方法，在不同的口味点 餐子类中具体实现。

    工厂方法模式：定义了一个创建对象的抽象方法，由子类决定要实例化的类。工厂方 法模式将对象的实例化推迟到子类。
     */

    /*
    OrderPizza和其子类就相当于工厂了
     */

    /*
    现在的要求是地点+口味两个参数
     */
    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String ordertype ="" ;    //订购披萨的类型

        do {
            ordertype = getType();
            pizza = createPizza(ordertype);   //抽象方法 由工厂子类去实现
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }


    //定义一个抽象方法 createPizza 让各个工厂子类去实现
    abstract Pizza createPizza(String orderType);





    private String getType() {
        BufferedReader strin  = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("pizza type: ");
        String type = null;
        try {

            type = strin.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }
}
