package basic_01;

public class PrintCountInversions {
    public static void printCountInversions(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        sortProcess(arr,0,arr.length-1);
    }

    public static void sortProcess(int[] arr,int l,int r ){
        if(l==r){
            return ;
        }
        int mid = l + ((r - l) >> 1);//等效于mid=(l+r)/2
        //把原数组分解至一个个单独的数时，再通过merge排序
//        sortProcess(arr,l,mid);
//        sortProcess(arr,mid+1,r);
        sortProcess(arr, l, mid);
        sortProcess(arr, mid + 1, r);
        merge(arr,l,mid,r);
    }


    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        String res= "";
        while (p1 <= m && p2 <= r) {
            if(arr[p1] > arr[p2]){
                for(int n =0;n<r-p2+1;n++){
                     res +=  "("+arr[p1]+","+arr[p2+n]+"),";
                }}
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
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
        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,10};
        printCountInversions(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
