package KuaiShou;

public class guoqi {
    public static int[] DistanceToHigher (int[] height) {
        // write code here
        if (height == null) {
            return null;
        }
        int[] res = new int[height.length];
        for (int i = 1;i<height.length;i++) {
            for (int j = i;j>=0;j--) {
                if (height[j]>height[i]){
                    res[i] = i-j;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] h = {175,173,174,163,182,177};
        int[] h1 = {177,177,177};
        int[] h2 = {175, 179, 174, 163, 176, 177};
        int[] res = DistanceToHigher(h2);
        for(int n:res){
            System.out.println(n);
        }
    }
}
