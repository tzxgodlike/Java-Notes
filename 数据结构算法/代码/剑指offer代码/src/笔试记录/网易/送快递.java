package 笔试记录.网易;

import java.util.*;

public class 送快递 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        //HashMap<Integer,Integer> map = new HashMap<>();
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0;i<n;i++) {
            list.add(new LinkedList<>());
        }
        int[] num = new int[n];
        for (int i = 0;i<n-1;i++) {
            num[i] = sc.nextInt();
            //System.out.println(num[i]);
            //map.put(num[i],i+1);
            list.get(num[i]).add(i+1);
        }
        //System.out.println(list);
        int start = 0;
        int[] res = new int[1];
        int[] max = new int[1];

        bfs(list.get(0),res,list);
        int ans = 0;
        if (res[0]>k)  ans = k+1;

        else ans = res[0]+1;
        System.out.println(ans);
    }

    private static void bfs(List<Integer> tmp, int[] res,List<List<Integer>> list) {
        //List<Integer> tmp = list.get(index);
        if (tmp.isEmpty()) {
            return ;
        }
        res[0]++;
        //max[0]  = Math.max(res[0],max[0]);
        for (int i = 0;i<tmp.size();i++) {
            //res[0]++;
            bfs(list.get(tmp.get(i)),res,list);
            //res[0]--;
        }
    }
}
