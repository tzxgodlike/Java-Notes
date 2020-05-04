package Tencent;

import java.util.Scanner;

public class zuiduanjuli_2 {

    public static class point{
        int x;
        int y;

        public point(int x, int y){
            this.x=x;
            this.y=y;
        };
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n  = sc.nextInt();
        for (int i = 0; i<n;i++) {

            int len = sc.nextInt();
            point[] a = new point[len];
            point[] b = new point[len];
            for (int j = 0;j<len;j++) {
                int x= sc.nextInt();
                int y= sc.nextInt();
                a[j] = new point(x,y);

                //System.out.println(a[j].x+" "+a[j].y);
            }
            for (int j = 0;j<len;j++) {
                int x= sc.nextInt();
                int y= sc.nextInt();
                b[j] = new point(x,y);
            }

            double res = cacl(a,b);
            String s = String.format("%.3f",res);
            System.out.println(s);
        }
    }

    private static double cacl(point[] a, point[] b) {
        double res = Double.MAX_VALUE;
        for (int i = 0;i<a.length;i++) {
            for (int j = 0;j<b.length;j++) {
                double cur = Math.sqrt(Math.pow(a[i].x-b[j].x,2)+Math.pow(a[i].y-b[j].y,2));
                res = Math.min(res,cur);
            }
        }
        return res;
    }
}
