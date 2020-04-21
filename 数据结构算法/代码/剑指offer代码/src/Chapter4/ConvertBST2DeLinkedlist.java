package Chapter4;

import utils.Node;

public class ConvertBST2DeLinkedlist {

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     */
    public Node Convert(Node pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }

        Node root = pRootOfTree;
        // 得到双向链表
        ConvertNodeInOrder(root);
        // 向左找到双向链表的头结点
        while (root.left != null) {
            root = root.left;
        }
        return root;

    }
    public static Node preNode ;  //当前链表的尾节点


    //函数完成后返回的是链表的尾结点
    public static void ConvertNodeInOrder (Node head) {
        if (head == null) return ;

        ConvertNodeInOrder(head.left);
        //中序遍历 操作写在中间
        //连接当前节点到链表
        //把当前节点的左指针指向preNode 把preNode的右指针指向当前节点
        head.left = preNode;
        if (preNode!=null) preNode.right = head;
        preNode = head;
        ConvertNodeInOrder(head.right);
    }
}
