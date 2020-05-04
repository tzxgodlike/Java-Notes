package Tree;

import utils.Node;

public class LowestCommonAncestorOfBinaryTree {

    /*
    最近公共祖先 leetcode.236
     */

    /*
    方法1： DFS深度搜索
     */

    static Node res;
    public static Node lowestCommonAncestor(Node root, Node p, Node q){
        dfs(root, p, q);
        return res;
    }

    private static boolean  dfs(Node node, Node p, Node q) {

        if (node==null) return false;

        boolean left = dfs(node.left,p,q);
        boolean right  = dfs(node.right,p,q);
        boolean mid = (node==p||node==q);

        if (mid ? (left||right):(left&&right))
            res = node;

        return mid||right||left;
    }
}

