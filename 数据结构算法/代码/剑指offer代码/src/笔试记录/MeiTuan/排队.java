package 笔试记录.MeiTuan;

import java.math.BigInteger;
import java.util.*;

public class 排队 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();  //点名多少次
         HashSet<BigInteger> set = new HashSet<>();
        Deque<BigInteger> queue = new LinkedList<>();
        for(int i =0;i<m;i++) {
            BigInteger no = sc.nextBigInteger();
            if (!set.contains(no)) {
                queue.addFirst(no);
                set.add(no);
            }else {
                queue.remove(no);
                queue.addFirst(no);
            }
        }

        for (BigInteger num:queue) {
            System.out.println(num);
        }
    }
}
