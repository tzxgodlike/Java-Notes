package Tencent;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class queue_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0;i<n;i++) {
            int m = sc.nextInt();    //操作次数
            LinkedList<Integer> queue = new LinkedList<>();
            sc.nextLine();

            for (int j = 0; j < m; j++) {
                String[] s = sc.nextLine().split(" ");
                if (s[0].equals("PUSH")) {
                    queue.add(Integer.parseInt(s[1]));
                } else if (s[0].equals("TOP")) {
                    //System.out.println(queue.isEmpty());
                    if (queue.isEmpty()) System.out.println("-1");
                    else {
                        System.out.println(queue.peekFirst());
                    }
                } else if (s[0].equals("POP")) {
                    if (queue.isEmpty()) System.out.println("-1");
                    else {
                        queue.pollFirst();
                        //System.out.println(queue.size());
                    }
                } else if (s[0].equals("SIZE")) {
                    System.out.println(queue.size());
                } else if (s[0].equals("CLEAR")) {
                    queue.clear();
                }
            }

        }
    }
}
