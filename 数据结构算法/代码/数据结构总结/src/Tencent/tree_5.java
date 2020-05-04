package Tencent;

import java.util.Scanner;

public class tree_5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0;i<n;i++) {
            long x = sc.nextLong();
            int k = sc.nextInt();
            long res = tree(x,k);
            System.out.println(res);

        }

    }

    private static long tree(long x, int k) {

        long h = (long)log2(x)+1;
        if (k>=h) return -1;
        else {
            while (h!=k){
                x = x/2;
                h--;
            }
        }
        return x;
    }

    public static double log2(double n){
        return Math.log(n)/Math.log(2);
    }
}
