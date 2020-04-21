package WANGYIHUYU;

import java.util.Scanner;

public class gongzuorenwu {
    public static  int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] w = new int[n];
        int[] t = new int[n];

        for (int i = 0;i<n;i++) {
            w[i] = sc.nextInt();
        }
        for (int i = 0;i<n;i++) {
            t[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        process(w,t,0);

        System.out.println(res%m);

    }

    public static void process(int[] w,int[] t,int i) {
        if (i == t.length) {
           if (check(w,t)) res++;
            //return ; i==chs.len之后 下面的j=i 肯定不满足循环体 所以也会直接退出
        }

        for (int j = i; j < t.length; j++) {
            swap(t, i, j);
//            if (t[j]>w[j]) {
//                swap(t, i, j);
//                break ;
//            }
            process(w,t, i + 1);
            swap(t, i, j);
        }
    }

    private static boolean check(int[] w, int[] t) {

        for (int i = 0;i<w.length;i++) {
            if (w[i]<t[i]) return false;
        }
        return true;
    }




    public static void swap(int[] chs, int i, int j) {
        int tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
