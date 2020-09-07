package basic_03;

import java.util.ArrayList;
import java.util.Collections;

public class Islands {

    public static int countIslands(int[][] m){
        if(m==null||m[0]==null){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        //ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0 ;i < N; i++){
            for(int j = 0 ;j < M; j++){
                if(m[i][j] == 1){
                    res ++;
                    int[] cnt = {0};
                    infect(m,i,j,N,M);
                    //ans.add(cnt[0]);
                }
            }
        }
        return res;
        //Collections.sort(ans);
       // return ans.get(ans.size()-1);
    }
    public static void infect(int[][] m, int i, int j, int N, int M){
        if(i<0||i>=N||j<0||j>=M||m[i][j]!=1){
            return;
        }
        m[i][j] = 2;
        //cnt[0]++;
        infect(m,i + 1,j,N,M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }
    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

        for (int i = 0;i<m1.length;i++) {
            for (int j = 0;j<m1[0].length;j++) {

            }
        }
        System.out.println(countIslands(m1));

        int[][]  m3 = {{1,0,1,1},
                {1,1,0,0},{1,0,0,0},{1,1,1,1}};

        System.out.println(countIslands(m3));
//        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
//                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
//                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
//                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
//                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
//        System.out.println(countIslands(m2));

    }
}
