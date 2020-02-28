package basic_01;

public class QuickSort {
    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int L,int R){
        if(L < R){
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr,L,R);
            quickSort(arr,L,p[0]-1);
            quickSort(arr,p[1]+1,R);
        }
    }
    public static int[] partition(int[] arr,int L,int R){
        int less = L-1;
        int more = R;
        int cur = L;
        while(cur<more){
            if(arr[cur]<arr[R]){
                swap(arr,++less,cur++);
            }else if(arr[cur]>arr[R]){
                swap(arr,--more,cur);
            }else{
                cur++;
            }
        }
        //把末尾的哨兵值换到more区域的第一个
        swap(arr,more,R);
        //返回的是等于num的区域的坐标index less+1是起点 more是相等区域的终点
        return new int[]{less+1,more};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,10};
        quickSort(arr,0,9);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
