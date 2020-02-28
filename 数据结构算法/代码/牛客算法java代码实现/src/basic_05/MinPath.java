package basic_05;

public class MinPath {
    //暴力递归
    //从(i,j)到右下的最小路径和
    public static int walk(int[][] matrix,int i,int j){
        if(i==matrix.length&&j==matrix[0].length){
            return matrix[i][j];
        }
        //到最后一行  只能向右走 就不用比较了 直接return
        if(i==matrix.length-1){
            return matrix[i][j]+walk(matrix,i,j+1);
        }
        if(j==matrix[0].length){
            return matrix[i][j]+walk(matrix,i+1,j);
        }
        int right = walk(matrix,i,j+1);
        int down = walk(matrix,i+1,j);
        return matrix[i][j]+Math.min(right,down);
    }

    //DP 状态转移表
    public static int minPath2(int[][] m){
        if(m==null||m.length==0||m[0]==null||m[0].length==0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[row-1][col-1] = m[row-1][col-1];
        for (int i = row-1-1;i>=0;i--){
            dp[i][col-1] = m[i][col-1] + dp[i+1][col-1];
        }
        for (int j = col-1-1;j>=0;j--){
            dp[row-1][j] = m[row-1][j] + dp[row-1][j+1];
        }
        for(int i = row-2;i>=0;i--) {
            for (int j = col - 1 - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + m[i][j];
            }
        }
        return dp[0][0];
    }
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static int minPath3(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }



    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        //System.out.println(walk(m,0,0));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));

        m = generateRandomMatrix(6, 7);
        //System.out.println(walk(m,0,0));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));
    }
}
