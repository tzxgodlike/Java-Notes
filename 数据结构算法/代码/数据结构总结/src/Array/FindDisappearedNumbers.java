package Array;

import java.util.LinkedList;
import java.util.List;

/**leetcode.448
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 * 思路：抽屉原理 遍历数组 把num[i]对应的坐标上的数置为负  最后剩下为正的 坐标就是缺失的正数
 *
 */

public class FindDisappearedNumbers {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0;i<nums.length;i++) {
            int newindex = Math.abs(nums[i])-1;
            if(nums[newindex]>0) nums[newindex] = -1*nums[newindex];
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 1;i<=nums.length;i++) {
            if (nums[i-1]>0) {
                res.add(i);
            }
        }
        return res;
    }
}

