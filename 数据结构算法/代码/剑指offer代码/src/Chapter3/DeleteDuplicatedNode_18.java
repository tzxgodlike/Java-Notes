package Chapter3;

public class DeleteDuplicatedNode_18 {
    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * 注意重复的结点不保留：并不是将重复结点删除到只剩一个，而是重复结点的全部会被删除。所以
     * 链表1->2->3->3->4->4->5不是1->2->3->4->5
     */
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur!=null&&cur.next!=null) {
            //如果当前节点值和下一个相同
            if (cur.val==cur.next.val) {
                int val = cur.val;
                while (cur.val==val){
                    cur = cur.next;
                }
                //若头节点就被重复
                if (pre == null) head = cur;
                else {pre.next =cur;}
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

}
