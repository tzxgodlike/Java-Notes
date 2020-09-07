package 笔试记录.腾讯;

import java.util.*;

public class 字符串次数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();  //前k大

        String[] strs = new String[n];
        sc.nextLine();
        for (int i = 0;i<n;i++) {
            strs[i] = sc.nextLine();
        }


        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], 1);
            } else {
                map.put(strs[i], map.get(strs[i]) + 1);
            }
        }

        //小顶堆
        PriorityQueue<String> queueSmall = new PriorityQueue<String>(
                (w1,w2)->map.get(w1).equals(map.get(w2))?w2.compareTo(w1):map.get(w1)-map.get(w2)
        );

        //大顶堆
        PriorityQueue<String> queueBig = new PriorityQueue<String>(
                (w1,w2)->map.get(w1).equals(map.get(w2))?w2.compareTo(w1):map.get(w2)-map.get(w1)
        );
//        queueBig.offer(1);
//        queueBig.offer(2);
//        queueBig.offer(3);
//        while (!queueBig.isEmpty()) {
//            System.out.println(queueBig.poll());
//        }

        for (String word:map.keySet()) {
            queueSmall.offer(word);
            if (queueSmall.size()>k) queueSmall.poll();
        }
        List<String> ans = new ArrayList<>();
        while (!queueSmall.isEmpty()) {
            ans.add(queueSmall.poll());
        }
        Collections.reverse(ans);
        for (String s:ans) {
            System.out.println(s+" "+map.get(s));
        }

        //后k个
        for (String word:map.keySet()) {
            queueBig.offer(word);
            if (queueBig.size()>k) queueBig.poll();
        }
        List<String> ans2 = new ArrayList<>();
        while (!queueBig.isEmpty()) {
            ans2.add(queueBig.poll());
        }
        Collections.reverse(ans2);
        for (String s:ans2) {
            System.out.println(s+" "+map.get(s));
        }




    }
}
