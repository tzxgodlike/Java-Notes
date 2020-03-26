package Chapter2;

import java.util.Arrays;

public class RobotMove_13 {
    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     */
    /*
    回溯
     */
    public static int movingCount (int[][] matrix,int threshold) {
        if (threshold<0||matrix==null) return 0;
        //重复到达的格子不重复计数
        int[][] visited = new int[matrix.length][matrix[0].length];
        for (int i = 0;i<matrix.length;i++){
            Arrays.fill(visited[i],0);
        }
        int count = movingCountCore(matrix,0,0,visited,threshold);
        return count;
    }

    private static int movingCountCore(int[][] matrix, int i, int j, int[][] visited, int threshold) {
        int count = 0;
        if (check(matrix,i,j,threshold,visited)){
            visited[i][j] = 1;
            count = 1 + movingCountCore(matrix,i,j+1,visited,threshold)
                    +movingCountCore(matrix,i,j-1,visited,threshold)
                    +movingCountCore(matrix,i-1,j,visited,threshold)
                    +movingCountCore(matrix,i+1,j,visited,threshold);
        }
        return count;
    }

    private static boolean check(int[][] matrix, int i, int j, int threshold,int[][] visited) {
        if (i>=0&&i<matrix.length&&j>=0&&j<matrix[0].length&&
                (getDigitSum(i)+getDigitSum(j)<=threshold)&&(visited[i][j]==0)) {
            return true;
        }
        return false;
    }

    private static int getDigitSum(int n) {
        int sum = 0;
        while (n>0){
            sum += n%10;
            n = n/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};
        System.out.println(movingCount(matrix,15));
    }
}
