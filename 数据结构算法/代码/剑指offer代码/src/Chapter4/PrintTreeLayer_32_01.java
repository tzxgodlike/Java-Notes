package Chapter4;

import utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTreeLayer_32_01 {
    /*
    层次遍历二叉树
     */

    public static void printTreeByLayer(Node head) {
        if (head==null) return ;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.println(head.value);
            if (head.left!=null) queue.offer(head.left);
            if (head.right!=null) queue.offer(head.right);
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
