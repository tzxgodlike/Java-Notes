package basic_02;

public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //快速解法 把链表改成数组链表 然后按照荷兰国旗问题排序
    public static Node listPartiton1(Node head,int pivot){
        if(head == null) {
            return head;
        }
        int len = 0;
        Node cur = head;
        while(cur!=null){
            len++;
            cur = cur.next;
        }
        cur = head;
        Node[] nodeArr = new Node[len];
        for (int i = 0;i<len;i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr,pivot);
        for(int i =0;i<len-1;i++){
            nodeArr[i].next = nodeArr[i+1];
        }
        nodeArr[len-1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr,int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index<big){
            if(nodeArr[index].value<pivot){
                swap(nodeArr,++small,index++);
            }else if(nodeArr[index].value==pivot){
                index++;
            }else {
                swap(nodeArr,--big,index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    //最佳解法
    public static Node listPartiton2(Node head,int pivot) {
        //构造三条链表
        Node sH = null; //small head
        Node sT = null; //small tail
        Node eH = null; //equal head
        Node eT = null; //equal tail
        Node bH = null; //small head
        Node bT = null; //equal tail

        Node next = null;
        while (head != null) {
            //先把该节点取下来
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (head.value > pivot) {
                    if (bH == null) {
                        bH = head;
                        bT = head;
                    } else {
                        bT.next = head;
                        bT = bT.next;
                    }
                }
            }
            head = next;
        }
        //各部分连起来
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }



        public static void printLinkedList(Node node) {
            System.out.print("Linked List: ");
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
        }

        public static void main(String[] args) {
            Node head1 = new Node(7);
            head1.next = new Node(9);
            head1.next.next = new Node(1);
            head1.next.next.next = new Node(8);
            head1.next.next.next.next = new Node(5);
            head1.next.next.next.next.next = new Node(2);
            head1.next.next.next.next.next.next = new Node(5);
            printLinkedList(head1);
            // head1 = listPartition1(head1, 4);
            head1 = listPartiton1(head1, 5);
            printLinkedList(head1);

        }

    }
