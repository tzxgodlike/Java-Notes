package Tree;

public class Merge2Tree {
    /*
    leetcode.617
    * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

    * */

    /*
    * 解题思路
    * 如果当前节点t1,t2都为空，则返回null,
      如果当前节点t1为空，则返回t2,
      如果当前节点t2都为空，则返回t1,
      如果当前节点t1,t2都不为空，则把t1值改为t1,t2的和，继续递归左右节点。

    * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public Node mergeTrees(Node head1,Node head2) {
        if (head1==null) return head2;
        if (head2==null) return head1;
        head1.value += head2.value;
        head1.left = mergeTrees(head1.left,head2.left);
        head1.right = mergeTrees(head1.right,head2.right);
        return head1;
    }
}
