package KuaiShou;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class kuaishou2 {
    public static List<Integer> Find(int[] height) {
        // write code here
        if (height == null) {
            return null;
        }
        List<Integer> list = new LinkedList<>();
        int[] res = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (height[j] > height[i]) {
                    res[i] += 1;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 1) list.add(i);
        }
        return list;
    }

    public static List<Integer> FindDP(int[] height) {
        int[] dp = new int[height.length];
        for (int i = 1;i< height.length;i++) {
            if (height[i]<height[i-1]){
                dp[i] = dp[i-1]+1;
            }
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 1) list.add(i);
            System.out.println(dp[i]);
        }
        return list;
    }
//    public static List<Integer> FindSort(int[] height) {
//        Arrays.sort(height);
//        List<Integer> list = new LinkedList<>();
//        for (int i = 0; i < dp.length; i++) {
//            if (dp[i] == 1) list.add(i);
//            System.out.println(dp[i]);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        //int[] h = {1,22,22,33,22,12,45,44,5};

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            int[] res = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                res[i] = Integer.parseInt(s[i]);
            }
            //System.out.println(res);
            List<Integer> ans = FindDP(res);
            if (ans==null||ans.size()==0) System.out.println(-1);
            for (int i :ans){
                System.out.print(i+" ");
            }
            System.out.println(ans);
        }
    }
}
