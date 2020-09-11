package 笔试记录.爱奇艺;

import java.util.HashMap;
import java.util.Scanner;

public class 无重复字符的最长子串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLongestSubstring1(s));
    }

    public static int lengthOfLongestSubstring1(String s) {
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
}
