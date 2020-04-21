package StringProblem;

public class LongestPalindromicSubstring {
    /*
    最长回文子串 leetcode.5
     */

    /*
    1.DP 状态转移方程中两端都需要移动 所以需要两个变量ij dp数组为二维数组
    P(i,j) = P(i+1,j-1)&&(si==sj)
    p(i,i) = true P(i,i+1) = (si==s(i+1))
     */
    public static String longestPalindromicSubstring(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] ch = str.toCharArray();
        int len = ch.length;
        int max = 1;
        int left = 0;
        int right = 0;
        boolean[][] isPalindromic = new boolean[len][len];
        for (int i = 0;i<len;i++) {
            isPalindromic[i][i] = true;
            if (i+1<len&&(ch[i]==ch[i+1])) {
                isPalindromic[i][i+1] = true;
                max = 2;
                left = i;
                right = i+1;
            }
        }


        for (int i = len-1;i>=0;i--){
            for (int j =len-1;j>=i+2;j--) {
                isPalindromic[i][j] = isPalindromic[i+1][j-1]&&(ch[i]==ch[j]);
                if (isPalindromic[i][j]==true&&(j-i+1>=max)) {
                    max = j-i+1;
                    left = i;
                    right = j;
                }
            }
        }
        System.out.println(left+" "+right);
        String res = String.valueOf(ch).substring(left,right+1);
        //string转char[]用valueof
        //substring index是左开右闭
        System.out.println(res);
        return res;
    }

    /*
    2.中心扩展算法
     */

    public static void main(String[] args) {
        String res =longestPalindromicSubstring("abcasdcabcbacyuiiu");
        System.out.println(res);
    }
}
