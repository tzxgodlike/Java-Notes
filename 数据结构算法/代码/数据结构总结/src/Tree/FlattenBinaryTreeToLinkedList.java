package Tree;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {
    /*
    leetcode.114
    给定一个二叉树，原地将它展开为链表。 即节点在右，左子为null

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6


     */
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }
    /*
    思路1：
    利用先序遍历 把树重组为一个链表的形式  注意其中的编程技巧
     */
    public void flatten(TreeNode root) {
        if (root==null) return ;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode res = null;
        stack.push(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            if (res!=null) {       //注意
                res.right = cur;
                res.left = null;
            }
            if (cur.right!=null) stack.push(cur.right);
            if (cur.left!=null) stack.push(cur.left);
            res =cur;
        }
        root = cur;
    }

    /*
    思路2 原地操作 用递归  就是把左子树移到右子树上
     */
    private TreeNode pre = null;
    public void flattenWithRec(TreeNode root){
        if (root==null) return;
        flattenWithRec(root.right);
        flattenWithRec(root.left);
        root.right = pre;
        root.left = null;
        pre = root;

    }
}
