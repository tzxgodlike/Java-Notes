package basic_01;

public class NetherlandsFlag {
    public static void partition(int[] arr,int L,int R,int num){
        int less = L-1;
        int more = R+1;
        int cur = L;
        while(cur<more){
            if(arr[cur]<num){
                swap(arr,++less,cur++);
            }else if(arr[cur]>num){
                swap(arr,--more,cur);
            }else{
                cur++;
            }
        }
        //返回的是等于num的区域的坐标index
        //turn new int[]{less+1,more-1};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {5,4,8,6,7,9,3,1,2,10};
        partition(arr,0,9,5);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
