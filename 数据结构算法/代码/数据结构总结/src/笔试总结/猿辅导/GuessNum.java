package 笔试总结.猿辅导;

import java.util.*;

public class GuessNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {

            int n = sc.nextInt();
            int k = sc.nextInt();
            int left = 1, right = n;
            List<Integer> list = new ArrayList<>();
            while (left <= right) {       //left ==right时 num[left]此时还未判断过 所以要取=号
                int mid = left + (right - left) / 2;
                if (right - left == 0) {
                    break;
                } else if (right - mid > mid - left) {
                    left = mid + 1;
                    list.add(mid);
                } else if (right - mid <= mid - left)
                    right = mid - 1;
                {

                    list.add(mid);
                }
            }
            Collections.sort(list);
//        int cnt = list.size()-k;
//        int res = 0;
//        for (int i = 0;i<cnt;i++) {
//            res += list.get(i);
//        }
//        System.out.println(res);

            Collections.sort(list);
            int cnt = list.size();
            int res = 0;
            for (int i = k; i < cnt; i++) {
                res += list.get(i);
            }
            System.out.println(res);
        }
    }
}
