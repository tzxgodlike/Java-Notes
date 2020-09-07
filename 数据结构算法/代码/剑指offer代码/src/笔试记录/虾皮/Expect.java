package 笔试记录.虾皮;

public class Expect {

    public static void main(String[] args) {
        System.out.println(expect(2));
    }
    public static int expect (int n) {
        // write code here
        double res = 0;
        for (int i = 1;i<=n;i++) {
            res+=Math.pow(0.5,2*n-i)*i;
        }
       return (int)res;
    }
}
