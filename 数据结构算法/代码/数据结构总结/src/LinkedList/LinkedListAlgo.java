package LinkedList;
/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 *
 * Author: tzx
 */
public class LinkedListAlgo {
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
    /*
    * 头插法
    * 先连后 再连前
    * p.next = head.next
    * head.next = p
    *
    * 尾插法
    * 设置一个尾指针
    * r.next = p
    * r = p
    * */
    public static Node createNode(int value) {
        return new Node(value, null);
    }
    /*
    * 单链表反转
    * 1.迭代法
    * 需要pre next cur三个指针 当前节点只把next指向上一个节点
    * */
    public static Node reverse(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur!=null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    /*
    * 2.递归法 假设列表head之后的节点已经被反转
    * */
    public static Node reverseWithRec(Node head) {
        if (head==null||head.next==null) {
            return head;
        }
        Node p = reverseWithRec(head.next);
        head = head.next.next;
        head.next = null;
        return p;    //p是最后一个节点 p在从后往前的递归过程中一直指向最后一个节点
    }

    /*
    * 环的检测
    * 方法1.使用hashset，每遍历一个点就把该点加入到hashset中去
      方法2.两个指针，一个快一个慢，快指针一次两步，慢指针一次一步，若快指针指null,则没环
                      当快指针和慢指针相遇时，快指针回到开头，然后快指针变为走一步，快指针和慢指针一
                      定会在入环节点处相遇。  [这是一个结论]
    * */
    public static Node getLoopNode(Node head) {
        //有环至少三个节点
        if (head == null||head.next == null||head.next.next ==null) {
            return null;
        }
        //快慢指针初始值为走过一次的值
        Node fast = head.next.next;
        Node slow = head.next;
        while (slow!=fast) {
            //点指向null说明没环
            if (fast.next==null||fast.next.next==null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow!=fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
    * 有序链表合并
    * 代码见MergeSortedLinkedList
    * 递归和非递归
    * */

    /*
    * 删除倒数第K个节点
    * */
    public static Node deleteLastKth(Node head,int k) {
        Node p,q = null;
        p = head;
        int i = 1;
        while (p!=null&&i<k) {
            p = p.next;
            i++;
        }
        if (p == null) return head;
        q= head;
        Node pre = null;
        //删除一个节点 要找到它前一个节点 记为pre
        while (p.next!=null) {
            p = p.next;
            pre = q;
            q = q.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    /*
    * 中间节点  快慢指针
    * */
    public static Node getMidNode(Node head) {
        Node p = head;
        Node q = head;
        while (q.next!=null&&q.next.next!=null){
            q = q.next.next;
            p = p.next;
        }
        return p;
    }





    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

}
