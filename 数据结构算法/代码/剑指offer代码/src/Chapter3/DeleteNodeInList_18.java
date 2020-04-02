package Chapter3;

public class DeleteNodeInList_18 {

/**
 * 给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间内删除该结点。假设要删除的结点确实在链表中
 */
    private class Node {
        int val;
        Node next;
    }
    /**
     * 常规方法，从first开始找到要删除结点的前一个结点，时间复杂度为O(n)
     */
    public void deleteNode_2(Node head, Node toBeDel) {
        if (head == null||toBeDel == null) return;
        //toBeDel是头链表
        if (head==toBeDel){
            head = head.next;
        }else {
            Node cur = head;
            while (cur.next!=toBeDel) {
                cur = cur.next;
            }
            //此时cur为toBeDel的前节点
            cur.next = cur.next.next;
        }
    }

    /**
     * 将toBeDel的下一个结点j的值复制给toBeDel。然后将toBeDel指向j的下一个结点
     * O(1)
     */
    public void deleteNode(Node head, Node toBeDel) {
        if (head == null || toBeDel == null) {
            return;
        }
        //要删除的不是尾节点 则按上面方法
        if (toBeDel.next!=null){
            Node next = toBeDel.next;
            toBeDel.val = next.val;
            toBeDel.next = next.next;
        }else if(head==toBeDel){ //是尾也是头
            head = head.next;
        }else{
            Node cur = head;
            while (cur.next != toBeDel) {
                cur = cur.next;
            }
            cur.next = null;
        }
    }
}
