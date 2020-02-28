package basic_01;

public class MaxGap {

    public static int maxGap(int[] num){
        if (num == null||num.length<2){return 0;}
        int len = num.length;
        //max初始值为小 min初始值为大
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<len;i++){
            min = Math.min(min,num[i]);
            max = Math.max(max,num[i]);
        }
        if(min==max){
            return 0;
        }
        //记录每个桶的三个值
        boolean hasNum[] = new boolean[len+1];
        int[] mins = new int[len+1];
        int[] maxs = new int[len+1];
        //桶序号
        int bid = 0;
        for(int i =0;i<len;i++){
            //得到该数应该放入哪个桶(0~len)
            bid = bucket(num[i],len,min,max);
            //若之前空 则该数为最小 若不空 则比较
            mins[bid] = hasNum[i]?Math.min(mins[bid],num[i]):num[i];
            maxs[bid] = hasNum[i]?Math.max(maxs[bid],num[i]):num[i];
            hasNum[bid] = true;
        }
        int res=0;  //保存最大值
        int lastMax = maxs[0];
        int i = 1;
        for(;i<=len;i++){
            if(hasNum[i]){
                res = Math.max(res,mins[i]-lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(long num,long len,long min,long max){
        return (int) ((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,11};
        int res = maxGap(arr);
        System.out.println(res);
        for (int i :arr) {
            System.out.println(i);
        }
    }

}
