package factory.simplefactory.pizzastore.pizza;

public class GeekPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("给希腊披萨准备原材料！");
    }
}
