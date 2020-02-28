package basic_05;

public class Hanoi {
    public static void hanoi(int n){
        if (n>0){
            process(n,"左","右","中");
        }
    }

    private static void process(int n, String from, String to, String help) {
        if(n==1){
            System.out.println("move 1 from"+from+"to"+to);
            //return ;
        }else {
            //n-1部分从左移到中，此时右就作为help
            process(n-1,from,help,to);
            //单独挪n
            System.out.println("move"+n+"from"+from+"to"+to);
            //n-1从中到右
            process(n-1,help,to,from);

        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(3);
    }
}
