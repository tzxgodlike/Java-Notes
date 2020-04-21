package meituan;

import java.util.Scanner;

public class buloudong {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int res = find(10,3);
        System.out.println(res);
    }

    private static int find(int n, int k) {
        int left = 1,right = n;
        while (left<=right) {
            int mid = left + (right - left)/2;
            //System.out.println(mid);
            //System.out.println(canSolve(mid,k,n));
            if (canSolve(mid,k,n)) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canSolve(int x, int k,int n) {
        int sum = x;
        while ((x/k)>0) {
            x = x/k;
            sum+=x;
            k = k*k;
        }
        if (sum >= n) return true;
        return false;
    }
}
