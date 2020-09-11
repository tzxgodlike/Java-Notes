package 笔试记录.网易;

import java.util.HashMap;
import java.util.Scanner;
class Tree_Node {


    public int value;
    public Tree_Node left;
    public Tree_Node right;

    public Tree_Node(int data) {
        this.value = data;
    }
}
public class 树上摘樱桃 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int m = sc.nextInt();
        sc.nextLine();
        HashMap<Integer,Tree_Node> map = new HashMap<>();

        for (int i = 1;i<=n;i++) {
            Tree_Node tmp = new Tree_Node(i);
            map.put(i,tmp);
        }
        for (int i = 0;i<m;i++) {
            String[] s = sc.nextLine().split(" ");
            //System.out.println(s[0]);
            int rootV = Integer.parseInt(s[0]);
            Tree_Node root = map.get(rootV);
            //map.put(rootV,root);
            int childV = Integer.parseInt(s[2]);
            Tree_Node child = map.get(childV);
            //map.put(childV,child);
            if (s[1].equals("left")) {
                root.left = child;
            }else if (s[1].equals("right")) {
                root.right = child;
            }
        }
        Tree_Node root_Node = map.get(1);
        HashMap<Tree_Node,Integer> isLeaf = new HashMap<>();
        finfLeaf(root_Node,isLeaf);
        int[] cnt = new int[1];
        finfLeaf_2(root_Node,isLeaf,cnt);
        System.out.println(cnt[0]);
    }
    public static void finfLeaf_2 (Tree_Node root,HashMap<Tree_Node,Integer> isLeaf,int[] cnt) {
        if (root==null) return ;
        if (root.left!=null&&root.right!=null) {

            if (isLeaf.get(root.left)==1&&isLeaf.get(root.right)==1) {
                cnt[0] = cnt[0]+1;
            }
        }
        finfLeaf_2(root.left,isLeaf,cnt);
        finfLeaf_2(root.right,isLeaf,cnt);
    }


    public static void finfLeaf(Tree_Node root,HashMap<Tree_Node,Integer> isLeaf) {
        if (root==null) return ;
        if (root.left==null&&root.right==null){
            isLeaf.put(root,1);  //是叶子节点
        }else  {
            isLeaf.put(root,0);
        }
        finfLeaf(root.left,isLeaf);
        finfLeaf(root.right,isLeaf);
    }
}
