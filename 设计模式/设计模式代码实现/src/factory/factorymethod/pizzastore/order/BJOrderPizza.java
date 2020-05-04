package factory.factorymethod.pizzastore.order;


import factory.factorymethod.pizzastore.pizza.BJCheesePizza;
import factory.factorymethod.pizzastore.pizza.BJPepperPizza;
import factory.factorymethod.pizzastore.pizza.Pizza;

public class BJOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();

        }else if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();

        }
        return pizza;
    }
}
