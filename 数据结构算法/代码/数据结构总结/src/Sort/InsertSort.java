package Sort;

public class InsertSort {
    //稳定 n^2
    public static void insertSort(int[] arr) {
        if (arr==null||arr.length<2) return ;
        //第一次考察位置为1的数
        for (int i = 1;i<arr.length;i++) {
            //遍历i之前的区间
            for (int j = i-1;j>=0;j-- ) {
                //在[0,i]上做冒泡
                if (arr[j]>arr[j+1])  swap(arr,j,j+1);
            }
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2};
        insertSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
