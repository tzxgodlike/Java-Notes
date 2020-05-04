package factory.simplefactory.pizzastore.order;

import factory.simplefactory.pizzastore.pizza.CheesePizza;
import factory.simplefactory.pizzastore.pizza.GeekPizza;
import factory.simplefactory.pizzastore.pizza.Pizza;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    /*
    传统方式 ：   违反了开闭原则  pizza的创建在order类中实现

    当order类有很多时，新增一个pepper披萨时 要修改所有的order类代码

    改进思路：    把创建pizza这个封装到一个类中  新增披萨种类时，只需要修改factory类
     */
    //构造器
//    public OrderPizza() {
//        Pizza pizza = null;
//        String ordertype  ;    //订购披萨的类型

//        do {
//            ordertype = getType();
//            if (ordertype.equals("greek")) {
//                pizza = new GeekPizza();
//                pizza.setName("希腊披萨");
//            }else if (ordertype.equals("cheese")) {
//                pizza = new CheesePizza();
//                pizza.setName("奶酪披萨");
//            }else {
//                break;
//            }
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
//        }while (true);
//    }

    SimpleFactory simpleFactory;
    Pizza pizza = null;

    public OrderPizza (SimpleFactory simpleFactory) {
        setSimpleFactory(simpleFactory);
    }

    public void setSimpleFactory (SimpleFactory simpleFactory) {
        String ordertype = "";  //用户输入

        this.simpleFactory = simpleFactory;

        do {
            ordertype = getType();

            pizza = simpleFactory.createPizza(ordertype);

            if (pizza!=null) {

                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购失败");
                break;
            }

        }while (true);
    }

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
