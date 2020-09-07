package 笔试记录.JD;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Num {
    /*
    2 3 5组成的数 如22 222 2233 2225 5 2 3 等 找到第N大的数
    直接暴力 求出所有的数 然后排序
     */

    public static List<BigInteger> res = new ArrayList<>();

    public static List<BigInteger> subsets(int[] nums) {

        StringBuilder temp = new StringBuilder();

        backtrack(nums,temp,0);
        return res;
    }

    //1.回溯   每个结点都作为结果  逻辑是先求个数为0的子集  再求个数为1的子集  再求.....
    public static void backtrack (int[] nums,StringBuilder temp,int cnt) {
        //if (index > nums.length) return;
        if (cnt>=5) {
            //System.out.println("end");
            return;
        }
        if (!temp.toString().equals(""))
        res.add(new BigInteger(temp.toString()));
        //if (index<nums.length) {
        for (int i =0; i < nums.length; i++) {

            temp.append(nums[i]);
            backtrack( nums, temp,++cnt);
            //temp.substring(0,temp.length() - 2);
            temp.deleteCharAt(temp.length()-1);
        }
        //}

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[] {2,3,5};

        subsets(num);
        //res.remove(0);
        Collections.sort(res);
        List<BigInteger> myList = res.stream().distinct().collect(Collectors.toList());
        //return myList ;
        //System.out.println(myList);
        //System.out.println(myList.size());
        //System.out.println(myList.get(3));
    }
}
