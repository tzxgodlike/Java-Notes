package basic_02;

//旋转矩阵
public class RotateMatrix {
    //每次旋转一个边界
    public static void rotate(int[][] matrix){
        int tR = 0; //行
        int tC = 0; //列
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        //长方形 只用判断2个点 且不可能相等
        while (tR<dR){
            rotateEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    public static void rotateEdge(int[][] m,int tR,int tC,int dR,int dC){
        //每次找四个点 计算总共找的次数 为边长减1
        int times = dR-tR;
        int tmp = 0;
        for(int i = 0;i<times;i++){
            tmp = m[tR][tC+i];
            m[tR][tC+i] = m[dR-i][tC];
            m[dR-i][tC] = m[dR][dC-i];
            m[dR][dC-i] = m[tR+i][dC];
            m[tR+i][dC] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix){
        for(int i = 0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.println(matrix[i][j]);
            }
            //System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4}, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
