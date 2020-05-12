package Chapter6;

import utils.Node;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public class IsBalancedTree_55_02 {

    /*
    判断是否是平衡二叉树
     */

    /*
    递归版本   每个结点被重复遍历多次
     */
    public static boolean isBalanced(Node head) {
        if (head==null) return true;

        int left = depthOfTree(head.left);
        int right = depthOfTree(head.right);

        if (Math.abs(left-right)>1) return false;

        return isBalanced(head.right)&&isBalanced(head.left);
    }

    public static int depthOfTree(Node head) {
        if (head==null) return 0;
        int left = depthOfTree(head.left);
        int right = depthOfTree(head.right);

        return Math.max(left,right)+1;
    }

    /**
     * 方法2：修改求二叉树深度的方法：
     * 只要有某个结点不平衡，将一直返回-1直到root；否则就正常返回树的深度
     */
    public boolean isBalanced2(Node root) {
        return depth2(root) >= 0;
    }

    private int depth2(Node root) {
        if (root == null) return 0;
        int left = depth2(root.left);
        int right = depth2(root.right);
        return left >= 0 && right >= 0 && Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

    /*
    后序遍历      为了传引用 使用int[]   感觉和方法2没啥差别
     */

    public boolean IsBalanced_Solution(Node root) {
        return isBalance3(root, new int[1]);
    }

    private boolean isBalance3(Node root, int[] depth) {
        if (root==null) {
            depth[0] = 0;
            return true;
        }
        boolean left = isBalance3(root.left, depth);
        // 左子树的深度
        int leftDepth = depth[0];
        // 右子树的深度
        boolean right = isBalance3(root.right, depth);
        int rightDepth = depth[0];
        // 当前结点的深度
        depth[0] = Math.max(leftDepth + 1, rightDepth + 1);
        if (left && right && Math.abs(leftDepth - rightDepth) <= 1) return true;
        return false;

    }

}
