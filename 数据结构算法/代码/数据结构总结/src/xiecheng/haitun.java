package xiecheng;

import java.util.Scanner;

public class haitun {

    public static int cowNumber1(int n){
        if(n<1){
            return 0;
        }
        if(n==1||n==2||n==3){
            return n;
        }
        return cowNumber1(n-1)+cowNumber1(n-3);
    }

    public static void main(String[] args) {
        int n = 20;
        //System.out.println(cowNumber1(n));
        //System.out.println(cowNumber2(n));
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
        String[] s = in.nextLine().split(" ");
        int res = 0;
        for (int i = 0;i<s.length;i++) {
            res+=Integer.parseInt(s[i]);
        }
        //System.out.println(res);
    }
    }

}
