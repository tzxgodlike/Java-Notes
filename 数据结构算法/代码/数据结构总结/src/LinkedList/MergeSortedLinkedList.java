package LinkedList;

public class MergeSortedLinkedList {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //使用辅助空间 使用一个头节点head 最后返回head->next
    //用两个指针指向两个链表 小的就加入新链表 然后后移
    public static Node mergeLinkListWithExtraPlace(Node head1,Node head2) {

        Node head = new Node(-1);    //head固定
        Node cur = head;
        Node p = head1;
        Node q = head2;
        while (p!=null&&q!=null) {
            if (p.value<=q.value) {
                cur.next = p;
                p = p.next;
            }else {
                cur.next = p;
                q = q.next;
            }
            cur =cur.next;
        }
        if (p.next == null) {
            cur.next = q;
        }
        if (q.next == null) {
            cur.next = q;
        }
        return head.next;
    }

    //递归实现
    public static Node mergeLinkListWithExtraPlaceRec(Node head1,Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        if(head1.value<=head2.value) {
            head1.next = mergeLinkListWithExtraPlaceRec(head1.next,head2);
            return head1;
        }else {
            head2.next = mergeLinkListWithExtraPlace(head1,head2.next);
            return head2;
        }
    }
}
