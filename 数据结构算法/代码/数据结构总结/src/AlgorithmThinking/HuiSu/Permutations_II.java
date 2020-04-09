package AlgorithmThinking.HuiSu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations_II {

    /*
    leetcode 47
     */
    public  List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        process(nums_lst,0);
        return res;
    }

    public  void process(ArrayList<Integer> chs, int i) {
        if (i == chs.size()) {
            res.add(new ArrayList<>(chs));;
            //return ; //i==chs.len之后 下面的j=i 肯定不满足循环体 所以也会直接退出
        }else {
            for (int j = i; j < chs.size(); j++) {
                if (j>i&&(chs.get(j)==chs.get(i))) continue;
                Collections.swap(chs, i, j);
                process(chs, i + 1);
                //这里不用回溯 因为第一种决策是自己与自己换
                //相当于决策树上父与左孩子一样
                Collections.swap(chs, i, j);
            }}
    }


    public static void main(String[] args) {
        int[] nums = {1,1,3};

        System.out.println(new Permutations_II().permute(nums));
    }
}
