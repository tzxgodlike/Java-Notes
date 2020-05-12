package pdd;

import java.util.Scanner;

public class tezhenghanshu {
     static int A= 0;
    static int B= 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] dp = new int[100000];
        for (int i = 0;i<t;i++) {
            A = sc.nextInt();
            B = sc.nextInt();

            int n = sc.nextInt();
//            for (int j = 2;j<=n;j++){
//                dp[j] = dp[j-1]+dp[j-2];
//            }
            int res = f(n);
            if(res%3==0) System.out.println("YES");
            else System.out.println("NO");
            //System.out.println(dp[n]);
        }
    }

    public static int f(int n){
        if (n==0) return A;
        if (n == 1) return B;

        return f(n-1)+f(n-2);
    }

}
