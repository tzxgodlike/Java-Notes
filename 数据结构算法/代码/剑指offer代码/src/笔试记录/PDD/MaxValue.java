package 笔试记录.PDD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MaxValue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();    //背包初始大小
        int[] value = new int[n];
        int[] weight = new int[n];
        for (int i = 0;i<n;i++) {
          weight[i] = sc.nextInt();
          value[i] = sc.nextInt();
        }

        int ans = 0;
        ArrayList<Integer> res = new ArrayList<>();
        dfs(0,m,0,n,res,weight,value);
        Collections.sort(res);
        System.out.println(res.get(res.size()-1));
    }

    private static void dfs(int amount, int space, int index, int n, ArrayList<Integer> res,
                            int[] weight,int[] value) {
        if (index>=n) {
            res.add(amount);
            return ;
        }
        if (space-weight[index]>=0) {
            dfs(amount+value[index],space-weight[index],index+1,n,res,weight,value);
        }
        dfs(amount,space,index+1,n,res,weight,value);
    }
}


//    public static int dp (int[] weight,int[] value,int i,int j ) {
//        int[][] results = new int[i][j];
//
//    //private int ks3(int i, int j){
//        // 初始化
//        for (int m = 0; m <= i; m++){
//            results[m][0] = 0;
//        }
//        for (int m = 0; m <= j; m++){
//            results[0][m] = 0;
//        }
//        // 开始填表
//        for (int m = 1; m <= i; m++){
//            for (int n = 1; n <= j; n++){
//                if (n < ws[m]){
//                    // 装不进去
//                    results[m][n] = results[m-1][n];
//                } else {
//                    // 容量足够
//                    if (results[m-1][n] > results[m-1][n-ws[m]] + vs[m]){
//                        // 不装该珠宝，最优价值更大
//                        results[m][n] = results[m-1][n];
//                    } else {
//                        results[m][n] = results[m-1][n-ws[m]] + vs[m];
//                    }
//                }
//            }
//        }
//        return results[i][j];
//    }
