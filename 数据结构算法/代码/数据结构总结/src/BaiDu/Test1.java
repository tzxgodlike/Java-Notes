package BaiDu;

import java.util.Scanner;

public class Test1 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;
        for (int i = 1;i<=n;i++ ) {
            for (int j = 1;j<=n;j++){
                int gcd = maxCommonDivisor(i,j);
                int lcm = minCommonMultiple(i,j);
                int res = lcm-gcd;
                max = Math.max(res,max);
            }
        }
        System.out.println(max);

    }

    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {     // 保证被除数大于除数
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {  // 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;    // 返回最大公约数
    }

    public static int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }
}