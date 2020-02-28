package basic_01;

public class SelectSort {
    //时间复杂度O(n^2)
    public static void selectSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=0;i<arr.length;i++){
            //在0,n-1中找最小的数 放到0上
            //在1，n-1上找最小的数放在1上
            //最小数索引初始值为i，然后遍历比较
            //找到最小的minindex 把i和min交换
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++){
                minIndex = arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
//        arr[i] = arr[i]^arr[j];
//        arr[j] = arr[i]^arr[j];
//        arr[i] = arr[i]^arr[j];
    }
    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2};
        selectSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
