package Chapter3;

public class SubstructureInTree_26 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean hasSubTree(Node head1,Node head2) {
        boolean result = false;
        if (head1!=null&&head2!=null) {
            if (head1.value==head2.value) {
                result = DoesTree1HasTree2(head1,head2);
            }
            //result为false
            if (!result) {
                result = hasSubTree(head1.left,head2);
            }
            if (!result) {
                result = hasSubTree(head1.right,head2);
            }
        }
        return result;
    }

    private static boolean DoesTree1HasTree2(Node head1, Node head2) {
        // node2到达叶子结点的左右子结点了都还相等，说明是树1的子结构
        if (head2 == null) return true;
        //head2 没到叶子结点的左右子节点 head1已经到了 说明肯定匹配不上
        if (head1 == null) return  false;

        if (head1.value==head2.value) return DoesTree1HasTree2(head1.left,head2.left)&&
                DoesTree1HasTree2(head1.right,head2.right);
        return false;
    }
}
