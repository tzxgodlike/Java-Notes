package Chapter5;

public class UglyNumber_49 {

    /*
    把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
    习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     */

    /*
    方法1. 遍历每个数 找到第1500个
     */

    /*
    方法2： 空间换时间  创建一个数组 是排好序的丑数 然后再一个个生成
     */

    public static int uglyNumber(int index) {
        if (index <= 0) return 0;

        int t2 = 0,t3 = 0,t5 = 0;
        int[] res = new int[index];

        res[0] = 1;

        for (int i = 1;i<index;i++) {
            int m1 = res[t2] * 2;
            int m2 = res[t3] * 3;
            int m3 = res[t5] * 5;
            res[i] = Math.min(m1,Math.min(m2,m3));
            // 选择某个丑数后ti * i，指针右移从丑数集合中选择下一个丑数和i相乘
            // 注意是三个连续的if，也就是三个if都有可能执行。这种情况发生在三个候选中有多个最小值，
            // 指针都要右移，不然会存入重复的丑数
            if (res[i]==m1) t2++;
            if (res[i]==m2) t3++;
            if (res[i]==m3) t5++;
        }
        return res[index-1];
    }

    public static void main(String[] args) {
        System.out.println(uglyNumber(3));
    }
}
