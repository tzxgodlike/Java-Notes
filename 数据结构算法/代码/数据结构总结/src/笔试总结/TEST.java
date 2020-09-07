package 笔试总结;

import java.util.ArrayList;
import java.util.List;

public class TEST {
    public static void main(String[] args) {
        int a = 0;
        Integer b = null;
        Integer  c = 127;
        Integer d = new Integer(127);
        //System.out.println(a==b); //空指针异常
        //System.out.println(c== d); //false new 出来的不在缓存中

        System.out.println(replaceSpace("we are happy"));


        //测试数组新引用修改 能否影响原数组
        int[] aa = {1,2,3};
        int[] bb = aa;
        //clone()的出的数组不会影响原数组
        int[] cc = aa.clone();
        bb[0] = 2;

        System.out.println("aa[0]:"+aa[0]);
        cc[0] = 3;
        System.out.println("aa[0]:"+aa[0]);

        //但是二维的数组 clone() 会影响原数组
        int[][] aaa = new int[][]{{1,1,1},{1,1,1}};
        int[][] ccc = aaa.clone();
        ccc[0][0] = 2;
        System.out.println("aaa[0][0]:"+aaa[0][0]);

        //快速幂
        //myPow(2,3);

        //全排列
        int[] loop = {0,1,2,3,4,5,6,7,8,9};
        //因为loop中有0 所以可以生成0到999的所有树
        System.out.println(quanpailie(3,loop));

    }

    //1到n位数的全排列
    public static List<String> quanpailie(int n,int[] loop) {
        int[] res;
        char[] num = new char[n];
        List<String> list = new ArrayList<>();
        dfs(num,list,0,n,loop);
        return list;
     }

    private static void dfs(char[] num, List<String> list, int i,int n,int[] loop) {
        if (i>=n) {
            list.add(String.valueOf(num));
            return;
        }
        for (int digit:loop) {
            num[i] = (char)(digit+'0');
            //System.out.println(num[i]);
            dfs(num,list,i+1,n,loop);
        }
    }

    static double  myPow(double x, int n) {
        //快速幂
        //每一步都把指数分成两半，而相应的底数做平方运算
        double res = 1.0;
        int t = n;
        while(n>0)
        {
            //n到奇数时 就乘上结果
            if((n&1)==1) res *= x;
            //n缩减为偶数时 x就翻倍
            x *= x;
            n /= 2;
        }
        return t > 0? res : 1.0 / res;
    }
    public static String replaceSpace(String s) {
        int cnt = 0;//空格的个数
        for (int i = 0;i<s.length();i++) {
            if (s.charAt(i)==' ') cnt++;
        }
        char[] c1 = s.toCharArray();
        char[] c2 = new char[s.length()+2*cnt];
        int p = c1.length-1,q = c2.length - 1;
        while(p>=0&&q>=p) {
            if (c1[p]!=' ') c2[q--] = c1[p];
            else {
                c2[q--] = '0';
                c2[q--] = '2';
                c2[q--] = '%';
            }
            p--;
        }
        return new String(c2);
    }
}
