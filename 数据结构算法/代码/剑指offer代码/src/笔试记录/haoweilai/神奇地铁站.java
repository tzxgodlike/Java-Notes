package 笔试记录.haoweilai;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class 神奇地铁站 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        BigInteger n_10 = new BigInteger(s,2);
        //BigDecimal b = new BigDecimal(n_10);
        //int sqrt_4 = Math.log(b)/Math.log(4);
        long l_10 = n_10.longValue();
        int k = 0;
        while (true) {
            long tmp = (long)Math.pow(4,k);
            //BigInteger tmp = new BigInteger(ss);
            if (tmp>=l_10) {
                System.out.println(k);
                break;
            }
            k++;
        }

    }
}
