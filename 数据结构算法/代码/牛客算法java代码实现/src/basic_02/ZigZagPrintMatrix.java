package basic_02;

public class ZigZagPrintMatrix {

    public static void printMatrixZ(int[][] matrix){
       int AR = 0; //A行
       int AC = 0; //列
       int BR = 0;//B
       int BC = 0;
       int endR = matrix.length-1;
       int endC = matrix[0].length-1;

       boolean fromUp = false;
       //?
       while(AR!=endR+1){
          printLevel(matrix,AR,AC,BR,BC,fromUp);
          AR = AC==endC?AR+1:AR;
          AC = AC==endC?AC:AC+1;
          //这两句不能颠倒 变得是行数 所以行数最后判断

          BC = BR==endR?BC+1:BC;
          BR = BR==endR?BR:BR+1;
          fromUp = !fromUp;
       }
        System.out.println();
   }
   public static void printLevel(int[][] m,int AR,int AC,int BR,int BC,boolean fromUp){
       if(fromUp){
           while(AR!=BR+1){
               System.out.print(m[AR++][AC--]+" ");
           }
       }else{
           while (BR!=AR-1){
               System.out.print(m[BR--][BC++]+" ");
           }
       }
   }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZ(matrix);

    }
}
