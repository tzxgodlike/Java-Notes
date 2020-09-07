package 笔试记录.百度;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ZouTaiJie {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //n个台阶
        int m = sc.nextInt();  //一步最多跨m个台阶 最少1个

        //每步和之前两步走的台阶数不能相同
        long[] res = new long[4];

        if (m>=n) {
            res[1] ++;
        }
        for (int i =1 ;i<=m;i++) {
            for (int j = 1;j<=m;j++) {
                if (i==j) continue;
                if (i+j>n) continue;
                res[0] = i+j;
                //可以两部
                if (res[0]==n) {
                    res[1] ++;
                    continue;
                }
                dfs(n,i,j,res,m);
                res[0] = 0;
            }
        }

        System.out.println(res[1]);
    }


    private static void dfs(int n, int first, int second, long[] res, int m) {
        if (res[0]==n) {
            res[1] ++;
            res[1]=(long)(res[1]%(1e9+7));
            return ;
        }
        for (int i = 1;i<=m;i++) {
            if (i==first||i==second) {
                continue;
            }
            res[0] = res[0] +i;
            if (res[0]>n) {
                res[0] = res[0]-i;
                continue;
            }
            dfs(n,second,i,res,m);
            res[0] = res[0]-i;
        }
    }
}
