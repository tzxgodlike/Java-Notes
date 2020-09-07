package 笔试记录.腾讯;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class 中位数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];

         for (int i = 0;i<n;i++) {
             nums[i] = sc.nextInt();
         }
         int[] tmp = nums.clone();
         Arrays.sort(tmp);
         int mid = n/2-1;

         for (int i = 0;i<n;i++) {
             if (nums[i]<=tmp[mid]) {
                 System.out.println(tmp[mid+1]);
             }else {
                 System.out.println(tmp[mid]);
             }
         }

    }
}
