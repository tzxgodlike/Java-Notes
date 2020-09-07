package 笔试记录.wangyihuyu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MiGong {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  //t组数据
        for (int times = 0;times<t;times++) {
            int n = sc.nextInt();  //有n行操作
            int[][] matrix = new int[2*n+1][2*n+1];
            //起点坐标为(n,n)
            for (int c = 0;c<2*n+1;c++) {
                Arrays.fill(matrix[c],-1);
            }
            matrix[n][n] = 1;
            int curX = n,curY = n;
            int endX = -1,endY = -1;
            for (int i = 0;i<n;i++) {
                int dir = sc.nextInt();
                int can = sc.nextInt();
                if (can==1) {
                    if (dir==0) matrix[--curX][curY] = 1;
                    if (dir==1) matrix[++curX][curY] = 1;
                    if (dir==2) matrix[curX][--curY] = 1;
                    if (dir==3) matrix[curX][++curY] = 1;
                }
            }
            endX = curX;
            endY = curY;
            //System.out.println(endX+" "+endY);
            int[][] visited = new int[2*n+1][2*n+1];
            for (int i =0;i<2*n+1;i++){
                Arrays.fill(visited[i],0);
            }
            ArrayList<Integer> res = new ArrayList<>();
            hasPathCore(matrix,n,n,endX,endY,visited,0,res);
            Collections.sort(res);
            System.out.println(res.get(0));

        }
    }

    private static void hasPathCore(int[][] matrix, int i, int j, int endX, int endY, int[][] visited, int len,ArrayList<Integer> res) {
        //当遍历长度为字符串长度时，返回
        if (i==endX&&j==endY) {
            res.add(len);
            return;
        }
        //boolean hasPath = false;
        if (i>=0&&i<matrix.length&&j>=0&&j<matrix[0].length&&matrix[i][j]==1&&(visited[i][j]==0)){
            //pathLen++;
            visited[i][j] = 1;
            //hasPathCore()有返回值 所以用一个bool值接收
            //判断能否走下一步
            hasPathCore(matrix,i,j-1,endX,endY,visited,len+1,res);
            hasPathCore(matrix,i,j+1,endX,endY,visited,len+1,res);
            hasPathCore(matrix,i-1,j,endX,endY,visited,len+1,res);
            hasPathCore(matrix,i+1,j,endX,endY,visited,len+1,res);

            visited[i][j] = 0;

        }
        return ;
    }
}
