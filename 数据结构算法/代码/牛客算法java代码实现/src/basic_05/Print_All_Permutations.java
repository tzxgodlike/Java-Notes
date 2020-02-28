package basic_05;

import java.util.HashSet;

//字符串的全排列
public class Print_All_Permutations {
    public static void printAllPermutations1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }

    public static void process1(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            //return ; i==chs.len之后 下面的j=i 肯定不满足循环体 所以也会直接退出
        }
        for (int j = i; j < chs.length; j++) {
            swap(chs, i, j);
            process1(chs, i + 1);
            //这里不用回溯 因为第一种决策是自己与自己换
            //相当于决策树上父与左孩子一样
            //swap(chs, i, j);
        }
    }

    public static void printAllPermutations2(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0);
    }
    //不出现重复的排列
    public static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process2(chs, i + 1);
                swap(chs, i, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String test1 = "abcd";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        //printAllPermutations1(test2);
        //System.out.println("======");
        //printAllPermutations2(test2);
        //System.out.println("======");
    }
}
