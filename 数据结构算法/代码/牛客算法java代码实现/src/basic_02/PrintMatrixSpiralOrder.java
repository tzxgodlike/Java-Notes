package basic_02;

public class PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix){
        int tR=0;  //行
        int tC=0; //列
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while(tR<=dR&&tC<=dC){
            //在调用printEdge后变量才减一
            printEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    public static void printEdge(int[][] m,int tR, int tC, int dR, int dC){
        if(tR==dR){
            for(int i=tC;i<=dC;i++){
                System.out.println(m[tR][i]+" ");
            }
        }else if(tC==dC){
            for(int i=tR;i<=dR;i++){
                System.out.println(m[i][tC]+" ");
            }
        }else {
            int curC = tC;
            int curR = tR;
            while(curC!=dC){
                System.out.println(m[tR][curC++]+"");
            }
            while(curR!=dR){
                System.out.println(m[curR++][dR]+"");
            }
            while(curC!=tC){
                System.out.println(m[dR][curC--]+"");
            }
            while(curR!=tR){
                System.out.println(m[curR--][tC]+"");
        }
    }

    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }};
        spiralOrderPrint(matrix);

    }
}
