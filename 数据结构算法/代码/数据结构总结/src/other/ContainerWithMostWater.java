package other;

public class ContainerWithMostWater {
    /*
    leetcode11.盛最多水的容器
    给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     1.暴力法
     2.双指针法
     */
        public int maxArea(int[] height) {
            if(height==null||height.length ==0) return 0;
            int res = 0;
            int len = height.length;
            int l = 0,r = len-1;
            while(l<r) {
                if (height[l]<height[r]){
                    res = Math.max(res,(r-l)*height[l]);
                    l++;
                }else{
                    res = Math.max(res,(r-l)*height[r]);
                    r--;
                }

            }
            return res;
        }
}
