import java.awt.*;
import java.lang.reflect.Array;
import java.util.HashSet;

/*
    有一系列数字，比如67182152391，这样11位数字，可以拼出多少个用户的手机号码？
    要求:
    手机号码的需要是158,139开头的，格式类似于:158-6712-2319
    输出手机号个数
 */
public class Phone {
    static int  res= 0;

    public static int PhonePermutations(int[] num) {
        if(num==null||num.length<3) throw new RuntimeException("输入错误");
        for (int i = 0;i<num.length;i++) {
            if (num[i]==1) swap(num,0,i);
            if (num[i]==5) swap(num,1,i);
            if (num[i]==8) swap(num,2,i);
        }
        if(num[0]==1&&num[1]==5&&num[2]==8){
            process(num, 3);
            //System.out.println(res);
        }

        for (int i = 0;i<num.length;i++) {
            if (num[i]==1) swap(num,0,i);
            if (num[i]==3) swap(num,1,i);
            if (num[i]==9) swap(num,2,i);
        }

        if(num[0]==1&&num[1]==3&&num[2]==9){

            process(num, 3);
            //System.out.println(res);
        }
        return res;
    }
    public static void process(int[] num, int i) {
        if (i == num.length-1) {
            res++;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int j = i; j < num.length; j++) {
            if (!set.contains(num[j])) {
                set.add(num[j]);
                swap(num, i, j);
                process(num, i + 1);
                swap(num, i, j);
            }
        }
    }

    public static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    public static void main(String[] args) {
        int[] n = {1,3,9,5,6,7};

        int num = PhonePermutations(n);
        System.out.println(num);
    }


}
