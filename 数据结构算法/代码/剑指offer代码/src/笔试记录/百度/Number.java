package 笔试记录.百度;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int cnt5 = 0,cnt0 = 0;

        for (int i = 0;i<n;i++) {
            a[i] = sc.nextInt();
            if (a[i]==5) cnt5++;
            if (a[i]==0) cnt0++;
        }
        //System.out.println(a);
        int times_5 = cnt5%9;
        StringBuilder sb = new StringBuilder();
        if (cnt5==0) {
            System.out.println(-1);
            return;
        }

        if (cnt0==0) {
            System.out.println(-1);
            return;
        }
        for (int i = 0;i<times_5*9;i++) {
            //sb.append(5);
            System.out.println(5);
        }
        for (int i = 0;i<cnt0;i++) {
            //sb.append(0);
            System.out.println(0);
        }

    }

}
