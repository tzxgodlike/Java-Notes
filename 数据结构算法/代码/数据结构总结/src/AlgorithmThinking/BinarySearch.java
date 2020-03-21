package AlgorithmThinking;

public class BinarySearch {

    /*
    二分查找框架
    https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E8%AF%A6%E8%A7%A3.md
     */
//    int binarySearch(int[] nums, int target) {
//        int left = 0, right = ...;
//
//        while(...) {
//            int mid = left + (right - left) >> 1;
//            if (nums[mid] == target) {
//            ...
//            } else if (nums[mid] < target) {
//                left = ...
//            } else if (nums[mid] > target) {
//                right = ...
//            }
//        }
//        return ...;
//    }

    /*
    1.查找一个值 【基本二分查找】
     */

    int binarySearch(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        while (left<=right) {       //left == right时 num[left]此时还未判断过 所以要取=号
            int mid = left + (right - left) >> 1;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;  //未查找到
    }

    /*
    2.当数组中包含多个target时，寻找target左边界
     */
    int left_bound(int[] nums,int target) {
        int left = 0,right = nums.length - 1;  //意味着区间为左闭右闭
        while (left<=right) {
            int mid = left + (right - left)>>1;
            if (nums[mid] == target) {  //因为要找最边界 所以继续二分
                right = mid - 1;
            }else if (nums[mid] > target ) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;           //最后一次循环 若没有比t大的数 则left会=length
            }
        }
        //没找到
        if (left==nums.length||nums[left]!=target) return -1; //nums[left]!=target意思是所有数都比t大 所以left最后变成0
        return left;
    }
    /*
    3.同理可得寻找右边界
     */
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) >> 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 这里改成收缩左侧边界即可
                left = mid + 1;
            }
        }
        // 这里改为检查 right 越界的情况，见下图
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }
}
