package 笔试记录.爱奇艺;

import java.util.*;

public class 三数之和 {


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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            for (String s1 : s) {
                list.add(Integer.parseInt(s1));
            }


            int n = list.size();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = list.get(i);
                //System.out.println(nums[i]);
            }
            List<List<Integer>> res = threeSum(nums);
            //Arrays.sort(res);
            //System.out.println(res);
            for (int i = 0; i < res.size(); i++) {
                for (int j = 0; j < res.get(i).size(); j++) {
                    System.out.print(res.get(i).get(j) + " ");

                }
                System.out.println();
            }
        }
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[0] > 0) break;  //第一个数大于0 则三数之和肯定大于0
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 先确定一个i 然后确定L R 所以下次循环碰到和上个数组相等的要跳过
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++;  //去重
                    while (L < R && nums[R] == nums[R - 1]) R--;  //去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
}
