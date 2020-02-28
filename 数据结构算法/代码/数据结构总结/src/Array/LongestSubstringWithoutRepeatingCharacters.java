package Array;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.HashSet;
import java.util.Set;

/*
leetcode 3
* Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.


* */
public class LongestSubstringWithoutRepeatingCharacters {
    /*
    * 1.暴力法
    * */
    public int lengthOfLongestSubstring(String s) {
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

}
