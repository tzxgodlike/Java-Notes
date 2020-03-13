package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /*
    * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/
    //排序+双指针

//    对数组进行排序。
//    遍历排序后数组：
//    若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
//    对于重复元素：跳过，避免出现重复解
//    令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
//    当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
//    若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
//    若和小于 00，说明 nums[L]nums[L] 太小，LL 右移


    // 难点之一在去重
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums);
        for (int i =0;i<len;i++) {
            if (nums[0]>0) break;  //第一个数大于0 则三数之和肯定大于0
            if (i>0&&nums[i] == nums[i-1]) continue; // 先确定一个i 然后确定L R 所以下次循环碰到和上个数组相等的要跳过
            int L = i+1;
            int R = len-1;
            while (L<R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum==0) {
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++;  //去重
                    while (L<R && nums[R] == nums[R-1]) R--;  //去重
                    L ++;
                    R --;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
}
