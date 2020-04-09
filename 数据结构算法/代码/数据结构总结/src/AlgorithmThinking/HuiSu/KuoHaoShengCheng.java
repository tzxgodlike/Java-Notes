package AlgorithmThinking.HuiSu;

import java.util.LinkedList;
import java.util.List;

public class KuoHaoShengCheng {
    /*
    给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new LinkedList<>();
        backtrack(ans,"",0,0,n);
        return ans;
    }

    private static void backtrack(List<String> ans, String s, int left, int right, int n) {
        if (s.length()==n*2) {
            ans.add(s);
            return ;
        }


        if (left<n) {
            backtrack(ans,s+"(",left+1,right,n);
        }
        if (right<left){
            backtrack(ans,s+")",left,right+1,n);
        }
    }

}
