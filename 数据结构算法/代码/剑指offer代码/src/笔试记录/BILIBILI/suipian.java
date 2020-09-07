package 笔试记录.BILIBILI;

import java.util.*;

public class suipian {

    public static void main(String[] args) {
        System.out.println(GetFragment("aabbaaac"));
    }

    public static int GetFragment (String str) {
        // write code here
        if (str==null||str.length()==0) return 0;
        if (str.length()==1) return 1;
        int cnt = 0; //种类
        int times = 0;//一个碎片的长度
        List<Integer> list = new ArrayList<>();
        int i=0,j = 1;
        while (i<str.length()) {
            if (j==str.length()) {
                list.add(j-i);
                //i = j;
                break;
            }
            if (str.charAt(j)==str.charAt(i)) {
                j++;
            }else  {
                times = j-i;
                //System.out.println(times);
                list.add(times);
                i = j;
                j++;
            }
        }
        int sum = 0;
        for (int n:list) {
            System.out.println(n);
            sum+=n;
        }
        return sum/list.size();
    }

    public static int GetFragment1 (String str) {
        // write code here
        if (str==null||str.length()==0) return 0;
        if (str.length()==1) return 1;
        //int cnt = 0; //种类
        int times = 1;//一个碎片的长度
        //List<Integer> list = new ArrayList<>();
        char start = str.charAt(0);
        for (int i = 1;i<str.length();i++) {
            if (str.charAt(i)!=start) {
                times++;
                start = str.charAt(i);
            }
        }
        return str.length()/times;
    }
}
