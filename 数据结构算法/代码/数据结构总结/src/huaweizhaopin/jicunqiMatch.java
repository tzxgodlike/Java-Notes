package huaweizhaopin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jicunqiMatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        String ss = s[0];
        //String[] s_pattern = s[1].split(",");
        String s_pattern = s[1];

        int ss_len = ss.length();
        int p_len = s_pattern.length();

        int index = 0;
        int cnt = 0;

        List<Integer>  list = new ArrayList<>();
        if (s_pattern.indexOf(ss,index)==-1) {
            System.out.println("FAIL");
        }
        while ((index=s_pattern.indexOf(ss,index))!=-1) {
            list.add(index);
            index+=ss.length();
        }
        boolean has = false;
        char[] c_pattern = s_pattern.toCharArray();
        for (int i = 0;i<list.size();i++) {
            //start为匹配起点
            int start = list.get(i);
            //System.out.println(start);
            if (c_pattern[start+ss_len]!='[') continue;
            has = true;
            //System.out.println(c_pattern[start+ss_len]);
            String addr = s_pattern.substring(start+10,start+14);
            if (addr.charAt(3)==']') addr = addr.substring(0,3);
            if (c_pattern[start+14]!=',') {
                addr = s_pattern.substring(start+10,start+15);
                start +=1;
            }
            System.out.print(addr+" ");
            String mask = s_pattern.substring(start+20,start+24);
            if (mask.charAt(3)==']') mask = mask.substring(0,3);
            if (c_pattern[start+24]!=',') {
                mask = s_pattern.substring(start+20,start+25);
                start +=1;
            }
            System.out.print(mask+" ");
            String val = s_pattern.substring(start+29,start+33);
            if (val.charAt(3)==']') val = val.substring(0,3);
            if (c_pattern[start+33]!=',') {
                val = s_pattern.substring(start+39,start+34);
                start +=1;
            }
            System.out.print(val);
            System.out.print("\r\n");

        }
        if (!has) {
            System.out.println("FAIL");
        }

    }

}
