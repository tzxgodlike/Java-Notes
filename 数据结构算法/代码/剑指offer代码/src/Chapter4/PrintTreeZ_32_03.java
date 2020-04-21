package Chapter4;

import utils.Node;

import java.util.Stack;

public class PrintTreeZ_32_03 {

    /**
     * 按照Z字形打印二叉树，即先打印根结点，然后从右往左打印第二层，从左往右打印第三层...以此类推
     */

    public static void printZ(Node head) {
        if (head == null) return ;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(head);

        while (!stack1.isEmpty()||!stack2.isEmpty()) {

            //加if else 为了让回车只输出一次
            if (!stack1.isEmpty()) {

                while (!stack1.isEmpty()) {
                    head = stack1.pop();
                    System.out.print(head.value+" ");
                    if (head.left != null) stack2.push(head.left);
                    if (head.right != null) stack2.push(head.right);

                }
            }else {
                while (!stack2.isEmpty()) {
                    head = stack2.pop();
                    System.out.print(head.value+" ");
                    if (head.right != null) stack1.push(head.right);
                    if (head.left != null) stack1.push(head.left);

                }
            }
            System.out.println();

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

        printZ(head);
    }
}
