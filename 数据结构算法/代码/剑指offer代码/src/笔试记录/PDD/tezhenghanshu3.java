package 笔试记录.PDD;

import java.util.HashSet;
import java.util.Scanner;

public class tezhenghanshu3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[]  feature = new int[m];
        for (int i = 0;i<m;i++) {
            feature[i] = sc.nextInt();
        }
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
//        for (int i = 1;i<=n;i++) {
//            set.add(i);
//        }
        for (int i = 0;i<m;i++) {
            res += count(n,feature[i],set);

        }
        System.out.println(res);
    }

    private static int count(int n, int feature,HashSet<Integer> set) {
        int ans = 0;
        for (int i = 1;i<=n;i++) {
            if (i%feature==0) {
                if (!set.contains(i)) {
                    ans+=1;
                    set.add(i);
                }
                //System.out.println(i);
            }
        }
        return ans;
    }

//    public static int uglyNumber(int n,int[] feature) {
//        //if (index <= 0) return 0;
//
//        int t2 = 0,t3 = 0,t5 = 0;
//        int m = feature.length;
//        int[] start = new int[m];    //初值为0
//        int[] res = new int[10000000];
//
//        res[0] = 1;
//
//        for (int i = 1;i<10000000;i++) {
//            for (int j = 0;j<m;j++) {
//                int m1 = res[start[j]] * 2;
//                int m2 = res[t3] * 3;
//                int m3 = res[t5] * 5;
//            }
//
//            res[i] = Math.min(m1,Math.min(m2,m3));
//            // 选择某个丑数后ti * i，指针右移从丑数集合中选择下一个丑数和i相乘
//            // 注意是三个连续的if，也就是三个if都有可能执行。这种情况发生在三个候选中有多个最小值，
//            // 指针都要右移，不然会存入重复的丑数
//            if (res[i]==m1) t2++;
//            if (res[i]==m2) t3++;
//            if (res[i]==m3) t5++;
//        }
//        return res[10000000-1];
//    }
}
