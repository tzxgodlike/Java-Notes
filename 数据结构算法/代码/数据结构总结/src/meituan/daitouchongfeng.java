package meituan;

import java.util.HashMap;
import java.util.Scanner;

public class daitouchongfeng {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0;i<n;i++) {
            start[i] = sc.nextInt();
        }
        for (int i = 0;i<n;i++) {
            end[i] = sc.nextInt();
        }
        System.out.println(find(start,end));

    }

    public static int find (int[]start,int[] end) {
        int n = start.length;
        HashMap<Integer,Integer> smap = new HashMap<>();
        for (int i = 0;i<n;i++) {
            smap.put(start[i],i);
        }
        int res = 0;
        for (int i = 0 ;i<n;i++) {
            int sindex = smap.get(end[i]);
            for (int j = i;j<n;j++){
                if (smap.get(end[j])<sindex) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
