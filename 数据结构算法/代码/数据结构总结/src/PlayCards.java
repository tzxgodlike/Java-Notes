import java.util.Scanner;

public class PlayCards {
    static int[] card = new int[10];
    private static int min = Integer.MAX_VALUE;
    /*
    给定一堆牌 大小从1到10  可以出单子 5连子 对子 数组为每个位置的张数 给出最小多少次出完
      */
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        for (int i = 0;i<10;i++) {
//            card[i] = sc.nextInt();
//        }
        int[] arr = {2,2,1,1,1,1,1,0,0,0};
        int cnt = getCount(arr);
        System.out.println(cnt);
    }

    private static int getCount(int[] arr) {
        card = arr;
        backtrace(0,0);
        return min;
    }

    //n是当前最小的牌的大小 count为次数  典型的回溯法 选一个出法之后要还原
    private static void backtrace(int n, int count) {
        if (n>=10) {
            min = Math.min(min,count);
            return ;
        }
        if (card[n]==0) {
            backtrace(n+1,count);
            return ;
        }
        int one = getContinue(n);
        if (one > 0) {
            //打连子 n后面5个位置减1
            divide(n,1,5);
            backtrace(n,count+1);
            //回溯
            add(n,1,5);
        }
        if (card[n]>=2){
            //可以打对子
            card[n] -= 2;
            backtrace(n,count+1);
            //回溯
            card[n] += 2;
        }
        {
            card[n]--;
            backtrace(n,count+1);
            card[n]++;
        }
    }

    private static void add(int n, int m, int t) {
        for (int i = n;i<n+t;i++) {
            card[i] += m;
        }
    }

    private static void divide(int n, int m, int t) {
        for (int i = n;i<n+t;i++) {
            card[i] -= m;
        }
    }

    private static int getContinue(int n) {
        //判断顺子
        if (n+1>6) {
            return 0;
        }
        int min = 5;
        for (int i = n;i<n+5;i++){
            min = Math.min(min,card[i]);
        }
        return min;  //min为0 说明没连子
    }
}
