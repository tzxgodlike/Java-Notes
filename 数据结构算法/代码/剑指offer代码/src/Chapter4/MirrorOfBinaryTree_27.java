package Chapter4;

public class MirrorOfBinaryTree_27 {

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static  Node mirrorRecur (Node head) {
        if (head==null) return null;

        if (head.left == null && head.right == null) {
            return null;
        }
        Node tmp = head.left;
        head.left = head.right;
        head.right = tmp;

        mirrorRecur(head.left);
        mirrorRecur(head.right);
        return head;
    }
}
