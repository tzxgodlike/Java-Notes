package HighFrequencyProblems.BinarySearchUse;

public class EatBanana {
    /*
    leetcode.875
    珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

    珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

    珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

    返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

     

    示例 1：

    输入: piles = [3,6,7,11], H = 8
    输出: 4

     */

    /*
    这种题首先想暴力解法  一次只能吃一堆
    设最多的那堆中有n个香蕉  那么speed最小是1，最大肯定为n 那么遍历1-n 就能找出满足条件的值
     */
    public static int FindSpeed(int[] piles,int H) {
        int n = getMax(piles);
        for (int i = 1;i<=n;i++) {
            if(canFinsh(i,H,piles)) return i;
        }
        return n;
    }

    private static int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int i : piles) {
            max = Math.max(max,i);
        }
        return max;
    }

    public static boolean canFinsh(int speed,int h,int[] piles) {
        //计算每堆要吃几次 再汇总起来
        int time = 0;
        for (int n :piles) {
            time += timeOf(n,speed);
        }
        return time<=h;
    }

    private static int timeOf(int n, int speed) {
        //刚好整除就不用加1
        return n/speed + (n%speed==0?0:1);
    }


    /*
    优化：speed遍历1到n的过程就是一个遍历有序数组的过程 可以用二分查找来优化
    类似于找==target的最左边界
     */
    public static int minEatingSpeed(int[] piles,int H) {
        int n = getMax(piles);
        int l = 1,r = n;
        while(l<=r) {
            //这里要两层括号 不然+l会失效
            int mid = l + ((r - l) >> 1);
            if (canFinsh(mid,H,piles)) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String[] args) {
        int[] piles = {3,6,7,11};
        System.out.println(minEatingSpeed(piles,8));

    }
}
