package 笔试记录.MeiTuan;

import java.util.HashSet;
import java.util.Scanner;

public class 争夺土地 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        int[] a = new int[p];
        int[] b = new int[p];
        for (int i = 0;i<p;i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0;i<p;i++) {
            b[i] = sc.nextInt();
        }

        for (int i = 0;i<p;i++) {
            setA.add(a[i]);
        }
        int all = 0;
        for (int i = 0;i<p;i++) {
           if (setA.contains(b[i])){
               all++;
           }
        }
        System.out.println((setA.size()-all)+" "+(q-all)+" "+all);



    }
}
