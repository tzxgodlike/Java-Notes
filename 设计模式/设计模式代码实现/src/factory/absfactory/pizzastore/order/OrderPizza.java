package factory.absfactory.pizzastore.order;

import factory.absfactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    AbsFactory factory;

    //构造器

    public OrderPizza(AbsFactory absFactory) {
        setFactory(factory);
    }

    public void setFactory(AbsFactory factory) {
        Pizza pizza = null;
        String orderType = "";
        this.factory = factory;

        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);   //多态
            if (pizza!=null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("够早失败");
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
