package factory.simplefactory.pizzastore.order;

public class PizzaStore {

    //相当于客户端

    public static void main(String[] args) {
        //传统模式
        //new OrderPizza();


        //简单工厂模式
        new OrderPizza(new SimpleFactory());
        System.out.println("退出程序");
    }
}
