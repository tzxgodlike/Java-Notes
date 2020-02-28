package Sort;

public class QuickSort {

    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int l,int r) {
        if (l>r) return ;
        swap(arr,r,l+(int)(Math.random()*(r-l+1)));
        int[] p =partition(arr,l,r);
        quickSort(arr,l,p[0]-1);
        quickSort(arr,p[1]+1,r);
    }

    //选一个哨兵 分为三个区域 小于等于大于 最后返回的是等于区域的起止index
    //将小于区域和大于区域递归
    //参考荷兰国旗问题 有一个Less cur more 指针  less 指向的是小于区的最后一个值 more初值是哨兵 后面指向大于区的第一个
    public static int[] partition(int[] arr,int l,int r) {
        int less = l-1;
        int more = r;
        int cur = l;
        while (cur<more) {
            //末尾值为哨兵
            if (arr[cur]>arr[r]) {
                swap(arr,cur,--more);
            }else if (arr[cur]<arr[r]) {
                swap(arr,++less,cur++);
            }else {
                cur++;
            }
        }
        //交换末尾哨兵到more区第一个
        swap(arr,r,more);
        return new int[]{less+1,more}; //返回相等区域的起止index
    }



    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,10};
        quickSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
