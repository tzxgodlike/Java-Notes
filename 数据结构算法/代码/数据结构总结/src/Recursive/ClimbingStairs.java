package Recursive;

public class ClimbingStairs {

    /*
    leetcode.70
    * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

    注意：给定 n 是一个正整数。
    * */
    /*
    * 从右往左递归会超时  改为从左往右dp
    * */
    public static int climbStairs(int n) {
        if (n==1)  return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3;i <=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
