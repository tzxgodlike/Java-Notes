package HighFrequencyProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static jdk.nashorn.internal.objects.NativeMath.min;

public class EditDistance {
    /*
    给定两个字符串s1 s2 计算将s1转化为s2锁使用的最少操作数
    有三种操作
    1.插入一个字符
    2.删除一个字符
    3，替换一个字符
     */

    /*
    1.递归 从下往上 处理字符串一般都末尾开始
    对于每对字符串 有4种操作
    if s1[i]==s2[j]   //后面的都已经一样
        //往前跳
        i-- j--
    else
        插入
        删除
        替换
     */
    public static int minDistance (String s1,String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        //int[][] memo = new int[c1.length-1][c2.length-1];
        //Arrays.fill(memo,0);  //作为备忘录
        return dp(c1,c2,c1.length-1,c2.length-1);
    }
    public static int dp(char[] c1,char[] c2,int i,int j) {

        if (i==-1) return j+1;
        if (j==-1) return i+1;
        //if (memo[i][j]>0) return memo[i][j];
        if (c1[i]==c2[j]) return dp(c1,c2,i-1,j-1);
        else{
            int a = dp(c1, c2, i, j-1)+1;//插入 在c1上插入 i不动  j前移
            int b = dp(c1, c2, i-1, j)+1;//删除c1[i]
            int c = dp(c1, c2, i-1, j-1)+1;//替换;
            //求最小值
            return (a<b)?(a<c?a:b):(b<c?b:c);
        }
        //return memo[i][j];
    }
    /*
    DP 从前往后 dp[i][j]记录  basecase是i j =-1的情况 数组边界从0开始 所以要右移处理一下
     */
    public static int DP(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        /*
        s1s2 "" a p p l e
        ""   0  1 2 3 4 5
        r    1  1 2 3 4 5
        a    2  1 2 3 4 5
        d    3  2 2 3 4 5
         */
        //dp[0][0] 默认值就是0
        for (int i = 1;i<=m;i++) dp[i][0] = i;
        for (int j = 1;j<=n;j++) dp[0][j] = j;

        for (int i = 1;i<=m;i++) {
            for (int j = 1;j<=n;j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = min(dp[i-1][j]+1,
                            dp[i][j-1]+1,
                            dp[i-1][j-1]+1);
            }
        }
        return dp[m][n];
    }

    public static int min(int a,int b,int c) {
        return Math.min(a,Math.min(b,c));
    }
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(minDistance(s1,s2));
        System.out.println(DP(s1,s2));
    }
}
