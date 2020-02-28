package basic_01;

public class MergeSort {
    public static void mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        sortProcess(arr,0,arr.length-1);
    }

    public static void sortProcess(int[] arr,int l,int r ){
        if(l==r){
            return;
        }
        int mid = l + ((r - l) >> 1);//等效于mid=(l+r)/2
        //把原数组分解至只有两个数时，再通过merge排序
//        sortProcess(arr,l,mid);
//        sortProcess(arr,mid+1,r);
        sortProcess(arr, l, mid);
        sortProcess(arr, mid + 1, r);
        merge(arr,l,mid,r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        //合并有序数组
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }
    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,10};
        mergeSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
