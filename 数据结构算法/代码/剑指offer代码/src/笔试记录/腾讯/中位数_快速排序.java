//package 笔试记录.腾讯;
//
//import java.math.BigInteger;
//import java.util.*;
//
//public class 中位数_快速排序 {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//
//        int[] nums = new int[n];
//
//        for (int i = 0;i<n;i++) {
//            nums[i] = sc.nextInt();
//        }
//        for (int i = 0;i<n;i++) {
//            ArrayList list = new ArrayList<>(Arrays.asList(nums));
//            list.remove(i);
//            int[] cur = list.stream().mapToInt(Integer::valueOf).toArray();
//        }
//        int mid1 = quickSort(nums,n/2);
//        int mid2 = quickSort(nums,n/2+1);
//        for (int i = 1;i<=n/2;i++) {
//            System.out.println(mid1);
//        }
//
//        for (int i = 1;i<=n/2;i++) {
//            System.out.println(mid2);
//        }
//    }
//
//    public static int quickSort(int[] arr,int k){
//        if(arr==null||arr.length<2){
//            return 0;
//        }
//        k = arr.length-k;
//        return quickSort(arr,0,arr.length-1,k);
//    }
//
//    public static int quickSort(int[] arr,int l,int r,int k) {
//        if (l>r) return 0;
//        swap(arr,r,l+(int)(Math.random()*(r-l+1)));
//        int[] p =partition(arr,l,r);
//        if (k == p[0]){
//            return arr[p[0]];}
//        // go left side
//        else if (k < p[0]){
//            return quickSort(arr, l,p[0]-1,k);}
//        else{
//            return quickSort(arr, p[1]+1,r,k);
//        }
//
//    }
//
//    //选一个哨兵 分为三个区域 小于等于大于 最后返回的是等于区域的起止index
//    //将小于区域和大于区域递归
//    //参考荷兰国旗问题 有一个Less cur more 指针
//    public static int[] partition(int[] arr,int l,int r) {
//        int less = l-1;
//        int more = r;
//        int cur = l;
//        while (cur<more) {
//            //末尾值为哨兵
//            if (arr[cur]>arr[r]) {
//                swap(arr,cur,--more);
//            }else if (arr[cur]<arr[r]) {
//                swap(arr,++less,cur++);
//            }else {
//                cur++;
//            }
//        }
//        //交换末尾哨兵到more区第一个
//        swap(arr,r,more);
//        return new int[]{less+1,more}; //返回相等区域的起止index
//    }
//
//
//
//    public static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
//}
