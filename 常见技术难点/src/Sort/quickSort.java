package Sort;

import java.util.Arrays;

public class quickSort {

    public static void quickSort (int[] num) {
        if (num == null||num.length<2) return ;

        quickSort(num,0,num.length-1);
    }

    private static void quickSort(int[] num, int start, int end) {

        if (start>end) return ;
        swap(num,end,start+(int)Math.random()*(end-start+1));
        int[] p = partition(num,start,end);

        quickSort(num,start,p[0]-1);
        quickSort(num,p[1]+1,end);
    }

    public static int[] partition(int[] num,int l,int r) {
        int less = l-1,more = r,cur = l;
        while (cur<more) {
            if (num[cur]<num[r]) {
                swap(num,cur++,++less);
            }else if (num[cur]>num[r]) {
                swap(num,cur,--more);
            }else {
                cur++;
            }
        }
        swap(num,r,more);
        return new int[] {less+1,more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] num = {2,4,5,1,3,6,8,9,7};
        quickSort(num);
        for (int n:num){
            System.out.println(n);
        }
    }
}
