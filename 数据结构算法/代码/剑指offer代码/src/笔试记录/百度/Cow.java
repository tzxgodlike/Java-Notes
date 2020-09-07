package 笔试记录.百度;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int t = 0;t<times;t++ ) {
            int n = sc.nextInt();  //奶牛数量
            int m = sc.nextInt();   //需要满足的特征数量
            int[][] cowNo = new int[m][n+1];
            for (int j = 0;j<m;j++) {
                int f_times = sc.nextInt();  //条件数
                for (int f = 0;f<f_times;f++) {
                    int start = sc.nextInt();
                    int end = sc.nextInt();
//                    for (int s = start;s<=end;s++) {
//                        cowNo[j][s] = 1;
//                    }
                    Arrays.fill(cowNo[j],start,end+1,1);
                }
            }

            //计算合格
            int res = 0;
            ArrayList<Integer> listRes = new ArrayList<>();
            for (int i = 1;i<=n;i++) {
                int cnt = 0;
                for (int j = 0;j<m;j++) {
                    if (cowNo[j][i]!=1)
                        break;
                    cnt++;
                }
                if (cnt==m) {
                    res++;
                    listRes.add(i);
                }
            }
            System.out.println(res);
            for (int num:listRes) {
                System.out.print(num+" ");
            }
            System.out.println();

        }
    }
}
