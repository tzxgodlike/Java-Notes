public class FindIn2DArray_04 {
    /**
     * 更推荐的做法，由于矩阵从上到下递增，从左到右递增。
     * 总是和二维矩阵的右上角元素比较（对称地，左下角也可以）
     * 如果目标比右上角的大，删除该行，行指针向下移动；如果比右上角的小，删除该列，列指针左移
     */
    public boolean Find(int target, int[][] array) {
        if (array != null && array.length > 0) {
            int N = array.length; // 总行数
            int col = array[0].length - 1; // 列索引
            int row = 0; // 行索引
            // array[row][col]是右上角的元素
            while (row < N && col >= 0) {
                if (target < array[row][col]) {
                    col--;
                } else if (target > array[row][col]) {
                    row++;
                } else {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
