package AlgorithmThinking.HuiSu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {

    /*
    leetcode.39
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
     */
    static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates==null) return null;
        List<Integer> list = new LinkedList<>();
        process(0,candidates,0,target,list);
        return res;
    }

    private static void process(int start, int[] candidates, int sum,int target, List<Integer> list) {

        if (sum>target) return ;

        if (target==sum)
            res.add(new ArrayList<>(list)); //需要创建一份副本保存结果 java是值传递
            // res add某个list之后 list被修改为空还是会影响res中list的值
            //res.add(list);  无副本的话 输出会是空
        else {
            //！！！！！！！
            // for循环的作用就是作为回溯的分支 所以不需要process(i+1, candidates, sum, target, list);这句了
            for (int i = start; i < candidates.length; i++) {
                //选择当前位
                list.add(candidates[i]);
                process(i, candidates, sum + candidates[i], target, list);
                //不选择当前位
                list.remove(list.size() - 1);
                //process(i+1, candidates, sum, target, list);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        System.out.println(combinationSum(candidates,8));
    }
}
