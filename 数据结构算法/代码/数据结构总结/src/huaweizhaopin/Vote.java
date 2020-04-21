package huaweizhaopin;

import java.util.*;

public class Vote {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().split(",");

        HashMap<String,Integer> map = new HashMap<>();
        int len = s.length;
        if (len==0||len>100) {
            System.out.println("error.0001");
            return ;
        }
        boolean right = true;
        for (int i = 0;i<s.length;i++) {
            char[] c = s[i].toCharArray();
            if (c[0] >= 'A' && c[0] <= 'Z') {
                //right = true;
//                System.out.println("error.0001");
//                return;
                for (int j = 1; j < c.length; j++) {
                    if (!(c[j] >= 'a' && c[0] <= 'z')) {
                        right = false;
                    }
                }
            } else {
                right = false;
            }
//            for (int i = 1;i<c.length;i++) {
//                if (c[i]>='a'&&c[0]<='z')
//            }
            if (right) {
                if (map.containsKey(s[i])) {
                    map.put(s[i], map.get(s[i]) + 1);
                } else {
                    map.put(s[i], 1);
                }
            }else {
                System.out.println("error.0001");
               return;
            }
        }
        int max = Integer.MIN_VALUE;
        //for ()
        //HashMap.Entry<Integer, Integer> majorityEntry = null;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            max = Math.max(map.get(iterator.next()),max);
        }
        System.out.println(max);
        List<String> res = new ArrayList<>();
        Iterator iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()) {
            String cur = (String)iterator1.next();
            System.out.println(cur);
            int curInt = map.get(cur);
            if (curInt==max){
                res.add(cur);
            }
        }
        Collections.sort(res);
        System.out.println(res.get(0));
    }
}
//    HashMap.Entry<Integer, Integer> majorityEntry = null;
////遍历hashMap
//        for (Map.Entry<Integer,Integer> entry :map.entrySet()) {
//        if (majorityEntry ==null ||entry.getValue()>majorityEntry.getValue()){
//        majorityEntry = entry;
//        }
//        }
//        return majorityEntry.getKey();