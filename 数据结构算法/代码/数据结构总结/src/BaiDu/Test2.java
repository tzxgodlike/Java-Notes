package BaiDu;


import java.util.Scanner;
import java.util.Arrays;

public class Test2 {

    public static int count (int[] num,int n) {
        Arrays.sort(num);
        int cnt = 0;
        int k = num[n-1] ;
        for (int i = 0;i<=k;i++) {
            num[n-1] = num[n-1]-n;
            for (int j = 0;j<n-1;j++){
                num[j] = num[j]+1;
            }
            Arrays.sort(num);
            cnt++;
            if (num[n-1]<n) break;
        }
        return cnt;
    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] num = new int[n];
        for (int i = 0;i<n;i++) {
            num[i] = sc.nextInt();
        }

        System.out.print(count(num,n));

    }
}

