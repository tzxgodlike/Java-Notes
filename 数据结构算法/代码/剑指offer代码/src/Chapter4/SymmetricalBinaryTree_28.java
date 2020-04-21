package Chapter4;

public class SymmetricalBinaryTree_28 {

    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    /*
    递归版本   递归过程需要两个节点
     */
    public boolean isSymmetric(Node head) {
        if (head == null) return true;
        return process(head.left,head.right);
    }

    private boolean process(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.value==right.value)&&
                process(left.left,right.right)&&
                process(left.right,right.left);
    }
}
