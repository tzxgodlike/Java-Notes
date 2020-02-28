package basic_02;

import java.util.Stack;

public class IsPalindromeList {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    //need n extra space
    public static boolean isPalindromeList1(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head!=null){
            if(head.value!=stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }
    //need n/2 extar space
//    public static boolean isPalindromeList2(Node head){
//        Stack<Node> stack = new Stack<>();
//
//    }



// need O(1) extra space
    public static boolean isPalindromeList3(Node head){
        if(head==null||head.next==null){
            return true;
        }
        Node n1 = head;  //慢针
        Node n2 = head;  //快针
        //奇数个点时 n1在正中 偶数个点时 n1在中间偏左那个点
        while(n2.next!=null&&n2.next.next!=null){
            n1 = n1.next;   //n1在mid
            n2 = n2.next.next; //n2在end
        }
        n2 = n1.next;  //n2是右半的第一个点
        n1.next = null; //mid.next = null
        Node n3 = null;
        while(n2!=null){ //右边逆序 right convert
            n3 = n2.next;
            n2.next =n1;
            n1 = n2;
            n2 = n3;     //最后一个点为n1
        }
        n3 = n1;   //保存最右的点
        n2 =head;
        boolean res = true;
        while(n1!=null&&n2!=null){
            if (n1.value != n2.value) {
                res = false;
                break;   //后面还要把逆序换回来 所以不能return
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //现在n1 n2都在中间
        n1 = n3.next;
        n3.next=null;
        while(n1!=null){
            n2 = n1.next;
            n1.next=n3;
            n3 = n1;
            n1 = n2;
        }//最后n1回到头结点
        return res;
    }


    public static void printLinkedList(Node node){
        System.out.println("list");
        while(node!=null){
            System.out.print(node.value+" ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        //System.out.print(isPalindromeList1(head) + " | ");
        //System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
