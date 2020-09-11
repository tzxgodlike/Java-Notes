package 笔试记录.小米;

import java.util.Scanner;

public class 密码生成器 {

    public static void main(String[] args) {
        /*
        注册网站时，需要使用包含不同类型（数字、符号、大写字母、小写字母）的字符，和特定长度。检查一个密码内容同时包含以上 4 种类型的字符，并且长度在8-120 个字符之间。符合要求，返回 0；长度不符合返回 1；类型不符合要求返还 2。

可以一次输入多组密码，以空格符间隔，空格符不作为密码。
         */

        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");

        for (int i = 0;i<strs.length;i++) {
            char[] c = strs[i].toCharArray();
            if (c.length<8||c.length>120) {

                System.out.println(1);
                continue;
            }

            //判断类型
            int[] leixing = new int[4];
            for (char c1:c) {
                if (c1>='a'&&c1<='z') leixing[0] = 1;
                else if (c1>='A'&&c1<='Z') leixing[1] = 1;
                else if (c1>='0'&&c1<='9')  leixing[2] = 1;
                else if ((c1>='!'&&c1<='/')||(c1>=':'&&c1<='@')||(c1>='['&&c1<=96)||(c1>='{'&&c1<='~')) leixing[3] = 1;
            }
            boolean isLx = true;
            for (int num:leixing) {
                if (num==0) isLx = false;
            }
            if (isLx) System.out.println(0);
            else {
                System.out.println(2);
            }
        }
    }
}
