package basic_05;

import java.util.ArrayList;
import java.util.List;

public class Print_All_Subsquences {

    //子集问题  回溯法
    private static void printAllSub(char[] arr,int index,String res){
        if(index==arr.length){
            System.out.println(res);
            //return ; //这里是否需要加return要看情况
            //要看该函数是否需要中断 若下面删掉else
            //当index==arr.len之后不return的话会继续执行printAllSub导致溢出
        }else{
            printAllSub(arr,index+1,res);
            //下面这句并没有改变res的值 所以不需要回退
            printAllSub(arr,index+1,res+String.valueOf(arr[index]));
        }

    }

    public static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<Integer> temp = new ArrayList<>();

        process(0,nums,temp);
        return res;
    }
    //leetcode.78
    public static void process (int index,int[] nums,List<Integer> temp) {
        if (index == nums.length) {
            System.out.println(temp);
            res.add(temp);

        }else {

            process(index+1,nums,temp);
            //这里改变了temp的值 而temp并不是一个局部变量 所以需要回溯
            temp.add(nums[index]);
            process(index+1,nums,temp);
            temp.remove(temp.size()-1);   //关键  意思是第三层的一个分支走完后会回退到第二步 那么第三层的决策要撤销掉
        }
    }

    public static void main(String[] args) {
        String test = "abc";
        char[] str = test.toCharArray();
        String res = "";
        printAllSub(str,0,res);
//        int[] nums = {1,2,3};
//        subsets(nums);
//        for (int i = 0;i<res.size();i++){
//            for(int j = 0;j<res.get(i).size();j++){
//                //System.out.println(res.get(i).get(j));
//            }
//        }
    }
}
