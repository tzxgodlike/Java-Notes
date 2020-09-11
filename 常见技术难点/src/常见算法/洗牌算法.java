package 常见算法;

import java.util.Random;

public class 洗牌算法 {
    /*
    洗牌算法的精妙在于 一般是产生一个随机数 再把这个随机数从这个数组中删去 继续下一轮 避免出现重复的牌

    用一个随机数 来产生这次要抽取的坐标
    我们可以每抽一张牌 就和数组最后的交换 同时随机的坐标范围减一  这样就不会出现重复的牌
     */

    public static void main(String[] args) {
        int[] datas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12,  13, 14, 15, 16, 17, 18, 19, 20};
        shuffle(datas);
        for (int i:datas) {
            System.out.println(i);
        }
    }

    public static void shuffle(int[] datas){
        Random random = new Random();
        for (int i = datas.length-1;i>0;i--) {
            int j = random.nextInt(i+1);
            int tmp = datas[j];
            datas[j] = datas[i];
            datas[i] = tmp;
        }
    }
}
