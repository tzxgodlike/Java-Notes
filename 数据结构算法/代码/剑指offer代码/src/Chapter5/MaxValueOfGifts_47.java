package Chapter5;

public class MaxValueOfGifts_47 {

    /**
     * 在一个mxn的棋盘的每一格斗放油一个礼物，每个礼物都有一定的价值（大于0）
     * 从棋盘的左上角开始，每次可以往右边或者下边移动一格，知道到达棋盘的右下角。
     * 给定一个棋盘和上面的礼物，计算我们最多可以拿到多少价值的礼物
     */

    /**
     * 方法一：递归，两个方向的深度优先搜索，用一个对象数组保存最大值（只需一个长度）
     */
    public static int getMax (int[][] gifts) {
        if (gifts == null || gifts.length == 0) return 0;

        //要把max到select函数中  因为Java是值传递 所以要传个引用类型进去 所以传大小为1的数组
        int[] max = {0};
        int rows = gifts.length;   //行数
        int cols = gifts[0].length;  //列数
        select (gifts,0,0,rows,cols,0,max);

        return max[0];
    }

    private static void select(int[][] gifts, int row, int col, int rows,int cols,int value, int[] max) {

        if (row >= rows || col >= cols) return;

        value += gifts[row][col];

        if (row == rows-1&& col==cols-1) {
            max[0] = Math.max(max[0],value);
            return ;
        }
        //穷举
        //向右
        select(gifts,row,col+1,rows,cols,value,max);
        //向下      都是值传递 不用回溯
        select(gifts,row+1,col,rows,cols,value,max);

    }

    /**
     * 方法2：动态规划，到达f(i,j)处拥有的礼物价值和有两种情况：
     * 1、从左边来，即f(i, j) = f(i, j -1) + gift(i, j)
     * 2、从上边来，即f(i, j) = f(i -1, j) + gift(i, j)
     *
     * 保证到达每一个格子得到的礼物价值之和都是最大的，也就是取max[f(i, j-1), f(i-1, j)] +gift(i, j)
     * 可以发现，要知道当前格子能获得最大礼物价值，需要用到当前格子左边一个和上面一个格子的最大礼物价值和
     */
    public static int  getMaxDP(int[][] gifts) {
        if (gifts == null || gifts.length == 0) return 0;

        int rows = gifts.length;   //行数
        int cols = gifts[0].length;  //列数

        int[][] dp = new int[rows][cols];

        dp[0][0] = gifts[0][0];
        for (int i = 1;i<rows;i++){
            dp[i][0] = gifts[i][0] + dp[i-1][0];
        }
        for (int j = 1;j<cols;j++){
            dp[0][j] = gifts[0][j] + dp[0][j-1];
        }
        for(int i = 1;i<rows;i++) {
            for (int j = 1; j <cols; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + gifts[i][j];
            }
        }
        return dp[rows-1][cols-1];


    }
    public static int getMaxVal(int[][] gifts) {
        int rows = gifts.length;   //行数
        int cols = gifts[0].length;  //列数
        if (gifts == null || gifts.length == 0) return 0;
        int[][] maxVal = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int left = 0;
                int up = 0;
                if (row > 0) up = maxVal[row -1][col];
                if (col > 0) left = maxVal[row][col -1];
                maxVal[row][col] = Math.max(up, left) + gifts[row][col];
            }
        }
        return maxVal[rows-1][cols-1];
    }

    public static void main(String[] args) {

        int[][] gifts = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        System.out.println(getMax(gifts));
        System.out.println(getMaxDP(gifts));
        //System.out.println(getMaxVal(gifts, 4, 4));
        //System.out.println(betterGetMaxVal(gifts, 4, 4));

    }
}
