package 笔试记录.PDD;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MaxSoilders {

    /*
    4 4
1 0 1 1
1 1 0 1
0 0 0 0
1 1 1 1


8
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //行数
        int m = sc.nextInt();  //列数
        int[][] matrix = new int[n][m];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<m;j++ ){
                matrix[i][j] = sc.nextInt();
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
         //matrix1 = null;
        for (int i = 0;i<n;i++) { //遍历行
            for (int j = 0;j<m;j++) {   //列

                for (int p = 0;p<n;p++) {
                    for (int q = 0;q<m;q++) {

                        int[][] m2 = swap(matrix,i,j,p,q);
                        int tmp3 = countIslands(m2);
                        ans.add(tmp3);
                    }
                }


            }

        }
        Collections.sort(ans);
//        for (int i = 0;i<n;i++) {
//            for (int j = 0;j<m;j++ ){
//                System.out.println(matrix[i][j]);
//            }
//        }
        System.out.println(ans.get(ans.size()-1));
    }

    public static int[][] swap (int[][] m,int i,int j,int p,int q) {
        int[][] m1 = new int[m.length][m[0].length];
        for (int b = 0;b<m.length;b++) {
            for (int v = 0;v<m[0].length;v++ ){
                m1[b][v] = m[b][v];
            }
        }
        int tmp = m1[i][j];
        m1[i][j] = m1[p][q];
        m1[p][q] = tmp;
        return m1;
    }

    public static int countIslands(int[][] m){
        if(m==null||m[0]==null){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0 ;i < N; i++){
            for(int j = 0 ;j < M; j++){
                if(m[i][j] == 1){
                    res ++;
                    int[] cnt = {0};
                    infect(m,i,j,N,M,cnt);
                    ans.add(cnt[0]);
                }
            }
        }
        //return res;
        Collections.sort(ans);
        if (ans.size()>=1) {
            return ans.get(ans.size()-1);
        }else
            return 0;
    }
    public static void infect(int[][] m, int i, int j, int N, int M,int[] cnt){
        if(i<0||i>=N||j<0||j>=M||m[i][j]!=1){
            return;
        }
        m[i][j] = 2;
        cnt[0]++;
        infect(m,i + 1,j,N,M,cnt);
        infect(m, i - 1, j, N, M,cnt);
        infect(m, i, j + 1, N, M,cnt);
        infect(m, i, j - 1, N, M,cnt);
    }
}
