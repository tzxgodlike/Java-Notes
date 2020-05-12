package pdd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class fangruxiaoqiu {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        Long [] h = new Long [n];

        Long res1 = 0l;
        for (int i = 0;i<n;i++) {
            h[i] = sc.nextLong();
            res1+=h[i];
        }
        Arrays.sort(h);
        HashSet<Long> set = new HashSet<>();
        for (int i = 0;i<n;i++){
            if(!set.contains(h[i])) set.add(h[i]);
            else {
                while (set.contains(h[i])){
                    h[i] = h[i]+1;
                }
                set.add(h[i]);
            }
        }
        Long res2 = 0l;
        for (int i = 0;i<n;i++) {
            //h[i] = sc.nextInt();
            res2+=h[i];
        }
        System.out.println(res2-res1);

    }
}
