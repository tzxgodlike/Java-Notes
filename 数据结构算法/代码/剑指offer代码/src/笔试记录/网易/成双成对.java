package 笔试记录.网易;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class 成双成对 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        char[] c = s.toCharArray();
        int maxLen = 0;

        for (int i = 0;i<c.length;i++) {
            for (int j = c.length;j>=1;j--) {
                String sb = s.substring(i,j);
                char[] cb = sb.toCharArray();
                HashMap<Character,Integer> map = new HashMap<>();
                map.put('a',0);
                map.put('b',0);
                map.put('c',0);
                map.put('x',0);
                map.put('y',0);
                map.put('z',0);
                for (char cc:cb) {
                    if (!map.containsKey(cc)) map.put(cc,1);
                    else map.put(cc,map.get(cc)+1);
                }
                if ( (map.get('a')%2==0)&&(map.get('b')%2==0)&&(map.get('c')%2==0)&&(map.get('x')%2==0)
                    &&(map.get('y')%2==0)&&(map.get('z')%2==0) ){
                    maxLen = Math.max(j-i,maxLen);
                    break;
                }
            }
        }


        System.out.println(maxLen);
    }

}
