package NetEase;

import java.util.HashSet;
import java.util.Scanner;

public class GanRan {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();

        //总人数
        HashSet<Integer> set = new HashSet<>();
        set.add(start);
        //int[] isInfect = new int[n];
        int q = 0;
        for (int i = 0;i<m;i++){
            q = sc.nextInt();
            int[] thisNum = new int[q];
            boolean isHave = false;
            for (int j = 0;j<q;j++) {
                thisNum[j] = sc.nextInt();
                if (set.contains(thisNum[j])){
                    isHave = true;
                }
            }
            if (isHave == true) {
                for (int j = 0;j<q;j++){
                    set.add(thisNum[j]);
                }
            }
        }
        System.out.println(set.size());
    }
}
