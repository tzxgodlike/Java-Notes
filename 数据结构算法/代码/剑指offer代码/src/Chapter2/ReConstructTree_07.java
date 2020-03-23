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
    public static Node reConstructTree(int[] pre,int[] in){
        if(pre==null||in==null){
            return null;
        }
        Node root = reConstructTreeCore(pre,0,pre.length,in,0,in.length);
        return root;
    }

    public static Node reConstructTreeCore(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        // 还有子数组就继续递归，不存在子数组了（表现为end > start）,就返回空子树给父结点
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = pre[preStart];
        Node root = new Node(rootVal);
        for (int i = inStart;i<=inEnd;i++){
            if (in[i]==rootVal){
                root.left = reConstructTreeCore(pre,preStart+1,preStart+i-inStart,in,inStart,i-1);
                root.right =reConstructTreeCore(pre, preStart + i - inStart + 1, preEnd, in, i + 1, inEnd);
            }
        }
        return root;
    }
}
