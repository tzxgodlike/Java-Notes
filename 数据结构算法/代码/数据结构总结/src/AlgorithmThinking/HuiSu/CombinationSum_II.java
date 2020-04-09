package AlgorithmThinking.HuiSu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum_II {

    /*
    leetcode 40
    不能重复
     */

    /*
    这个避免重复当思想是在是太重要了。
这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
                  1
                 / \
                2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
               /     \
              5       5
                例2
                  1
                 /
                2      这种情况确是允许的
               /
              2

为何会有这种神奇的效果呢？
首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。
因为当第二个2出现的时候，他就和前一个2相同了。

那么如何保留例2呢？
那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
例2的两个2是处在不同层级上的。
在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
     */
    static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates==null) return null;
        Arrays.sort(candidates);
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
                if (candidates[i]>target) continue;
                //!!!!!!!  !!!!!!!!!!!!!
                //去重  就是说可以选这一层第一次出现的a a出现第二次就不能选了
                if (i>start&&candidates[i]==candidates[i-1]) continue;
                //选择当前位
                list.add(candidates[i]);
                process(i+1, candidates, sum + candidates[i], target, list);
                //不选择当前位
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,2,7,6,1,5};
        System.out.println(combinationSum(candidates,8));
    }
}
