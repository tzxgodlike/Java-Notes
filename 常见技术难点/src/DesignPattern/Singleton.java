package DesignPattern;

//DCL 双重校验锁懒汉式
class Singleton_1 {
    private volatile static Singleton_1 singleton;
    private Singleton_1 (){}

    public static Singleton_1 getSingleton() {

        if (singleton==null) {
            synchronized (Singleton_1.class){
                if (singleton==null){
                    singleton = new Singleton_1();
                }
            }
        }
        return singleton;
    }

}
