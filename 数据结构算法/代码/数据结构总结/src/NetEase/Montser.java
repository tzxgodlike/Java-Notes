//package NetEase;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//class Node {
//    int row;
//    int cloumm;
//    int round;
//
//    public Node(int row, int cloumm, int round) {
//        super();
//        this.row = row;
//        this.cloumm = cloumm;
//        this.round = round;
//    }
//
//}
//
//public class Montser {
//
//
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        int[][] matrix = new int[n][m];
//
//        for (int i = 0;i<n;i++){
//            for (int j = 0;j<m;j++){
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//
//        hasPath(matrix);
//    }
//
//    public static int hasMonster(int[][] matrix) {
//        //if (matrix==null) return 0;
//        int rows = matrix.length;  //行
//        int cols = matrix[0].length;   //列
//
//        //记录该点是否被访问
//        int[][] visited = new int[rows][cols];
//        for (int i =0;i<rows;i++){
//            Arrays.fill(visited[i],0);
//        }
//        //已遍历路径长度
//        int pathLen = 0;
//        int[][] res = new int[rows][cols];
//        //以所有点为起点 开始遍历
//        for (int i = 0;i<rows;i++) {
//            for (int j = 0;j<cols;j++){
//                if (BFS(matrix,i,j,visited,pathLen))
//                    res[i][j] = pathLen;
//            }
//        }
//        return pathLen;
//    }
//
//    private static boolean BFS(int[][] matrix, int i, int j, int[][] visited, int pathLen) {
//
//        int next[][] = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };// 4个方向
//
//        Queue<Node> q = new LinkedList<>();
//
//        Node start = new Node(0, 0, 0);
//        q.offer(start);
//        while (!q.isEmpty()) {
//
//            Node temp = q.poll();
//
//        }
//
//    }
//
//}
