package factory.simplefactory.pizzastore.pizza;

public abstract class Pizza {

    protected String name;

    //准备原材料   不同的披萨不一样
    public abstract void prepare();

    public void bake() {
        System.out.println(name+" 烘烤!");
    }

    public void cut() {
        System.out.println(name+" 切披萨!");
    }

    public void box() {
        System.out.println(name+" 打包!");
    }

    public void setName(String name) {
        this.name = name;
    }
}
