package Chapter2;

import java.util.Arrays;

public class StringPathInMatrix_12 {
    /*
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如下面矩阵
 a b t g
 c f c s
 j d e h
 包含一条字符串"bfce"的路径，但是矩阵中不包含"abfb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     */

    /*
    回溯
     */

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

    public static void main(String[] args) {
        char[][] matrix = {{'a','b','t','g'},{'c','f','c','s'},{'j','d','e','h'}};
        System.out.println(hasPath(matrix,"jdea"));

    }
}
