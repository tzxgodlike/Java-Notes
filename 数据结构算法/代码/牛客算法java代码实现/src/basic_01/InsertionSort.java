package basic_01;

public class InsertionSort {
    //插入排序 就好像扑克牌 已有的牌有序 新来的牌依次比较并交换

    public static void insertSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        //第一次考察的是1位置的数
        for(int i =1;i<arr.length;i++){
            //当j>=0&&arr[j]>arr[j+1]不成立时会结束循环
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }

    }
    public static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }
    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2};
        insertSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
