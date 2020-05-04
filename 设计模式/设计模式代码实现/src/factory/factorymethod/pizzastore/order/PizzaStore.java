package factory.factorymethod.pizzastore.order;

public class PizzaStore {

    public static void main(String[] args) {
        //北京口味
        new BJOrderPizza();    //子类会调用父类的构造器

        //伦敦口味
        new LDOrderPizza();
    }
}
