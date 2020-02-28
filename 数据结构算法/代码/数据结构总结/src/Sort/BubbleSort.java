package Sort;

public class BubbleSort {
    /*
    一次确定一个位置 先确定最后一个位置的数
    若某一次遍历中没有交换数据 说明已经有序
    * */
    //    时间复杂度 O(n^2) 空间复杂度O(1) 稳定
    public static void bubbleSort(int[] arr) {
        if(arr==null||arr.length<2){
            return;
        }
        boolean flag = false;
        for(int end = arr.length-1;end>0;end--){
            for(int i =0;i<end;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                    flag = true;
                }
            }
            if(!flag) break;  //没有数据交换 直接退出循环
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
