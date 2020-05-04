package factory.absfactory.pizzastore.order;


//抽象工厂模式得抽象层

import factory.absfactory.pizzastore.pizza.Pizza;


public interface  AbsFactory {

    //让工厂子类去具体实现
    public Pizza createPizza (String orderType);
}
