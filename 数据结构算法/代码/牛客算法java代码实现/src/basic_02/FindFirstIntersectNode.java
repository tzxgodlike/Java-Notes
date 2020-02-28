package basic_02;

import java.util.HashSet;

public class FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //主函数 传入两个链表 返回第一个相交的节点或null
    public static Node getIntersectNode(Node head1,Node head2){
        if(head1 == null||head2 == null){
            return null;
        }
        //先找到两个链表是否有环，并返回第一个成环的节点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
       // System.out.println(loop1.value+"   "+loop2.value);
        //两个都没环，判断无环链表相交节点
        if(loop1 == null && loop2 ==null){
            return noLoop1(head1,head2);
        }
        if(loop1!=null&&loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        //一个有环，一个没环 必没有相交节点
        return null;
    }

    //判断环 并返回第一个成环节点
    public static Node getLoopNode(Node head){
        //成环至少三个节点
        if(head == null||head.next == null||head.next.next == null){
            return null;
        }
        //快指针一次两步 慢指针一次一步
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow!=fast){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //无环链表相交节点方法1
    //先判断end1是否和end2
    //"相等"(内存地址一样)，若不等则不相交。长链表先走，等到长的剩下部分
    public static Node noLoop1(Node head1,Node head2){
        if(head1 == null||head2.next == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        //技巧 用n++ n--来表示长度差值
        while(cur1.next!=null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next!=null){
            n--;
            cur2 = cur2.next;
        }
        //若相交 则末尾节点必为同一节点 不等则不相交
        if(cur1!=cur2){
            return null;
        }
        //cur1指向长的
        cur1 = n>0?head1:head2;
        cur2 = cur1==head1?head2:head1;
        n = Math.abs(n);
        while(n!=0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1!=cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //无环链表相交节点方法2
    // A走完继续从B走 B走完继续从A走 相遇点位相交节点
    public static Node noLoop2(Node head1,Node head2) {
        if(head1 == null||head2== null){
            return null;
        }
        Node pa = head1;
        Node pb = head2;
        while (pa!=pb) {
            pa = pa.next;
            pb = pb.next;
            if (pa==null&&pb==null) {
                return null;
            }
            if (pa == null ) {
                pa = head2;
            }
            if (pb == null) {
                pb = head1;
            }
        }
        return pa;
    }
    //无环链表相交节点方法3 hash法
    public static Node noLoop3(Node head1,Node head2) {
        if(head1 == null||head2== null){
            return null;
        }
        HashSet<Node> set = new HashSet<>();
        while (head1!=null) {
            set.add(head1);
            head1 = head1.next;
        }
        while (head2!=null) {
            if (set.contains(head2)) return head2;
            head2 = head2.next;
        }
        return null;
    }

    //有环链表相交节点
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        //先相交 共享一个环 “Y形下面接个圆圈”
        //等同于无环链表找相交节点 只是把遍历到null变成遍历到loop
        if(loop1==loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            //技巧 用n++ n--来表示长度差值
            while(cur1!=loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2!=loop2){
                n--;
                cur2 = cur2.next;
            }
            //若相交 则末尾节点必为同一节点 不等则不相交
            if(cur1!=cur2){
                return null;
            }
            //cur1指向长的
            cur1 = n>0?head1:head2;
            cur2 = cur1==head1?head2:head1;
            n = Math.abs(n);
            while(n!=0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1!=cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            //继续遍历loop
            cur1 = loop1.next;
            while(cur1!=loop1){
                if(cur1 ==loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        //System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
        System.out.println(getLoopNode(head1).value);

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        //System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        //System.out.println(getIntersectNode(head1, head2).value);

    }
}
