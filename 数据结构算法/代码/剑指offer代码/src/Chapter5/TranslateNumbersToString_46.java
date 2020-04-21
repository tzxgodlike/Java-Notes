package Chapter5;
import java.util.*;
public class TranslateNumbersToString_46 {

    /**
     * 给定一个数字，我们按照如下的规则把它翻译成字符串
     >   0 -> a
     >   1 -> b
     >   2 -> c
     >   ...
     >   25 -> z
     >
     >   一个数字可能有多种翻译，比如12258有五种，分别是"bccfi", "bwfi","bczi","mcfi", "mzi".
     请实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     */

    /*
    方法1：  自上而下递归  重复计算较多，但是可以保存编码的结果
     */
    public static List<String> translateNum(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) return list;

        String number = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        translate(sb, number, list);
        return list;
    }

    private static void translate(StringBuilder sb, String strNum, List<String> list) {
        //边界条件   strNum.substring(strNum.length()) == ""  为空
        if (strNum.equals("")) {
            list.add(sb.toString());
            return ;
        }



        String s1 = strNum.substring(0,1);
        char c = numToLetter(s1);
        sb.append(c);
        translate(sb,strNum.substring(1),list);
        sb.deleteCharAt(sb.length()-1);            //回溯

        if (strNum.length()>=2) {
            String s2 = strNum.substring(0,2);
            if (check(s2)) {                 //满足大于11小于25
                char c2 = numToLetter(s2);
                sb.append(c2);
                translate(sb, strNum.substring(2),list);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    /**
     * 当一次翻译两位数时，检查是否范围在10-25之间
     */
    private static boolean check(String num) {
        int val = Integer.parseInt(num);
        return val >= 10 && val <= 25;
    }

    /**
     * 数字 -> 字母的映射，a的ASCII码是97，所以0-25的数字加上97就得到了题目中的映射
     */
    private static char numToLetter(String num) {
        return (char) (Integer.parseInt(num) + 97);
    }


    /*
    方法2：  自下而上  DP
     */

    public static int getTranslateCount(int n) {
        if (n < 0) return 0;
        return count(String.valueOf(n));
    }

    private static int count(String valueOf) {
        int len = valueOf.length();
        int[] dp = new int[len+1];  //多一个dp[len] 是为了处理边界值情况
        //dp[i] = dp[i+1]+dp[i+2];  i=len-2时，dp[i+2]会越界

        //dp[i] 代表以i为开头的串的数量
        dp[len-1] = 1;
        dp[len] = 1;

        for (int i = len-2;i>=0;i--) {
            String s = valueOf.substring(i,i+2);

            if (check(s)) {
                dp[i] = dp[i+1]+dp[i+2];
            }else {
                dp[i] = dp[i+1];
            }
        }


        return dp[0];
    }


    public static void main(String[] args) {

        int n = 12258;

        System.out.println(translateNum(n));
        System.out.println(getTranslateCount(n));


//        String s = "abc";
//        System.out.println(s.substring(0,3));
    }
}
