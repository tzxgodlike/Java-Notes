package 笔试记录.haoweilai;

import java.util.Scanner;

public class 青蛙跳 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0;i<n;i++) {
            long right = sc.nextLong();
            long left = sc.nextLong();
            long times = sc.nextLong();
            long end = 0;
            //奇数次
            if (times%2==1)  {
                end = (long)((right-left)*(times/2)+right);
            }else {
                end = (long)((right-left)*(times/2));
            }
            System.out.println(end);
        }
    }
}
