package HuaWei;

import java.util.Scanner;

public class HighestScore {
    public static void main(String[] args) {
        int M=0,N=0;
        int A=0,B=0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            N = sc.nextInt();
            M = sc.nextInt();
            int[] score = new int[N];
            for (int i = 0;i < N;i++) {
                score[i] = sc.nextInt();
            }
            String c = null;
            for (int i = 0;i < M;i++) {
                c = sc.next();
                A = sc.nextInt();
                B = sc.nextInt();
                process(score,c,A,B);
            }
        }
    }

    private static void process(int[] score, String c, int a, int b) {
        int start,end;
        if (c.equals("Q")) {
            //A可能比B大
            start = Math.min(a,b)-1;
            end = Math.max(a,b);
            int max = score[start];
            for (int i = start;i<end;i++) {
                if (max<score[i]) max = score[i];
            }
            System.out.println(max);
        }else if (c.equals("U")) {
            score[a-1] = b;
        }
    }
}



