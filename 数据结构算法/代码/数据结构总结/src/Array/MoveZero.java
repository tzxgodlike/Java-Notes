package Array;

public class MoveZero {
    /**
     * 方法1
     */
    public void moveZeroes1(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt++, cur);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /*
    * 方法2  用k记录非0的元素个数 遍历数组 把非0的前移
    * */
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        for (int j = k; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}
