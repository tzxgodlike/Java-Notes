package basic_04;

import java.util.Arrays;
import java.util.Comparator;

public class LowestLexicography {

    /*
    给定一个字符串类型的数组strs，找到一种拼接方式，使得把所 有字 符串拼起来之后形成的字符串具有最低的字典序。
    先构造一个比较器
    策略1:strq1<=str2,str1放前面      错误
    策略2:str1+str2<=str2+str1 str1+str2放前面    正确
     */
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String a, String b) {
            return (a+b).compareTo(b+a);  //1在2前面 则返回负数
        }
    }

    public static String lowestString(String[] strs){
        if(strs==null||strs.length==0){
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for(int i=0;i<strs.length;i++){
            res+=strs[i];
        }
        return res;
    }
    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));

    }
}
