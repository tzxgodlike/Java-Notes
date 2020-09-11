package 笔试记录.小米;

import java.util.Arrays;
import java.util.Scanner;

public class 单次搜索 {
    public static void main(String[] args) {
        /*
        给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格,同一个单元格内的字母不允许被重复使用。
         */
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
//        String[] s1 = sc.nextLine().split(" ");
////        String[] s2 = sc.nextLine().split(" ");
////        String[] s3 = sc.nextLine().split(" ");
////        char[][] matrix = new char[3][4];
////
////        for (int j = 0;j<4;j++) {
////            matrix[0][j] = s1[j].toCharArray()[0];
////        }
////        for (int j = 0;j<4;j++) {
////            matrix[1][j] = s2[j].toCharArray()[0];
////        }
////        for (int j = 0;j<4;j++) {
////            matrix[2][j] = s3[j].toCharArray()[0];
////        }
        char[][] matrix = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(hasPath(matrix,s));



    }

    public static boolean hasPath (char[][] matrix,String str) {
        if (matrix==null||str==null) return false;
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
        for (int i = 0;i<rows;i++) {
            for (int j = 0;j<cols;j++){
                if (hasPathCore(matrix,i,j,str,visited,pathLen))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[][] matrix, int i, int j, String str, int[][] visited, int pathLen) {
        //当遍历长度为字符串长度时，返回
        if (pathLen==str.length()) return true;
        boolean hasPath = false;
        if (i>=0&&i<matrix.length&&j>=0&&j<matrix[0].length&&
                matrix[i][j]==str.charAt(pathLen)&&(visited[i][j]==0)){
            //pathLen++;
            visited[i][j] = 1;
            //hasPathCore()有返回值 所以用一个bool值接收
            //判断能否走下一步
            hasPath = hasPathCore(matrix,i,j-1,str,visited,pathLen+1)||hasPathCore(matrix,i,j+1,str,visited,pathLen+1)
                    ||hasPathCore(matrix,i-1,j,str,visited,pathLen+1)||hasPathCore(matrix,i+1,j,str,visited,pathLen+1);
            if(!hasPath) {
                //如果这个点不能匹配 回溯
                //--pathLen;
                visited[i][j] = 0;
            }
        }
        return hasPath;
    }
}
