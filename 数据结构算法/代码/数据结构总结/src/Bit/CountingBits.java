package Bit;

public class CountingBits {

    /*
    leetcode.338
    给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

    示例 1:

    输入: 2
    输出: [0,1,1]

     */
    /*
    思路1. num&(num-1)来消除1 计数
     */
    public static int[] countBits(int num) {
        //List<Integer> temp = new ArrayList<>();
        int[] res = new int[num+1];
        for(int i = 0;i<=num;i++){
            int cnt = 0;
            int k = i;
            while(k!=0) {
                k &=(k-1);
                cnt++;
            }
            res[i] = cnt;
        }
        return res;
    }

    //思路2： DP  有三种状态转移方程
    // https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode/

    public static void main(String[] args) {
        int[] res = countBits(2);
        System.out.println(res);
    }
}
