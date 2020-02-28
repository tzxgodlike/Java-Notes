package Sort;

public class SelectSort {
    //时间复杂度O(n^2)   不稳定
    //选择排序和冒泡排序相似 都是每次遍历确定一个位置
    public static void selectSort(int[] arr) {
        if(arr==null||arr.length<2){
            return;
        }
        for (int i = 0;i<arr.length;i++) {
            int minIndex = i;
            //在0,n-1中找最小的数 放到0上
            //在1，n-1上找最小的数放在1上
            //最小数索引初始值为i，然后遍历比较
            //找到最小的minindex 把i和min交换
            for (int j =i+1;j<arr.length;j++) {
                minIndex = arr[minIndex]<arr[j]?minIndex:j;
            }
            swap(arr,minIndex,i);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
