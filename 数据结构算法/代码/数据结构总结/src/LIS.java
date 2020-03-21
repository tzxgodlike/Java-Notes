import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        /*
        找出字符串组合起来也是上升 的 最大长度

        先排序 再按最长上升子序列做
         */
        String[] s = {"xxxxxxy","yyy","azz","bcdz","aaaaa","bbbbbbbbbbz"};
        String[] s1 =  {"aaa", "bcd", "zzz", "bcdef", "uvwz", "bcdefvwzzzzzz", "bbbbb", "bbbb"};
        System.out.println(LIS(s1));
    }

    //这题跟那个最长上升子序列类似
    private static int LIS(String[] s) {
        Arrays.sort(s);
        int count = s[0].length();
        int dp[] = new int[s.length];
        for (int i = 0;i<s.length;i++) {
            dp[i] = s[i].length();
        }
        for (int i = 1;i<s.length;i++) {
            int j = s[i].length();
            char x = s[i].charAt(0);
            for (int k = 0;k<i;k++) {
                char y = s[k].charAt(s[k].length()-1);
                if (x>=y){
                    //排序的目的就是  两个字符串只需要判断前一个的末尾和后一个的首是否上升
                    //说明s[k]和s[i]连起来是上升
                    dp[i] = Math.max(dp[k]+s[i].length(),dp[i]);
                }
            }
            count = Math.max(count,dp[i]);
        }
        return count;
    }
}
