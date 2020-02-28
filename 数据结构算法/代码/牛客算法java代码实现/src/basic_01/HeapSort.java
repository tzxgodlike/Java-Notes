package basic_01;

public class

HeapSort {
    public static void heapSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i =0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        //上面是堆化的过程
        //下面是排序的过程  0(nlogn)
        int size = arr.length;
        //把堆顶放到数组末尾 相当于在堆中删除了堆顶 把最后一个
        //叶子节点放到堆顶 然后再调整堆重新成为大根堆
        swap(arr,0,--size);
        //heapsize此时堆的大小
        while(size>0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }
    //建堆 从下而上  如果新加入节点比父节点大 则从下往上一直交换  O(n)
    public static void heapInsert(int[] arr,int index){
        //当index为0时，index-1 /2 =0 所以循环条件包含了边界
        while(arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapify(int[] arr,int index,int heapSize){  //自下而上的堆化方法 O(n)
        int left = index*2+1;
        while(left<heapSize){
            //当右子节点在边界内的前提下 找到index的子节点中最大的那个的坐标
            int largest = left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;
            largest = arr[largest]>arr[index]?largest:index;  //对比index和largest 找到较大的坐标
            if(largest==index){
            break;
            } //如果index比子节点都大 直接跳出循环
            swap(arr,index,largest); //说明largest!=index 则把index和该子节点交换
            index=largest;           //继续调整该子节点 另一颗子树不用调整
            left = index*2+1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,2,5};
        heapSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
