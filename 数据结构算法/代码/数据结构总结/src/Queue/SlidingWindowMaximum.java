package Queue;

public class SlidingWindowMaximum {
    /*
    leetcode.239
    * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

 

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

    * */

    /*
    1.暴力解法brute force 遍历所有窗口
    * */
    public int[] maxSlidingWindowWithBruteForce (int[] nums,int k){
        int n = nums.length;
        if (n * k == 0) return new int[0];

        int[] out = new int[k-n+1];
        for (int i = 0;i < k-n+1;i++) {
            int max = Integer.MAX_VALUE;
            for (int j = i;j<k+i;j++) {
                max = Math.max(max,nums[j]);
            }
            out[i] = max;
        }
        return out;
    }

    /*
    * 2.双端队列
    * */

}
