package Array;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    /*
    最长上升子序列 leetcode.300  没有要求是连续的
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int res = 0;
        Arrays.fill(dp,1);
        for (int i = 0;i<len;i++) {
            for (int j = 0;j<i;j++) {
                if (nums[i]>nums[j]){
                    //这里dp[i]需要取最大值 因为可能前面有6个上升 自中间断了 然后接着5个上升
                    //所以在遍历[0,j]中要记录最大的dp[i]
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                    //dp[i] = dp[j+1];
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
