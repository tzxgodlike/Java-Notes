package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

    /*
    * leetcode.226反转二叉树
    * 翻转一棵二叉树。         就是把每个结点的左右子树交换

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

    *
    * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    /*
     * 1.层次遍历 BFS
     * */
    public Node invertBinaryTreeWithBFS(Node head) {
        if (head == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            Node temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;
            if (cur.left!=null) {
                queue.offer(cur.left);
            }
            if (cur.right!=null) {
                queue.offer(cur.right);
            }
        }
        return head;
    }

    /*
    2.递归
    * */
    public Node invertBinaryTreeWithRec (Node head) {
        if (head == null) return null;
        Node right = invertBinaryTreeWithRec(head.left);
        Node left = invertBinaryTreeWithRec(head.right);

        head.right = left;
        head.left = right;
        return head;
    }
}
