package LinkedList;

import utils.Node;

public class ReverseList {

    /*
    反转链表
     */

    /*
    1.迭代法
     */

    public static Node reverse (Node head) {
        Node cur =head,pre = null;

        while (cur!=null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
    递归法
     */

}
