package basic_03;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//先中后 - 遍历  递归
public class PreInPosTraversal {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head){
        if(head==null){
            return ;
        }
        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head){
        if(head==null){
            return ;
        }

        inOrderRecur(head.left);
        System.out.print(head.value+" ");
        inOrderRecur(head.right);
    }
    public static void posOrderRecur(Node head){
        if(head==null){
            return ;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }
    //先序非递归 循环之前先把head压栈 再遍历 先放右子再放左子 非空时打印
    public static void preOrderUnRecur(Node head){
        //ArrayList<Integer> list = new ArrayList<>();
        //list.get()
        System.out.print("pre-order");
        if(head!=null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value);
                if(head.right!=null){
                    stack.push(head.right);
                }
                if(head.left!=null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }
    //中序遍历 有点没逻辑 先把左边界压栈
    //当前节点为空，出栈一个打印，当前节点变为出栈节点的右节点
    //当前节点不空，当前节点压栈，当前节点变左
    public static void inOrderUnRecur(Node head){
        System.out.print("in-order");
        if(head!=null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty()||head!=null){
                if(head!=null){
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.println(head.value+" ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    //后序遍历非递归 好理解 用两个栈
    //思路：先构建一个类似先序遍历的过程 但是是中右左
    //把该过程的打印部分换成压到另外一个栈 该栈的出栈顺序就是左右中 正好是后序遍历
    public static void posOrderUnRecur1(Node head){
        System.out.print("pos-order");
        if(head!=null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                //System.out.println(head.value);
                s2.push(head);
                if(head.left!=null){
                    s1.push(head.left);
                }
                if(head.right!=null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.println(s2.pop()+" ");
            }
        }
        System.out.println();
    }
    //只用一个栈 炫技写法

    public static void posOrderUnRecur2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }
/*
    层次遍历二叉树
     */

    public static void printTreeByLayer(Node head) {
        if (head==null) return ;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.println(head.value);
            if (head.left!=null) queue.offer(head.left);
            if (head.right!=null) queue.offer(head.right);
        }
    }
}
