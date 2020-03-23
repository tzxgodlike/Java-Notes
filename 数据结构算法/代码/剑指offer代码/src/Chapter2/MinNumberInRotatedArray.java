package Chapter2;

public class MinNumberInRotatedArray {
    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     *
     */
    public static int Min (int[] arr) {
        if (arr==null||arr.length==0)
            throw new RuntimeException("请输入正确的数组！");
        int l = 0,r = arr.length-1;
        if (arr[r]>=arr[l]) return arr[0];
        int min = 0;
        /*
        而当数组为{1,0,1,1,1}时 mid和l r的值相等 无法判断 这种情况直接顺序查找
         */
        while (l<r) {
            if ((r-l)==1) {
                min = r;
                break;
            }
            int mid = l+((r-l)>>1);
            if (arr[mid]==arr[l]&&arr[mid]==arr[r])
                return MinInOrder(arr);
            //如果中间数比左边大 则mid一定处于第一个递增数组
            //其实有特例 就是arr本身就是升序 那么按这个逻辑找下去会返回末尾的最大值
            //所以当数组为升序时 直接返回第一个
            if (arr[mid]>=arr[l]) {
                l = mid ;
            }else if (arr[mid]<arr[l]){
                r = mid;
            }
        }
        return arr[min];
    }

    private static int MinInOrder(int[] arr) {
        int min = 0;
        for (int i = 0;i<arr.length;i++){
            min = Math.min(min,arr[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,6,7,1,2};
        int[] arr1 = {1,2,3,4,5,6,7};
        int[] arr2 = {1,0,1,1,1};
        System.out.println(Min(arr2));
    }
}
