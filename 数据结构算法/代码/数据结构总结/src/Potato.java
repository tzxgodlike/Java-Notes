public class Potato {
    /*
    比如，每斤土豆6天价格[[1, 3, 2, 8, 4, 9],每次交易会交1块钱的手续费。
    那么你的最大利润就是：
    8-1-1+9-4-1=10

    买卖只扣一次钱
     */

    public static int MaxProfit(int[] prices, int cost) {
        if (prices==null||prices.length<2) return 0;
        int len = prices.length;
        int dp[][] = new int[len][2];

        //dp[i][j]表示第i天持有与否时的利润
        dp[0][0] = 0;
        dp[0][1] = -prices[0]-cost;
        for (int i = 1;i < len;i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][0]-prices[i]-cost,dp[i-1][1]);
        }
        return dp[len-1][0];
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int cost = 1;
        int profit = MaxProfit(prices,cost);
        System.out.println(profit);
    }
}
