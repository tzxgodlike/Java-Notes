package other;

public class BestTimeSellStock {

    /*
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

    如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

    注意你不能在买入股票前卖出股票。

    示例 1:

    输入: [7,1,5,3,6,4]
    输出: 5
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
         注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格
     */

    /*
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
    通用方法DP解决此类问题

    这个问题的「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态
    dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今最多进行 2 次交易。

    买-卖算一次交易 记买的时候交易次数+1

    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
              max(   选择 rest  ,           选择 sell      )

    解释：今天我没有持有股票，有两种可能：
    要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
    要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                  max(   选择 rest  ,           选择 buy         )

    解释：今天我持有着股票，有两种可能：
    要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
    要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

    定义basecase:
    dp[-1][k][0] = 0
    解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
    dp[-1][k][1] = -infinity
    解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
    dp[i][0][0] = 0
    解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
    dp[i][0][1] = -infinity
    解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。

     */



    /*
    情况1：只交易一次 k=1
    解法1：
    记下每天的历史最低值 并用当天的减最低值 作为最大利润
     */
    public static int maxProfit1(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
    /*
    通用解法2：
    k=1
    dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
    dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
    而dp[i-1][0][0] = 0
    所以
    dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
    dp[i][1][1] = max(dp[i-1][1][1], - prices[i])
    k没有变化 所以省去
    dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    dp[i][1] = max(dp[i-1][1], - prices[i])
     */
    public static int maxProfit1DP(int[] prices)  {
        int len = prices.length;
        int[][] dp = new int[len][2];
        //dp[0][0] = max(dp[0-1][0], dp[0-1][1] + prices[i])=max(0,-infinity)=0

        dp[0][0] = 0;
        //dp[0][1]=max(dp[i-1][1], - prices[i])=max(-infinity,- prices[i])=- prices[i]
        dp[0][1] = -prices[0];
        for (int i = 1;i<len;i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
        }
        return dp[len-1][0];
    }
    /*
    情况2:可以多次交易 k为正无穷
    解法1
     */
    public static int maxProfit2(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int valley = prices[0];
        int peek = prices[0];
        int maxprofit = 0;
        /*
        先找出波谷 再找波峰 再相减 所以要用while循环
         */
        int i = 0;
        while (i<prices.length-1){
            while (i<prices.length-1&&prices[i+1]<prices[i]){
                i++;
            }
            valley = prices[i];
            while(i<prices.length-1&&prices[i+1]<prices[i]){
                i++;
            }
            peek = prices[i];
            maxprofit = peek-valley;
        }
        return maxprofit;
    }

    /*
    解法2 k为正无穷 则k-1和k看作相等
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
    看出K没变化 省去
    dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
    dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    public static int maxProfit2DP(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = - prices[0];
        for (int i = 1;i<len;i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[len-1][0];
    }

    /*
    情况3：最多完成两笔交易
     */
    public static int maxProfit3(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int valley = prices[0];
        int peek = prices[0];
        int maxprofit = 0;
        /*
        先找出波谷 再找波峰 再相减 所以要用while循环
         */
        int i = 0;
        while (i<prices.length-1){
            while (i<prices.length-1&&prices[i+1]<prices[i]){
                i++;
            }
            valley = prices[i];
            while(i<prices.length-1&&prices[i+1]<prices[i]){
                i++;
            }
            peek = prices[i];
            maxprofit = peek-valley;
        }
        return maxprofit;
    }
}
