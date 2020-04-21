package WANGYIHUYU;

import java.util.*;

public class zhenglikuaidihe {
    public static void main(String[] args) {
        Box b1 = new Box(5,4,3);
        Box b2 = new Box(5,4,5);
        Box b3 = new Box(6,6,6);
        Box[] boxes  = new Box[3];
        boxes[0] = b1;
        boxes[1] = b2;
        boxes[2] = b3;
        System.out.println(maxBoxes2(new int[][] {{5,4,3},{5,4,5},{6,6,6},{6,6,6}}));

        Scanner sc = new Scanner(System.in);

    }

    private static int maxBoxes2(int[][] boxesInt) {
        int n = boxesInt.length;
        Box[] boxes  = new Box[boxesInt.length];
        for (int i = 0;i<n;i++) {
            Box box = new Box(boxesInt[i][0],boxesInt[i][1],boxesInt[i][2]);
            boxes[i] = box;
        }
        // write code here
        Arrays.sort(boxes,new BoxComparator());

        int[] visited = new int[n];
        int res = 0;
        for (int i = 0;i<boxesInt.length;i++) {
            if (visited[i]==1) continue;
            for (int j = i+1;j<n;j++) {
                //j能装i
                if (check(boxes, j, i)&&(visited[j]==0)) {
                    visited[i] = 1;
                    visited[j] = 1;
                    res++;
                }
            }
        }
        if (res==0) return 1;
        else {
            return res*2;
        }
        //return res;
    }

    public static int maxBoxes1 (int[][] boxesInt) {

        int n = boxesInt.length;
        Box[] boxes  = new Box[3];
        for (int i = 0;i<n;i++) {
            Box box = new Box(boxesInt[i][0],boxesInt[i][1],boxesInt[i][2]);
            boxes[i] = box;
        }
        // write code here
        Arrays.sort(boxes,new BoxComparator());
        //System.out.println(boxes[2].toString());
        //return 0;
        int count = 0;
        int dp[] = new int[boxes.length];
        for (int i = 0;i<boxes.length;i++) {
            dp[i] = 1;
        }
        for (int i = 0;i<boxes.length;i++) {
            for (int j = 0;j<i;j++) {
                //是否boxes[i]>boxes[j]
                if (check(boxes,i,j)){
                    //System.out.println(check(boxes,i,j));
                    //这里dp[i]需要取最大值 因为可能前面有6个上升 自中间断了 然后接着5个上升
                    //所以在遍历[0,j]中要记录最大的dp[i]
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                    //dp[i] = dp[j+1];
                }
            }
            count = Math.max(count,dp[i]);
        }
        return count;

    }

    private static boolean check(Box[] boxes, int i, int j) {
        if (boxes[i].l>boxes[j].l&&boxes[i].m>boxes[j].m&&boxes[i].n>boxes[j].n) {
            return true;
        }
        return false;
    }

    public static class Box {
        public int l;
        public int m;
        public int n;

        public Box(int l, int m,int n) {
            this.l = l;
            this.m = m;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "l=" + l +
                    ", m=" + m +
                    ", n=" + n +
                    '}';
        }
    }
    public static class BoxComparator implements Comparator<Box> {

        @Override
        public int compare(Box o1, Box o2) {
            if (o1.l!=o2.l){
                return o1.l-o2.l;
            }
            else if (o1.m!=o2.m){
                return o1.m-o2.m;
            }
            else if(o1.n!=o2.n){
                return o1.n-o2.n;
            }
            return 0;
        }

    }
}























