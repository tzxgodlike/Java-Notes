package basic_02;

import java.util.HashMap;

//复制含有随机指针节点的链表
public class CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }
    //extra area On
    public static Node copyListWithRand1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while(cur!=null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur!=null){
            map.get(cur).next = cur.next;
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        //返回复制链表的第一个节点
        return map.get(head);
    }

    //no extra area
    public static Node copyListWithRand2(Node head){
        if(head ==null){
            return null;
        }
        Node cur = head;
        Node next = null;
        // copy node and link to every node
        while(cur!=null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next=next;
            cur = next;
        }
        // set copy node rand

        cur = head;
        Node curCopy = null;
        while(cur!=null){
            next = cur.next.next;
            //curCopy =cur.next;
            //若1.rand=2 那么1'.rand=2'[即2.next为2']
            cur.next.rand = cur.rand!=null?cur.rand.next:null;
            cur = cur.next.next;
        }
        //split
        Node res = head.next;
        cur = head;
        while (cur!=null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            //赋值时时刻注意判断非空
            curCopy.next = next!=null?next.next:null;
            cur = next;
        }
        return res;
    }




    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
