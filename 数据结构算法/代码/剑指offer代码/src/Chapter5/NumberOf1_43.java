package Chapter5;

public class NumberOf1_43 {

    /**
     * 输入一个整数n，求1~n这n个整数的十进制表示中1出现的次数，
     * 例如输入12, 1~12中出现1的有1、10、11、12共5次
     */

    /*
    方法1： 求出每位的数字来判断
     */
    public int NumberOf1From1To(int n) {
        // 正负数不影响1的个数，统一变成非负数
        if (n < 0) n = Math.abs(n);

        int count = 0;
        // 循环求n个数字，共O(nlgn)的时间
        for (int i = 1; i <= n; i++) {
            count += numOf1(i);
        }
        return count;
    }

    /**
     * O(lgn)的复杂度求一个数中含有1的数量
     */
    private int numOf1(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 10 == 1) count++;
            n = n / 10;
        }
        return count;
    }

    /*
    方法2：使用StringBuilder将所有数字拼接，无脑数数
     */

    public int numOf1Between1AndN(int n) {
        // 正负数不影响1的个数，统一变成非负数
        if (n < 0) n = Math.abs(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }

        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    /*
    方法3： //	对于整数n，我们将这个整数分为三部分：当前位数字cur，更高位数字high，更低位数字low，
//如：对于n=21034，当位数是十位时，cur=3，high=210，low=4。
//	我们从个位到最高位 依次计算每个位置出现1的次数：
//	1）当前位的数字等于0时，例如n=21034，在百位上的数字cur=0，百位上是1的情况
//有：00100~00199，01100~01199，……，20100~20199。一共有21*100种情况，即high*100;
//	2）当前位的数字等于1时，例如n=21034，在千位上的数字cur=1，千位上是1的情况
//有：01000~01999，11000~11999，21000~21034。一共有2*1000+（34+1）种情况，
//即high*1000+(low+1)。
//	3）当前位的数字大于1时，例如n=21034，在十位上的数字cur=3，十位上是1的情况
//有：00010~00019，……，21010~21019。一共有（210+1）*10种情况，即(high+1)*10。
//这个方法只需要遍历每个位数，对于整数n，其位数一共有lgn个，所以时间复杂度为O(logn)。

     */

    public int numberOf1(int n) {       //n = 21234
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) {   //m=1
            long a = n / m;                  // a=21234
            long b = n % m;                  //b=0
            if (a % 10 == 0) ones += a / 10 * m; //
            else if (a % 10 == 1) ones += (a / 10 * m) + (b + 1);
            else ones += (a / 10 + 1) * m;
        }
        return ones;
    }
}
