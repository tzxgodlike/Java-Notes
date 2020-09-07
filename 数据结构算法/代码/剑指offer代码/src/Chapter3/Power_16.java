package Chapter3;

public class Power_16 {
    /*
    求一个数的整数次方

    1.首先考虑边界情况
    输入为负数  先求绝对值 再求倒数
    输入为0 返回0
    2.(a,n) 即把a自乘n次
    n为偶数时，a^n = a^(n/2)*a(n/2)
    n为奇数时，a^n = a^(n-1/2)*a(n-1/2)*a
     */

    static boolean InvalidInput = false;  //判断函数是否出错

    public static double Power(double base,int exponent) {
        InvalidInput = false;

        if (base == 0) return 0;
        int positiveExponent = Math.abs(exponent);
        double result = powerUnsigned(base, positiveExponent);;
        return exponent < 0 ? 1 / result : result;

    }

    public static double powerUnsigned (double base,int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerUnsigned(base, exponent >> 1);
        result *= result;
        if ((exponent & 1) == 1) {
            result *= base;
        }
        return result;
    }

    /*
    快速幂
     */
    public double myPow(double x, int n) {

        double res = 1;
        while (n>0) {
            //n为奇数 一共会执行这语句两次 最后n==1时总会执行一次
            if ((n&0x01)==1)  res *=x;
            x *= x;
            n = n>>1;
        }

        return res;
    }
}
