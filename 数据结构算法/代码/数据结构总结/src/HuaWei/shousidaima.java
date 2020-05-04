package HuaWei;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class shousidaima {

    /*
    int数组 统计出现频率最高的前三个数字
     */
    public static void getMaxThree(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        //小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(3);

        //大顶堆
        PriorityQueue<Integer> queueBig = new PriorityQueue<Integer>(3,(m,n)->(n-m));
        queueBig.offer(1);
        queueBig.offer(2);
        queueBig.offer(3);
        while (!queueBig.isEmpty()) {
            System.out.println(queueBig.poll());
        }



        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {

            int curkey = (int)iterator.next();
            int curvalue = map.get(curkey);

            if (queue.size() < 3) {
                queue.offer(curkey);
            } else if (map.get(queue.peek()) < curvalue) {//如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
                queue.poll();
                queue.offer(curkey);
            }

        }

        //System.out.println(queue.size());
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2,2,2,3,3,4};
        getMaxThree(nums);
    }

}
