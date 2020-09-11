package 多态;

public class test {

    public static void main(String[] args) {
        F f = new S();
        f.A();
    }
}

class F{
    public void A(){
        System.out.println("父亲");
    }
}
class S extends F{
    public void A(){
        System.out.println("儿子A");
    }
    public void B(){
        System.out.println("儿子B");
    }
}

