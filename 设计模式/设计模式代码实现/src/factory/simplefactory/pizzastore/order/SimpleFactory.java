package factory.simplefactory.pizzastore.order;


import factory.simplefactory.pizzastore.pizza.CheesePizza;
import factory.simplefactory.pizzastore.pizza.GeekPizza;
import factory.simplefactory.pizzastore.pizza.Pizza;

//简单工厂类   也叫静态工厂模式
public class SimpleFactory {


    public Pizza createPizza (String ordertype) {
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (ordertype.equals("greek")) {
            pizza = new GeekPizza();
            pizza.setName("希腊披萨");
        }else if (ordertype.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }

    /*
    静态的创建方法   这样order类调用时直接类名+方法名调用
     */

    public static Pizza createPizza_2 (String ordertype) {
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (ordertype.equals("greek")) {
            pizza = new GeekPizza();
            pizza.setName("希腊披萨");
        }else if (ordertype.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }
}
