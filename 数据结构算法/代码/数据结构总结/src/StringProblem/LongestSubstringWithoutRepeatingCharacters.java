package StringProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    /*
    无重复字符的最长子串  leetcode.3
    * */
    /*
    1.暴力法
     */
    public int lengthOfLongestSubstringBF(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0;i<n;i++) {
            for (int j = i+1;j<=n;j++){
                //
                if (allUnique(s,i,j)) ans = Math.max(ans,j-i);
            }
        }
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start;i<end;i++) {
            char c = s.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }
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
        //int i=0,j = 0;
        //map保存每个字符的最右下标

        for (int i=0,j=0;j<len;j++){
            if (map.containsKey(ch[j])){
                i = Math.max(map.get(ch[j])+1,i);
            }
            max = Math.max(max, j - i + 1);
            map.put(ch[j],j);
            //若此处是map.put(ch[j],j);那么有重复走到if (map.containsKey(ch[j]))时
            //i = Math.max(map.get(ch[j])+1,i) 因为i要移动到重复位置的下一位
        }
        return max;
    }

    /*
   剑指offer解法   跟滑动窗口优化解法一样

   重点都是preIndex = Math.max(preIndex, position[str.charAt(i) - 'a']);
    */
    public static int  findLongestSubstring2(String str) {
        int curLen = 0;
        int maxLen = 0;
        int preIndex = -1;
        // 0~25表示a~z，position[0] = index,表明a上次出现在index处
        int[] position = new int[26];
        //用position数组来代替set


        for (int i = 0; i < 26; i++) {
            position[i] = -1;
        }

        for (int i = 0; i < str.length(); i++) {
            //preIndex是str(i)上一次出现的index
            preIndex = Math.max(preIndex, position[str.charAt(i) - 'a']);

            //那么curLen是当前无重复子串的长度
            curLen = i - preIndex;
            // 记录当前字符出现的位置
            position[str.charAt(i) - 'a'] = i;
            maxLen = Math.max(curLen, maxLen);
        }
        return maxLen;
    }
}
