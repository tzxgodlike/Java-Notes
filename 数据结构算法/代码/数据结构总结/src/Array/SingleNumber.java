package Array;

import java.util.HashMap;

public class SingleNumber {
    /*
    leetcode.136
    * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    * 思路：异或满足交换律 而相同数字异或为0 所以异或所有数字 最后结果就为所求
    * */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int num:nums) {
            ans^=num;
        }
        return ans;
    }

    public static int singleNumberHashMap(int[] nums) {
        HashMap<Integer,Object> map = new HashMap<>();
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            }else {
                map.put(num,null);
            }
        }
        return map.keySet().iterator().next();
    }
}
