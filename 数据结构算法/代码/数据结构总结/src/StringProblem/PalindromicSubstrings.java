package StringProblem;

import java.util.HashSet;

public class PalindromicSubstrings {

    /*
    回文子串 leetcode.647 不用考虑去重
     */
    public static int countSubstrings(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = 0;

        char[] ch = str.toCharArray();
        int len = ch.length;
        boolean[][] isPalindromic = new boolean[len][len];
        for (int i = 0;i<len;i++) {
            isPalindromic[i][i] = true;
            res++;


            if (i+1<len&&(ch[i]==ch[i+1])) {
                isPalindromic[i][i+1] = true;
                res++;
            }
        }


        for (int i = len-1;i>=0;i--){
            for (int j =len-1;j>=i+2;j--) {
                isPalindromic[i][j] = isPalindromic[i+1][j-1]&&(ch[i]==ch[j]);
                if (isPalindromic[i][j]==true) {
                    res++;

                }
            }
        }
    return res;
}}

