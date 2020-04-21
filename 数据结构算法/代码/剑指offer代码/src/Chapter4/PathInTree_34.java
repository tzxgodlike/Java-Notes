package Chapter4;

import utils.Node;

import java.util.ArrayList;
import java.util.List;

public class PathInTree_34 {
    /*
    输入一个二叉树和一个整数，打印二叉树中节点值的和为输入整数的所有路径 [根节点---->叶节点]

     */

    public static List<List<Integer>> printPath(Node head,int target) {
        if (head == null) return null;

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        //先序遍历
        preProcess (head,target,path,res);

        return res;
    }

    private static void preProcess(Node head, int target, List<Integer> path, List<List<Integer>> res) {

        if (head == null) return ;

        path.add(head.value);
        target -= head.value;

        //到了叶结点才算路径
        if (head.left==null&&head.right==null) {
            //值传递 所以要新建一个path的副本
            if (target==0) res.add(new ArrayList<>(path));
        }
        preProcess(head.left,target,path,res);
        preProcess(head.right,target,path,res);

        //这个节点走完后 要从path中删除 回溯到父节点
        path.remove(path.size()-1);
        target += head.value;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(5);
        head.right = new Node(12);
        head.left.left = new Node(4);
        head.left.right = new Node(7);

        System.out.println(printPath(head,22));
    }
}
