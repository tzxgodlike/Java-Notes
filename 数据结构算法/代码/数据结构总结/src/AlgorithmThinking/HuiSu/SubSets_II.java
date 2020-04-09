package AlgorithmThinking.HuiSu;

import java.util.ArrayList;
import java.util.List;

public class SubSets_II {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<Integer> temp = new ArrayList<>();

        backtrack(0,nums,temp);
        return res;
    }


    public void backtrack (int index,int[] nums,List<Integer> temp) {
        //if (index > nums.length) return;
        res.add(new ArrayList<>(temp));
        //if (index<nums.length) {
        for (int i = index; i < nums.length; i++) {
            if (i>index&&nums[i]==nums[i-1]) continue;
            temp.add(nums[i]);
            backtrack(i + 1, nums, temp);
            temp.remove(temp.size() - 1);
        }
        //}

    }

    public static void main(String[] args) {
        int[] num = {1,2,2};
        System.out.println(new SubSets_II().subsets(num));
    }
}
