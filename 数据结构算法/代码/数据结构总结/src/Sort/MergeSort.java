package Sort;

public class MergeSort {

   /*
   *  - 时间复杂度O(n*logn) 额外空间复杂度O(n) 稳定
    - 一个数组，左右分别排好序，然后创建一个辅助数组B 依次从左右数组首端开始比较 小的放入B中 然后后移 一个数组移到末尾之后 直接将另一数组放入B
    - T(n) = 2T(n/2)+O(n)  即时间复杂度为O(n*logn)
    - 分治思想
    - mid = (l+r)/2会溢出 ，mid = l+(r-l)/2 不会溢出

    重点是搞清楚merge过程 需要一个临时数组 合并有序数组 再返回后给arr
   * */
    public static void mergeSort (int[] arr) {
        if (arr.length < 2 || arr == null) return;
        sortProcess(arr,0,arr.length-1);
    }
    public static void sortProcess(int[] arr,int l,int r) {
        if (l==r) return ;
        // //这里要两层括号 不然+l会失效
        int mid = l + ((r - l) >> 1);
        sortProcess(arr,l,mid);
        sortProcess(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    public static void merge(int[] arr,int l,int m,int r) {
        int[] help = new int[r-l+1];
        int p = l;
        int q = m+1;
        int i = 0;
        while (p <= m && q <= r) {
            help[i++] = arr[p]<arr[q]?arr[p++]:arr[q++];
        }
        while (p<=m) {
            help[i++] = arr[p++];
        }
        while (q<=r) {
            help[i++] = arr[q++];
        }
        for ( i = 0;i<r-l+1;i++) {
            arr[l+i] = help[i];
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
