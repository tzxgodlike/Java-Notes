package HuaWei;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.HashMap;
import java.util.HashSet;

public class HW2 {

    public static void main(String[] args) {
        System.out.println(fun("ddd","egg"));
        System.out.println(fun("foo","bar"));
        System.out.println(fun("paper","title"));
    }

    public static boolean fun (String s1,String s2)  {
        return process(s1,s2)&&process(s2,s1);
    }
    public static boolean process(String s1,String s2) {

        if (s1.length()!=s2.length()) return false;

        HashMap<Character,Character> map = new HashMap<>();

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        for (int i = 0;i<s1.length();i++) {
            if (!map.containsKey(c1[i])) {
                map.put(c1[i],c2[i]);
            }else if (c2[i]!=map.get(c1[i])){
                return false;
            }
        }
        return true;
    }
}
