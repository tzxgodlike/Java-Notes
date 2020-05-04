package factory.absfactory.pizzastore.pizza;

public class LDPepperPizza  extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒披萨");
        System.out.println("给伦敦胡椒披萨准备原材料");
    }
}
