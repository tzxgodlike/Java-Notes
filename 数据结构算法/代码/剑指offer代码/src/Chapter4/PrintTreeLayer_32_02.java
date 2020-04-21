package Chapter4;

import utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeLayer_32_02 {
    /**
     * 逐层打印二叉树，即打印完一层后要有换行符
     */
    public static void printTreeByLayer(Node head) {
        if (head==null) return ;
        //下一层需要打印节点的个数
        int nextLevel = 0;
        int toBePrint = 1;
        //再队列中是看不到分层的 所以需要这2个参数来计数
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value);
            toBePrint--;
            if (head.left!=null) {
                queue.offer(head.left);
                nextLevel++;
            }
            if (head.right!=null) {
                queue.offer(head.right);
                nextLevel++;
            }
            if (toBePrint==0) {
                System.out.println();
                toBePrint = nextLevel;
                nextLevel = 0;
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);

        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);

        printTreeByLayer(head);
    }
}
