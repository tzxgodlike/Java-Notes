package 笔试记录.腾讯;

import java.util.*;

public class 通知传递 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set_no = new HashSet<>();
        set.add(0);

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0;i<m;i++) {
            int k = sc.nextInt();
            LinkedList<Integer> arrIn = new LinkedList<>();
            for (int j = 0;j<k;j++) {
                arrIn.add(sc.nextInt());
                set.addAll(arrIn);
                set_no.addAll(arrIn);
            }
            list.add(arrIn);
        }

        set.clear();
        set.clear();
        //int len = 0;
        //while (len<m) {
        for (int len = 0;len<m;len++) {
            for (int i = 0;i<m;i++) {
                for (int j = 0;j<list.get(i).size();j++) {
                    if (set_no.contains(i)) break;
                    if (set.contains(list.get(i).get(j))) {
                        for (int p = 0;p<list.get(i).size();p++) {
                            set.add(list.get(i).get(p));
                        }
                        set_no.add(i);
                        break;
                    }
                }
            }
            //len++;
        }
        System.out.println(set.size());
    }
}
