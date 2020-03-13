public class Father {
    public static int age = 18;
    public  int cm = 222;

    public static void main(String[] args) throws InterruptedException {
        Father f = new Father();
        f.cm = 20;
        Father f1 = new Father();
        System.out.println(f1.cm);
    }
}
