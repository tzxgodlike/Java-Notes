package 笔试总结.猿辅导;

import java.util.*;

public class MaxMatrix {
    /*

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (m==0&&n==0)
           System.out.println(0);
        int[][] matrix = new int[n][m];
        for (int i=0;i<n;i++){

            for (int j = 0;j<m;j++){
                matrix[i][j] = sc.nextInt();
                matrix[i][j] = sc.nextInt();
            }

        }

        int max = 0,res = 0;
        int pre = 0;
        //System.out.println(pre);
        for (int i = 0;i<m;i++) {
            int x = column(matrix,i);
            pre = Math.max(pre+x,x);
            max = Math.max(max,pre);
            System.out.println(pre);
            System.out.println(max);
        }

        System.out.println(max);
    }

    public static int column(int[][] m,int column) {
        int res = 0;
        for (int i = 0;i<m.length;i++){
            res += m[i][column];
        }
        return res;
    }
}
