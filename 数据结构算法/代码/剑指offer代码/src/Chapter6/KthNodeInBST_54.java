package Chapter6;

import utils.Node;

import java.util.LinkedList;

public class KthNodeInBST_54 {
    /**
     * 给定一颗二叉搜索树，请找出排名第k的结点。
     *
     *
     * 二叉排序树中序遍历就是有序的 所以在中序遍历中打印一次就 计一个数
     */

    public Node KthNode (int k, Node head) {
        if (head==null||k==0) {
            return null;
        }
        return KthNodeCore(head,k);
    }

    private Node KthNodeCore(Node head, int k) {
        LinkedList<Node> stack = new LinkedList<>();
        while (!stack.isEmpty()||head!=null) {
            if (head!=null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.poll();
                if (k==1) return head;
                k--;
                head = head.right;
            }
        }
        return null;
    }
}
