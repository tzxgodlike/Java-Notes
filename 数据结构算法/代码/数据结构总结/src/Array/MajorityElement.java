package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    //解法1 排序 n/2的位置肯定是众数
    public static int countNumsWithSorted(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
    //解法2 哈希
    private static int countNums(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }

        HashMap.Entry<Integer, Integer> majorityEntry = null;
        //遍历hashMap
        for (Map.Entry<Integer,Integer> entry :map.entrySet()) {
            if (majorityEntry ==null ||entry.getValue()>majorityEntry.getValue()){
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2,2,4,5};
        int i = countNums(nums);
        System.out.println(i);
    }
}
