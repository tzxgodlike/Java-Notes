package AlgorithmThinking.HuiSu;
import java.util.*;
public class SubSets {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        List<Integer> temp = new ArrayList<>();

        process(0,nums,temp);
        return res;
    }

    //1.回溯   每个结点都作为结果  逻辑是先求个数为0的子集  再求个数为1的子集  再求.....
    public void backtrack (int index,int[] nums,List<Integer> temp) {
        //if (index > nums.length) return;
        res.add(new ArrayList<>(temp));
        //if (index<nums.length) {
            for (int i = index; i < nums.length; i++) {

                temp.add(nums[i]);
                backtrack(i + 1, nums, temp);
                temp.remove(temp.size() - 1);
            }
        //}

    }


    //2. dfs  不利于剪枝  树的叶节点作为结果
    public void process (int index,int[] nums,List<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList(temp));

        }else {
            temp.add(nums[index]);
            process(index+1,nums,temp);
            temp.remove(temp.size()-1);
            process(index+1,nums,temp);
        }
    }

    public static void main(String[] args) {
        int[] num = {1,2,3};
        System.out.println(new SubSets().subsets(num));
    }
}
