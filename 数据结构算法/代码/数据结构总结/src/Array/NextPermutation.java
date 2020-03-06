package Array;

public class NextPermutation {

    /*
    leetcode.31
    “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

    我们可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以得到下一个更大的整数。如 123 下一个更大的数为 132。如果没有更大的整数，则输出最小的整数。

     */

    /*
    思路：
    我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的大数与前面的小数交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
    我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
    在尽可能靠右的低位进行交换，需要从后向前查找
    将一个尽可能小的大数与前面的小数交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
    将大数换到前面后，需要将大数后面的所有数重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     */

    public static void nextPermutation(int[] nums) {
        int i = nums.length-2;
        int j = nums.length-1;
        int k = nums.length-1;
        //找到第一个升序的相邻对
        while (i>=0&&nums[i]>nums[j]) {
            i--;
            j--;
        }
        if(i>=0){
            while (k>i&&nums[k]<=nums[i]) {
                k--;
            }
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
        }

        //把i之后的逆置
        int left = j;
        int right = nums.length-1;
        while (left<right){
            int tmp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,1,1};
        nextPermutation(nums);
        System.out.println(nums);
    }
}
