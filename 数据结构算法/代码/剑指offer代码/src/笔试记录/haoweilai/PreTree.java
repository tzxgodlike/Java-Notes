package 笔试记录.haoweilai;

import utils.TreeNode;

import java.util.*;

public class PreTree {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        System.out.println(notReCuPreOrder(head));
    }

    public static String notReCuPreOrder (TreeNode root) {
        // write code here
        StringBuilder sb = new StringBuilder();
        if(root!=null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()){
                root = stack.pop();
                //System.out.print(head.value);
                sb.append(root.value);
                sb.append(',');
                if(root.right!=null){
                    stack.push(root.right);
                }
                if(root.left!=null){
                    stack.push(root.left);
                }
            }
        }
        //System.out.println();

        return sb.toString().substring(0,sb.length()-1);
    }
}
