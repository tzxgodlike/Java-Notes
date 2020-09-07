package 笔试记录.BILIBILI;

import java.nio.channels.FileChannel;

public class zuichanglianxuzishuzu {

    /*

     */
    public int GetMaxConsecutiveOnes1 (int[] arr, int k) {
        // write code here
        int ans = 0;
        int curCnts = 0; //当前窗口1个个数

        int l = 0,r=0;
        while (r<arr.length) {
            if (arr[r]==1) curCnts = curCnts+1;

            if (r-l+1>curCnts+k) {
                if (arr[l] ==1) curCnts = curCnts+1;
                l = l+1;
            }

            ans = Math.max(ans,r-l+1);
            r =r+1;
        }
        return ans;
    }

    public int GetMaxConsecutiveOnes (int[] arr, int k) {

        int i=0,j=0,sum=0,max=0;
        while (j<arr.length) {
            if (arr[j] ==1) {
                sum = sum+1;
                j = j+1;
            }else if (arr[j]==0&&k>0) {
                k--;
                sum = sum+1;
                j = j+1;
            }else {
                sum = sum-1;
                if (arr[i]==0) {
                    k++;
                }
                i++;
            }
            max = Math.max(sum,max);
        }
        return max;
    }
}
