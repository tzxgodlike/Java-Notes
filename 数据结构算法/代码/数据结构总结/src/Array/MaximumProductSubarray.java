package Array;

public class MaximumProductSubarray {
    /*
    leetcode.152 乘积最大子序列
     */
    /*
    遍历数组时计算当前最大值，不断更新
    令imax为当前最大值，则以当前num[i]为末尾的子串最大值为 imax = max(imax * nums[i], nums[i])
    由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
    当负数出现时则imax与imin进行交换再进行下一步计算
    时间复杂度：O(n)O(n)
     */
    public static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0;i<nums.length;i++) {
            //计算以当前num[i]为末尾的子串的乘积 所以num[i]是必乘上的
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max (imax*nums[i],nums[i]);
            imin = Math.min (imin*nums[i],nums[i]);
            //
            max = Math.max(max,imax);
        }
        return max;
    }
}
