package Tree;

import other.MaximumSubarray;

public class DiameterOfBinaryTree {

    /*
    leetcode.543 求二叉树的直径   直径就是任意两个点之间的最大边数
    思路：递归求高度 顺便求该节点左子树高度与右子树高度之和
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    int ans;
    public int diameterOfBinaryTree(Node root) {
        ans = 0;
        depth(root);
        return ans-1;
    }

    public int depth(Node root) {
        if (root==null) return 0 ;
        int l = depth(root.left);
        int r = depth(root.right);
        ans = Math.max(ans,l+r);
        return Math.max(l,r)+1;
    }
}
