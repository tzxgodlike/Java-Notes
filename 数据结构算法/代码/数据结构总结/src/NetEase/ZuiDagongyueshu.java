package NetEase;

import java.util.Scanner;

public class ZuiDagongyueshu {
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

        for (int i = 0;i<n;i++ ) {
            num[i] = sc.nextLong();
        }
        Long temp = num[1]-num[0];
        for (int i = 2;i<n;i++) {
            Long a = num[i] - num [i-1];
            temp = maxCommonDivisor(temp,a);
        }
        System.out.println(temp);
    }
}
