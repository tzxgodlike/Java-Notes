package AlgorithmThinking.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAllAnagramsInAString {
    /*
    leetcode.438 找到字符串中所有字母异位词
    给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

    字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

    说明：

    字母异位词指字母相同，但排列不同的字符串。
    不考虑答案输出的顺序。
    示例 1:

    输入:
    s: "cbaebabacd" p: "abc"

    输出:
    [0, 6]

    解释:
    起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
    起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

     */

    /*
    滑动窗口思想
    起始窗口为[0,0] 先不断增加right扩大窗口 直到窗口符合要求
    然后停止增加right 转而增加left 直到窗口不再符合要求 每次增加left 都要更新结果
    重复以上过程
     */
    public List<Integer> findAnagrams(String s, String p){
        List<Integer> list = new ArrayList<>();
        int left = 0,right = 0;
        HashMap<Character,Integer>  needs = new HashMap<>();
        HashMap<Character,Integer>  windows = new HashMap<>();
        //先用needs记录p中字符出现的个数
        for (char c:p.toCharArray()){
            //第一次加入c 默认值为0
            needs.put(c,needs.getOrDefault(c,0)+1);
        }
        int match = 0;
        while(right<s.toCharArray().length) {
            char c1 = s.charAt(right);
            if (needs.containsKey(c1)) {
                if(!windows.containsKey(c1)) windows.put(c1,1);
                else {
                    windows.put(c1,windows.get(c1)+1);
                }
                //若c1已在windows中
                if (windows.get(c1)==needs.get(c1)) match++;
            }
            right++;
            while (match==needs.size()) {
                //如果长度正好满足 就把left加入结果
                if (right - left == p.length()){
                    list.add(left);
                }
                //缩小左边界
                char c2 = s.charAt(left);
                if (needs.containsKey(c2)) {
                    windows.put(c2,windows.get(c2)-1);
                    if (windows.get(c2)<needs.get(c2)) match--;
                }
                left++;
            }
        }
        return list;
    }
}
