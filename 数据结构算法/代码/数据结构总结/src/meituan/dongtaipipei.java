package meituan;

import java.util.*;

public class dongtaipipei {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k =sc.nextInt();
        sc.nextLine();
        List<String> list = new LinkedList<>();
        //Set<String> set = new LinkedHashSet<>();
        List<String> set = new LinkedList<>();
        for (int i = 0;i<k;i++) {
            String tmp = sc.nextLine();
            list.add(tmp);
            set.add(tmp);
        }
        //System.out.println(set);
        for (int i = 0;i<n;i++) {
            String s = sc.nextLine();
            if (s.charAt(0)=='?') {
                String mu = s.substring(1);
                //System.out.println(mu);
                int res = 0;
                for(String ss:set){
                    int cnt = pipei(mu,ss);
                    res = res+cnt;
                }
                System.out.println(res);
            }
            if (s.charAt(0)=='+') {
                int index = Integer.parseInt(s.substring(1));
                set.add(list.get(index-1));
            }
            if (s.charAt(0)=='-') {
                int index = Integer.parseInt(s.substring(1));
                set.remove(list.get(index-1));
            }
        }
    }

    public static int pipei (String parent,String kid) {
        int cnt = 0;
        int index = 0;
        while ((index=parent.indexOf(kid,index))!=-1){
            index = index + 1;
            cnt++;
        }
        return cnt;
    }
}
