package Chapter6;

import utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class DepthOfTree_55_01 {

    /*
    求二叉树的深度
     */

    /*
    递归
     */

    public int depthOfTree(Node head) {
        if (head==null) return 0;
        int left = depthOfTree(head.left);
        int right = depthOfTree(head.right);

        return Math.max(left,right)+1;
    }

    /*
    层次遍历
     */
    public int depth(Node head) {
        if (head==null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        int size = 0;
        int depth = 0;
        while (!queue.isEmpty()) {
            //记下当前层次的节点个数 即为此时队列的元素个数
            size = queue.size();
            for (int i = 0;i<size;i++) {
                head = queue.poll();
                if (head.left!=null) queue.offer(head.left);
                if (head.right!=null) queue.offer(head.right);
            }
            depth++;
        }
        return depth;
    }
}
