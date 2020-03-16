package HighFrequencyProblems;



public class RemoveDuplicationInOrderedArray {
    /*
    取出有序数组中重复的元素 原地修改并返回新数组的长度
     */
    /*
    数组
     */
    int removeDuplicatesInArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int slow = 0, fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 长度为索引 + 1
        return slow + 1;
    }

    /*
    链表
     */
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
    Node removeDuplicatesInLinkedList(Node head) {
        if (head==null) return null;
        Node slow = head;
        Node fast = head.next;
        while (fast != null) {
            if (slow.data!=fast.data){

                slow.data = fast.data;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
