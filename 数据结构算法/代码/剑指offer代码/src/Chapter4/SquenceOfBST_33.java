package Chapter4;

public class SquenceOfBST_33 {

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
     *
     * BST一般不考虑重复节点
     */

    public static boolean isBST(int[] sequence) {
        if (sequence==null||sequence.length==0) return false;

        return findBST(sequence,0,sequence.length-1);
    }

    private static boolean findBST(int[] sequence, int start, int end) {

        //终止条件  start==end 说明到了叶节点 直接返回true
        // 若上层没有左子树 则findBST(sequence,start,i-1)中i-1比start小 也返回true
        if (start>=end) return true;
        //每次确认一颗子树   root为当前子树的根节点
        int root = sequence[end];

        //然后找到左右子树对应的区间  比根节点小的都是左子树 其他的为右子树

        int i = start;   //[start,i-1]为左子树  [i,end-1]右子树
        while (i < end&&sequence[i]<root) {
            i++;
        }
        int j = i;
        for (;j<=end-1;j++) {
            if (sequence[j]<root) return false;
        }

        //再递归判断左右子树

        return findBST(sequence,start,i-1) && findBST(sequence,i,end-1);
    }

    public static void main(String[] args) {
        System.out.println(isBST(new int[]{7,4,6,5}));
    }
}
