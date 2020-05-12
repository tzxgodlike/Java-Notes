package Queue;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TopKElement {

    /*
    leetcode.347

    给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    示例 1:

    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]
     */

    /*
    思路  hashmap存储出现次数  堆[优先级队列]进行排序
     */

    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> count = new HashMap<>();
        for (int n:nums) {
            //有则自增1  没有则设为1
            count.put(n,count.getOrDefault(n,0)+1);
        }

        //小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1,n2)->count.get(n1)-count.get(n2));

        //因为是小顶堆 所以个数大于K个之后要弹出堆顶 堆中的就是最大的K个数
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        List<Integer> top_k = new LinkedList();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);

        int len = top_k.size();
        int[] res = new int[len];
        for (int i = 0;i<len;i++) {
            res[i] = top_k.get(i);
        }
        return res;


    }
}
