package pdd;

import java.util.Scanner;

public class zuidagongyueshu {

    public static Long maxCommonDivisor(Long m, Long n) {
        if (m < n) {
            Long temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {
            Long temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Long[] num = new Long[n];
        for (int i = 0;i<n;i++) {
            num[i] = sc.nextLong();
        }
        Long gcd = 0l;
        Long res = 0l;
        for (int i = 0;i<n;i++) {
            for (int j = i;j<n;j++) {
                for (int m = i;m<=j;m++){
                    if (m==i){
                        gcd = num[i];
                    }else {
                        gcd = maxCommonDivisor(num[m],gcd);

                    }
                }
                res = Math.max(gcd*(j-i+1),res);

            }
        }
        int p = 0,q=0;
        Long tmp = 0l;
        while (p<n&&q<n) {
            for (int m = p;m<=q;m++){
                if (m==p){
                    gcd = num[p];
                }else {
                    gcd = maxCommonDivisor(num[m],gcd);

                }
            }
            tmp= gcd*(q-p+1);
            if (tmp>res) {
                res = tmp;
                q++;
            }else {
                p++;
            }

        }
        System.out.println(res);
    }
}
