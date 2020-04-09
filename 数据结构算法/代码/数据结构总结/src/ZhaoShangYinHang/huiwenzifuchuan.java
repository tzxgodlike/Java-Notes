package ZhaoShangYinHang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class huiwenzifuchuan {

    public static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {

        map.put(1,1);
        map.put(2,5);
        map.put(3,8);
        map.put(4,7);
        map.put(6,9);

        map.put(5,2);
        map.put(8,3);
        map.put(7,4);
        map.put(9,6);

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> list = new LinkedList<>();
        //String[] s = new String[n];
        sc.nextLine();
        for (int i = 0;i<n;i++) {
            String s = sc.nextLine();
            //System.out.println(s.toString());
            char[] c =s.toCharArray();

            //System.out.println(c.toString());

            int[] number = new int[c.length];
            for (int j = 0; j < number.length; j++) {
                number[j] = c[j]-'0';
            }
            list.add(isP(number));
            //System.out.println(isP(number));
        }
        //System.out.println(list);
        for(String s : list) {
            System.out.println(s);
        }
    }

    public static String isP(int[] s) {
        if (isPa(s)){
            return "YES";
        }else {
            return "NO";
        }
    }

    private static boolean isPa(int[] num) {
        for(int i = 0;i<num.length;i++){
            //System.out.println(num[i]);
            //.out.println(num[num.length-i-1]);
            if(num[i]!=map.get(num[num.length-i-1])){
                return false;
            }
        }
        return true;
    }
}
