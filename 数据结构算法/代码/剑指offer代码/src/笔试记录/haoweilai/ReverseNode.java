package 笔试记录.haoweilai;

import utils.ListNode;

public class ReverseNode {

    public static ListNode reverseList (ListNode head) {
        // write code here
        ListNode cur = head;
        ListNode pre = null;
        while (cur!=null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        System.out.println(reverseList(head));
    }
}
