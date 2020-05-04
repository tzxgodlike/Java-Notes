package factory.factorymethod.pizzastore.order;

import factory.factorymethod.pizzastore.pizza.*;

public class LDOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();

        }else if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();

        }
        return pizza;
    }
}
