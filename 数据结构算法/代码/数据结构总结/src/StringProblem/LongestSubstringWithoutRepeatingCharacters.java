package StringProblem;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    /*
    无重复字符的最长子串  leetcode.3
    * */
    /*
    1.暴力法
     */
    /*
    2.滑动窗口 左开右闭 其实就是以i开头，一位位往后滑，直到遇到重复的 就记录之前的长度 然后i是0~n-1
     */
    //暴力法需要n^3的复杂度 因为假设[0,m-1]无重复 遍历1时 又重新开始计算[1,m-1]之间的子串 所以用一个滑动窗口保留这部分子串
    // O(2n)
    public int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        int i=0,j = 0;
        while (i<len&&j<len) {
            if (!set.contains(ch[j])) {
                set.add(ch[j++]);
                max = Math.max(max,j-i);
            }else {
                set.remove(ch[i++]);
            }
        }
        return max;
    }
    /*
    继续优化  遍历0时 找到[0,m-1]中与m重复的下标j 滑动窗口左边直接划到[j+1,m-1）O(n)
     */
    public int lengthOfLongestSubstring1(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int max = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        int i=0,j = 0;
        //map保存每个字符的最右下标
        while (i<len&&j<len) {
            if (map.containsKey(ch[j])){
                i = Math.max(map.get(ch[j]),max);
            }
            max = Math.max(max, j - i + 1);
            map.put(ch[j],j);
        }
        return max;
    }

}
