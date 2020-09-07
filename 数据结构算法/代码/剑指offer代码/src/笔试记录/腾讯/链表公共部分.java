package 笔试记录.腾讯;

import java.math.BigInteger;
import java.util.Scanner;

public class 链表公共部分 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        BigInteger[] a1 = new BigInteger[n1];
        for (int i = 0;i<n1;i++) {
            a1[i] = sc.nextBigInteger();
        }
        int n2 = sc.nextInt();
        BigInteger[] a2 = new BigInteger[n2];
        for (int i = 0;i<n2;i++) {
            a2[i] = sc.nextBigInteger();
        }
        int i = 0,j=0;
        while (i<a1.length&&j<a2.length) {
            if (a1[i].compareTo(a2[j])==0)  {
                System.out.print(a1[i]+" ");
                i++;
                j++;
            }
            else if (a1[i].compareTo(a2[j])==-1) j++;
            else i++;
        }
    }
}
