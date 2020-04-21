package Chapter4;

import java.util.ArrayList;
import java.util.List;

public class PrintMatrix_29 {
    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     *
     * 画图找过程
     */
    public static List<Integer> PrintMatrixClockWisely (int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        //每次打印一圈 找到每圈的四个顶点
        int left = 0,right = matrix[0].length-1,top = 0,bottom = matrix.length-1;

        while (left<=right&&top<=bottom) {
            //从左往右 先打印完一行
            for (int i = left;i<=right;i++) list.add(matrix[top][i]);
            //再从右上到右下 至少两行才继续打印
            if (bottom>top) {
                for (int i = top+1;i<=bottom;i++) list.add(matrix[i][right]);
            }
            //从右下打印到左下  至少两行两列
            if (bottom>top&&left<right) {
                for (int i =right-1;i>=left;i-- )  list.add(matrix[bottom][i]);
            }
            //从左下打印到左上 至少三行两列
            if (bottom>top+1&&left<right) {
                for(int i = bottom-1;i>top;i--) list.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }
}
