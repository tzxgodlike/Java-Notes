package 笔试记录.MeiTuan;

import java.util.Scanner;

public class 式子求值 {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0;i<n;i++) {
            a[i] = sc.nextInt();
        }
        int res = 0;
        for (int i = 0;i<n;i++) {
            res^=a[i];
        }
        int tmp = 0;
        for (int i = 0;i<n;i++) {
            tmp = tmp^res;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 1;i<n;i++) {
            dp[i+1] = dp[i]^(i+1);
        }
        for (int i = 1;i<=n;i++) {

            int p = n&(i-1);
            int q = n/i;
            if (q%2==0){
                res^=dp[p];
            }else {
                res^=dp[i-1]^dp[p];
            }
        }

        //int res = 0;
        for (int i = 0;i<n;i++) {
            res = res^b[i];
        }
        System.out.println(res);

    }
}
