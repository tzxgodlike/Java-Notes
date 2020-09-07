package 笔试记录.ABC;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class StringSort {
    /*
    给定A B C 等字母的大小等级  给定1 2 3 4 .. 的优先级 求A2这种字符串的排序
     */
    public static String[] getPokerOrder(String[] cards) {

        //用treeset或者直接arrays排序

//        TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                HashMap<Character,Integer> map = new HashMap<>();
//                map.put('k',1);
//                map.put('s',2);
//                map.put('h',3);
//                map.put('p',4);
//                map.put('q',5);
//                int num = map.get(s1.charAt(0))-map.get(s2.charAt(0));
//                //第一位相同就比第二位
//                int num2 = num==0?Integer.parseInt(s1.substring(1))-
//                        Integer.parseInt(s2.substring(1)):num;
//                return num2;
//            }
//        });
//        for (String s:cards) set.add(s);
//        String[] res = set.toArray(new String[set.size()]);

        //或者
        Arrays.sort(cards, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                HashMap<Character,Integer> map = new HashMap<>();
                map.put('k',1);
                map.put('s',2);
                map.put('h',3);
                map.put('p',4);
                map.put('q',5);
                int num = map.get(s1.charAt(0))-map.get(s2.charAt(0));
                //第一位相同就比第二位
                int num2 = num==0?Integer.parseInt(s1.substring(1))-
                        Integer.parseInt(s2.substring(1)):num;
                return num2;
            }
        });



        return cards;

    }

    public static void main(String[] args) {
        String[] s = new String[] {"s2","h3","k5","k1","h2","p1","q2","q3"};
        getPokerOrder(s);
        for (String s1:s
             ) {
            System.out.println(s1);
        }
    }
}
