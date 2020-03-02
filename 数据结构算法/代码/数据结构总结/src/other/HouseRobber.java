package other;

public class HouseRobber {

    /*

    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

    思路：
    打家劫舍这一类问题分别在No198，No213和No337，本题是这一类问题最简单的一种状态，第一直觉就应该是动态规划，如官方题解，设f(x)为打劫前x家房子所能得到的最大的资金，很容易想到动态规划的边界条件，即：

    f(1)=nums[1]

    f(2)=max(nums[1],nums[2])

    然后是最关键的动态转移方程，如果要打劫第n家，就必然不能打劫第n-1家，所以打劫第n家得到的钱一共是第n家的钱加上前n-2家获得的最多的钱，即：f(n-2)+nums(n)，如果不打劫第n家，获得的最大收益就是f(n-1)，两者我们要去较大的那个，所以动态转移方程是：

    f(n)=max(nums[n]+f(n-2),f(n-1))
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        ans[1] = Math.max(ans[0],nums[1]);
        for (int i = 2;i<nums.length;i++) {
            ans[i] = Math.max(nums[i]+ans[i-2],ans[i-1]);
        }
        return ans[ans.length-1];
    }
}
