package 笔试记录.MeiTuan;

import java.math.BigInteger;
import java.util.*;

public class 排队2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();  //点名多少次
        LinkedHashSet<BigInteger> set= new LinkedHashSet<>();
        //HashSet<BigInteger> set = new HashSet<>();
        //Deque<BigInteger> queue = new LinkedList<>();
        for(int i =0;i<m;i++) {
            BigInteger no = sc.nextBigInteger();
            if (!set.contains(no)) {
                //set.addFirst(no);
                set.add(no);
            }else {
                set.remove(no);
                set.add(no);
            }
        }
        List<BigInteger> res = new ArrayList<>();

        for (BigInteger num:set) {
            res.add(num);
        }
        Collections.reverse(res);
        for (BigInteger num:res) {
            System.out.println(num);
        }
    }
}
