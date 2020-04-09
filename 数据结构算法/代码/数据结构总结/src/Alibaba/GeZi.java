package Alibaba;

import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.*;

public class GeZi {

    //public static HashSet<Integer> set = new HashSet<>();
    static List<Integer> reslist = new LinkedList<>();

    public static void hasPath (int[][] matrix,int k) {
        if (matrix==null) return ;
        int rows = matrix.length;
        int cols = matrix[0].length;
        //记录该点是否被访问
        int[][] visited = new int[rows][cols];
        for (int i =0;i<rows;i++){
            Arrays.fill(visited[i],0);
        }
        //已遍历路径长度
        int pathLen = 0;
        //以所有点为起点 开始遍历

        movingCountCore(matrix,0,0,1,k);



        return ;
    }

    private static void movingCountCore(int[][] matrix, int i, int j,int res,int k) {

        //int count = 0;
        //int res = 0;
//        if (i>=0&&i<matrix.length&&j>=0&&j<matrix[0].length) {
//            reslist.add(res);
//            return;
//        }

//        if (i<0||i>matrix.length||j<0||j>matrix[0].length) {
//            return;
//        }
        if (hasNoPath(matrix,i,j)) {
            reslist.add(res);
            return;
        }
        int cur = matrix[i][j];

        if (i+k>=0&&i+k<matrix.length&&j>=0&&j<matrix[0].length&&matrix[i+k][j]>cur){
            movingCountCore(matrix,i+k,j,res+matrix[i+k][j],k);
        }
        if (i-k>=0&&i-k<matrix.length&&j>=0&&j<matrix[0].length&&matrix[i-k][j]>cur){
            movingCountCore(matrix,i-k,j,res+matrix[i-k][j],k);
        }
        if (i>=0&&i<matrix.length&&j+k>=0&&j+k<matrix[0].length&&matrix[i][j+k]>cur){
            movingCountCore(matrix,i,j+k,res+matrix[i][j+k],k);
        }
        if (i>=0&&i<matrix.length&&j-k>=0&&j-k<matrix[0].length&&matrix[i][j-k]>cur){
            movingCountCore(matrix,i,j-k,res+matrix[i][j-k],k);
        }
//        if (check(matrix,i,j,pre)) {
//            //visited[i][j] = k;
//            movingCountCore(matrix,i,j+k,pre,res+matrix[i][j+k]);
//            movingCountCore(matrix,i,j-k,pre,res+matrix[i][j-k]);
//            movingCountCore(matrix,i-k,j,pre,res+matrix[i-k][j]);
//            movingCountCore(matrix,i+k,j,pre,res+matrix[i+k][j]);
        //}
//        else {
//            reslist.add(res);
//        }


    }

    private static boolean hasNoPath(int[][] matrix, int i, int j) {
        if(i-1>=0&&matrix[i][j]<matrix[i-1][j]) {
            return false;
        }
        if(i+1<matrix.length&&matrix[i][j]<matrix[i+1][j]) {
            return false;
        }
        if(j+1<matrix[0].length&&matrix[i][j]<matrix[i][j+1]) {
            return false;
        }
        if(j-1>=0&&matrix[i][j]<matrix[i][j-1]) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        for (int i = 0;i<t;i++) {
//            int n = sc.nextInt();
//            int k = sc.nextInt();
//            int[][] matrix = new int[n][n];
//
//            for (int j = 0;j<n;j++) {
//                for (int l=0;l<n;l++) {
//                    matrix[j][l] = sc.nextInt();
//                }
//            }
//            //hasPath(matrix);
//            //System.out.println(reslist);
            int[][] m1 = {{1,2,5},{10,11,6},{12,12,7}};
            int[][] m = {{1,2},{3,4}};
            hasPath(m1,1);
            int max = 0;
            for (int h = 0;h<reslist.size();h++) {
                max = Math.max(max,reslist.get(h));
            }
            System.out.println(reslist);
            System.out.println(max);
        //}
        //int[][] m = {{k,2,5},{k0,kk,6},{k2,k2,7}};


    }
}
