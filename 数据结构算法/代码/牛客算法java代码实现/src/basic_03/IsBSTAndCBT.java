package basic_03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //判断搜索二叉树 中序遍历是有序的 那就用一个pre记录中序遍历前一个被打印的值 与每次被打印的值比较
    public static boolean inOrderUnRecur(Node head) {
        System.out.print("in-order");
        int pre = Integer.MIN_VALUE;
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    //System.out.println(head.value+" ");

                    if (head.value <= pre) {
                        return false;
                    } else {
                        pre = head.value;
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    //判断是否完全二叉树  按层次遍历
    public static boolean isCBT(Node head){
        if(head==null){
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;

            if((leaf&&(l!=null||r!=null)) //leaf状态开启时，head必须为叶子节点
            ||
                    (l==null&&r!=null)){  //有右无左
                return false;
            }
            if(l!=null){
                queue.offer(l);
            }
            if(r!=null){
                queue.offer(l);
            }else {
                //当右节点空时，要开启leaf状态
                leaf = true;
            }
        }
        return true;
    }


    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(inOrderUnRecur(head));
}}
