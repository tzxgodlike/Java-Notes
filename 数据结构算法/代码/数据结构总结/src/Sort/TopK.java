package Sort;

public class TopK {
    /*
    * 利用快速排序来求第K大的数
    * 快速排序第一次排完之后 会得到相等区域的起index
    * 此时arr[p[0]]就是第p[0]小的数
    * 再比较p[0]和k 来选择继续在小于区域还是大于区域继续快排
    * */
    public static int quickSort(int[] arr,int k){
        if(arr==null||arr.length<2){
            return 0;
        }
        k = arr.length-k;
        return quickSort(arr,0,arr.length-1,k);
    }

    public static int quickSort(int[] arr,int l,int r,int k) {
        if (l>r) return 0;
        swap(arr,r,l+(int)(Math.random()*(r-l+1)));
        int[] p =partition(arr,l,r);
        if (k == p[0]){
            return arr[p[0]];}
            // go left side
        else if (k < p[0]){
            return quickSort(arr, l,p[0]-1,k);}
        else{
            return quickSort(arr, p[1]+1,r,k);
        }

    }

    //选一个哨兵 分为三个区域 小于等于大于 最后返回的是等于区域的起止index
    //将小于区域和大于区域递归
    //参考荷兰国旗问题 有一个Less cur more 指针
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
        int res = quickSort(arr,1);
        System.out.println(res);
    }





}
