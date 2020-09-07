package Chapter2;

public class CuttingRope_14 {
    /**
     * 给你一根长度为n的绳子，把绳子剪成m段（m、n都是整数且m > 1, n > 1）,m段绳子的长度依然是整数，求m段绳子的长度乘积最大为多少？
     * 比如绳子长度为8，我们可以分成2段、3段、4段...8段，只有分成3段且长度分别是2、3、3时能得到最大乘积18
     */
    public static int maxDP(int n) {
        /*
        DP解法
        首先从上而下 构思递归
        f(n)为把长度为N的绳子剪若干段后得最大乘积
        f(n) = max(f(n-i)*f(i))
        然后自底向上构造DP
         */
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max = 0;
        for (int i = 4;i<=n;i++) {
            //避免重复计算
            max = 0;
            for (int j = 1;j<=i/2;j++) {
                max = Math.max(max,dp[j]*dp[i-j]);
            }
            dp[i] = max;
        }
        //max = dp[n];
        return dp[n];
    }
    /*
    贪心策略
    n>=5时 3(n-3)>2(n-2)>n  所以尽可能剪长度为3的段
    n=4时 2*2=4
     */
    public static int max2(int n) {
        if (n<2) return 0;
        if (n==2) return 1;
        if (n==3) return 2;

        int timesOf3 = n/3;
        //最后有4的时候 不能减3
        if (n-timesOf3*3==1)
            timesOf3 -=1;
        int timesOf2 = (n-timesOf3*3)/2;  //2的个数为2或1
        return (int)Math.pow(3,timesOf3)*(int) Math.pow(2,timesOf2);
    }
    public static void main(String[] args) {
        System.out.println(maxDP(3));
        System.out.println(max2(8));
    }
}
