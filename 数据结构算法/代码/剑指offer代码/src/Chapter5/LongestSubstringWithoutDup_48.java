package Chapter5;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutDup_48 {

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 假设字符串中只包含'a'~'z'之间的字符，例如在字符串"arabcacfr"中，最长的不含重复字符的子字符串是"acfr"，长度为4
     */

    /*
    滑动窗口解法
     */

    public static int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        int i=0,j = 0;
        while (i<len&&j<len) {
            //把ch[j]判断是否在set中
            if (!set.contains(ch[j])) {
                set.add(ch[j++]);
                max = Math.max(max,j-i+1);
            }else {
                //i右移一位 继续判断
                set.remove(ch[i++]);
            }
        }
        return max;
    }

    //优化
    public static int lengthOfLongestSubstring2(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int max = 0;
        HashMap<Character,Integer> map = new HashMap<>();
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

    public static void main(String[] args) {
        String s = "arabcacfr";
        String s1 = "abba";
        System.out.println(lengthOfLongestSubstring2(s));
        System.out.println(findLongestSubstring2(s));
    }
}
