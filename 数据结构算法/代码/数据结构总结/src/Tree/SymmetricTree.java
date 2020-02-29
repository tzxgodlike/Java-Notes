package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    /*
    * leetcode.101 对称树  关键在于判断从第二层开始 逻辑是A B两树是否是镜像的
    * 而从根节点开始逻辑是 A树是否是镜像
    * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //1.迭代 BFS 关键在于先加入第二层的两个子结点 加入的时候加入四个节点
    public boolean isSymmetric1(Node root) {
        if (root == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            Node t1 = queue.poll();
            Node t2 = queue.poll();
            if (t1==null&&t2==null) continue;
            if (t1==null||t2==null) return false;
            if (t1.value != t2.value) return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }

    //2.递归
    public boolean isSymmetric2(Node root) {
        if (root==null) return true;
        return process(root.left,root.right);
    }

    public boolean process(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.value==right.value&&process(left.left,right.right)&&process(left.right,right.left);
    }
}
