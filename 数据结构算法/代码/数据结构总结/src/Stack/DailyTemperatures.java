package Stack;

import java.util.*;

public class DailyTemperatures {

    /*
    leetcode.739
    根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
    如果之后都不会升高，请在该位置用 0 来代替。

    例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是
     [1, 1, 4, 2, 1, 1, 0, 0]。


     */

    /*
    思路1： 递减栈

    见题解https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/
    视频描述的过程很清楚
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int length = T.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int pre = stack.pop();
                result[pre] = i - pre;
            }
            stack.add(i);

        }
        return result;
    }

    /*
    思路2： 从左往右计算 会重复计算
            从右往左的话 当前我们需要计算 7575 位置，然后向右遍历到 7171，因为我们已经计算好了 71
            71 位置对应的值为 22，那么我们就可以直接跳 22 为在进行比较，利用了已经有的结果，减少了遍历的次数。

    https://leetcode-cn.com/problems/daily-temperatures/solution/jie-ti-si-lu-by-pulsaryu/

     */

    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] result = new int[length];

        //从右向左遍历
        for (int i = length - 2; i >= 0; i--) {
            // j+= result[j]是利用已经有的结果进行跳跃
            for (int j = i + 1; j < length; j+= result[j]) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) { //遇到0表示后面不会有更大的值，那当然当前值就应该也为0
                    result[i] = 0;
                    break;
                }
            }
        }

        return result;
    }


}
