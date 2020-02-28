package Stack;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    /*
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

    * */
    /*
    * https://leetcode-cn.com/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode/
    *   初始化栈 S。
        一次处理表达式的每个括号。
        如果遇到开括号，我们只需将其推到栈上即可。这意味着我们将稍后处理它，让我们简单地转到前面的 子表达式。
        如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个 相同类型的 左括号，那么我们将它从栈中弹出并继续处理。否则，这意味着表达式无效。
        如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。
    **/
    //用hashmap来做括号的配对
    private HashMap<Character,Character> mappings;
    public ValidParentheses() {
        mappings = new HashMap<>();
        mappings.put(')','(');
        mappings.put(']','[');
        mappings.put('}','{');
    }

    public  boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
