package 笔试记录.MeiTuan;

import java.util.Scanner;

public class 树 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0;i<n;i++) {
                a[i] = sc.nextInt();
            }

            int cnt0 = 0;
            int cnt2 = 0;

            boolean f = true;
            for(int i = 0;i<n;i++) {
                if (a[i]==1) cnt0++;
                if (a[i]%2==0) {
                    //System.out.println("NO");
                    f = false;
                    break;
                }
                else cnt2++;
            }
            //System.out.println(cnt2==cnt0-1);
            if (f==false) System.out.println("NO");
            else if(cnt2==cnt0-1){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
