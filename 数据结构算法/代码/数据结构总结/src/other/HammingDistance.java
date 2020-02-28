package other;

public class HammingDistance {

    /*
    * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

    给出两个整数 x 和 y，计算它们之间的汉明距离。

    注意：
    0 ≤ x, y < 2^31.

    示例:

    输入: x = 1, y = 4

    输出: 2

    解释:
    1   (0 0 0 1)
    4   (0 1 0 0)
           ↑   ↑

上面的箭头指出了对应二进制位不同的位置。  就是找二进制上不同位的个数 用异或
    * */
    public int hammingDistance(int x, int y) {
        int a = x^y; //异或
        int ans = 0;
        while (a!=0) {
            ans++;
            a = a&(a-1);       //a&(a-1)把二进制最右边的1变为0
        }
        return 0;
    }
}
