package 笔试记录.爱奇艺;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class 多数元素 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // List<Integer> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            int n = s.length;
            int[] nums = new int[n];
            for (int i = 0;i<n;i++) {
                nums[i] = Integer.parseInt(s[i]);
            }

            Arrays.sort(nums);
            int mid = n/2;
            System.out.println(nums[mid]);
        }
    }
}
