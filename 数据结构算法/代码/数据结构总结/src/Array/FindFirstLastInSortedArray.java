package Array;

public class FindFirstLastInSortedArray {
    /*
    leetcode.34 在排序数组中查找元素的第一个和最后一个位置
    要求时间复杂度为O(n) 那么猜想为二分查找
    即可以简化为 找到小于等于target的最大值的位置 以及
    大于等于target的最小值的位置
     */
    public int[] searchRange(int[] nums, int target) {
        int left = lower_bound(nums, target);
        int right = higher_bound(nums, target);
        return new int[]{left, right};
    }
    private int lower_bound(int[] a, int v){
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while(l <= r){
            int mid = l + ((r-l)>>1);
            if(a[mid] < v){
                l = mid + 1;
            }else if(a[mid] > v){
                r = mid - 1;
            }else{
                res = mid;
                r = mid - 1;
            }
        }
        return res;
    }
    private int higher_bound(int[] a, int v){
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while(l <= r){
            int mid = l + ((r-l)>>1);
            if(a[mid] > v){
                r = mid - 1;
            }else if(a[mid] < v){
                l = mid + 1;
            }else{
                res = mid;
                l = mid + 1;
            }
        }
        return res;
    }
}
