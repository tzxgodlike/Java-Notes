import java.util.LinkedList;

public class FromTail2Head_06 {
    // 节点类的定义
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    /**
     * 更推荐使用栈，正序压入，逆序弹出
     *
     * @param listNode 链表的头结点
     * @return 从尾到头排列的结点
     */
    public static void printListFromTailToHead(Node node){
        LinkedList<Integer> stack = new LinkedList<>();
        while (node!=null){
            stack.push(node.value);
            node = node.next;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    /**
     * 利用递归，先递归到最后一个结点后开始依次返回。链表如果很长不适合用递归，递归深度将很大
     */
    public static void printListFromTailToHeadRec(Node node){
        if(node!=null&&node.next!=null){
            printListFromTailToHeadRec(node.next);
        }
        System.out.println(node.value);
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        printListFromTailToHeadRec(head);
        printListFromTailToHead(head);
    }

}
