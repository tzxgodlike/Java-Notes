package Chapter2;

public class ReConstructTree_07 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    /**
     * 递归！
     * [preStart + 1, preStart + i - inStart]是前序序列中左子树封闭区间
     * [preStart + i - inStart + 1, preEnd]是前序序列中右子树封闭区间
     *
     * [inStart, i - 1]是中序序列中左子树封闭区间
     * [i + 1, inEnd]是中序序列中右子树封闭区间
     *
     * @param pre 前序序列
     * @param preStart 前序序列封闭区间的左指针
     * @param preEnd   前序序列封闭区间的右指针
     * @param in  中序序列
     * @param inStart 中序序列封闭区间的左指针
     * @param inEnd   中序序列封闭区间的右指针
     * @return  树的根结点
     */
    public Node buildTree(int[] preorder, int[] inorder) {
        Node root = reConstructTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        return root;
    }

    public Node reConstructTree(int[] pre, int preS,int preE,int[] in,int inS,int inE) {
        if (preS > preE||inS>inE ) {
            return null;
        }
        Node root = new Node(pre[preS]);  //先把根节点赋值
        for (int i  = inS;i<=inE;i++)  {//再去中序遍历中找这个根节点的index 
            if (in[i]==pre[preS]) {
                //找出左子树的前序和中序坐标范围
                root.left = reConstructTree(pre,preS+1,preS+i-inS,in,inS,i-1);
                //出错在右子树的前序起始坐标就为左子树的前序末尾+1 前序末尾坐标就为该次前序数组的末尾坐标
                root.right = reConstructTree(pre,preS+i-inS+1,preE,in,i+1,inE);
            }
        }
        return root;
    }


}
