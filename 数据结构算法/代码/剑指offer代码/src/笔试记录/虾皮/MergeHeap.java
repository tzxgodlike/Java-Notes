package 笔试记录.虾皮;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeHeap {

    public static void main(String[] args) {

    }

    public int getMinScore (int[] gz) {
        // write code here
        int[][] dp = new int[gz.length+1][gz.length+1];
        int[] sum = new int[gz.length+1];
        sum[0] = 0;
        for (int i = 0;i<=gz.length;i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for (int i = 0;i<gz.length;i++) {
            sum[i+1] = gz[i]+sum[i];
            dp[i+1][i+1] = 0;
        }
        for (int len = 1;len<gz.length;len++) {
            for (int i = 1;i+len<=gz.length;i++) {
                int j = i+len;
                for (int k = i;k<j;k++) {
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+sum[j]-sum[i-1]);
                }
            }
        }
        return dp[1][gz.length];
    }

}
