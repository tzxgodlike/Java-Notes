public class DuplicationInArray_03_01 {
    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2或者3。
     */

    //此题关键在于长度为n 数字范围在0-n-1 说明
    //若没有重复数字 排序之后num[i] = i
    public static boolean duplicate(int[] numbers,int length,int[] duplication){
        if(numbers ==null ||length<=0){
            return false;
        }
        for (int i = 0;i < length;i++){
            if (numbers[i] < 0 || numbers[i] > length -1) {
                return false;
            }
        }
        for(int i = 0;i<length;i++){
            while(numbers[i]!=i){
                if(numbers[i]==numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers,numbers[i],i);
            }
        }
        return false;
    }
    /**
     * 推荐的做法，通过交换元素，将值i保存到numbers[i]
     * 在numbers[i]不和i相等时，如果numbers[i]和numbers[numbers[i]]相等就说明重复元素；
     * 否则就交换这两个元素，这个过程相当于排序
     */



    private static void swap(int[] numbers, int p, int q) {
        int temp = numbers[p];
        numbers[p] = numbers[q];
        numbers[q] = temp;
    }
}
