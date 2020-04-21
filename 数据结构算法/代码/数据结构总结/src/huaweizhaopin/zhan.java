//package huaweizhaopin;
//
//import java.util.*;
//
//public class zhan {
//    public static class Node {
//        // whatever you like
//        public int index;
//        public int value;
//        public List<Integer> next ;
//
//        public Node(int index,int value,List<Integer> list){
//            this.index = index;
//            this.value = value;
//            this.next = list;
//        }
//
//        //public Node get (int i)
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int zushu_n = sc.nextInt();
//        int[]  zushu = new int[zushu_n];
//        Node[] nodes = new Node[zushu_n];
//
//        List<Node> nodeList = new ArrayList<>();
//
//        for (int i = 0;i<zushu_n;i++) {
//            zushu[i] = sc.nextInt();
//        }
//
//        //sc.nextLine();
//
//        for (int i = 0;i<zushu_n;i++) {
//            int index = sc.nextInt();
//            int value = sc.nextInt();
//            List<Integer> tmp = new ArrayList<>();
//            for (int j = 0;j<zushu[i];j++){
//                tmp.add(sc.nextInt());
//            }
//            nodeList.add(new Node(index,value,tmp));
//        }
//        //UnionFindSet unionFindSet = new UnionFindSet(nodeList);
//
//        for (int i = 0;i<nodeList.size();i++) {
//            List<Integer> cur = new ArrayList<>();
//            cur = nodeList.get(i).next;
//            for (int j = 0;j<cur.size();j++){
//                unionFindSet.union(nodeList.get(i),nodeList.get(cur.get(j)));
//            }
//        }
//        //System.out.println(unionFindSet.fatherMap);
//        //System.out.println(unionFindSet.sizeMap);
//    }
////    public static class UnionFindSet{
////        public HashMap<Node,Node> fatherMap;//key:child value:father
////        public HashMap<Node,Integer> sizeMap;
////
////        public UnionFindSet(List<Node> nodes){
////            fatherMap = new HashMap<Node, Node>();
////            sizeMap = new HashMap<Node, Integer>();
////            //一次性接受样本
////            makeSets(nodes);
////
////        }
////
////        public void makeSets(List<Node> nodes){
////            //最开始每个点自己形成集合
////            for(Node node:nodes){
////                fatherMap.put(node,node);
////                sizeMap.put(node,1);
////            }
////        }
////        //递归找代表节点
////        public Node findHeadRecur(Node node){
////            Node father = fatherMap.get(node);
////            if(father!=node){
////                father = findHeadRecur(father);
////            }
////            fatherMap.put(node,father);
////            return father;
////        }
////        //非递归找代表节点
////        public Node findHeadUnRecur(Node node){
////            Node father = node;
////            //第一次循环找到father
////            while(father!=fatherMap.get(father)){
////                father = fatherMap.get(father);
////            }
////            //第二次遍历来优化
////            while(node!=father){
////                fatherMap.put(node,father);
////                node = fatherMap.get(node);
////            }
////            return father;
////        }
////
////
////        public boolean isSameSet(Node a,Node b){
////            return findHeadRecur(a)==findHeadRecur(b);
////        }
////
////        public void union(Node a,Node b){
////            if(a==null||b==null){
////                return;//结束程序 不返回
////            }
////            Node aHead = findHeadRecur(a);
////            Node bHead = findHeadRecur(b);
////            if(aHead!=bHead){
////                int aSetSize = sizeMap.get(aHead);
////                int bSetSize = sizeMap.get(bHead);
////                if(aSetSize<=bSetSize){
////                    fatherMap.put(aHead,bHead);
////                    sizeMap.put(bHead,aSetSize+bSetSize);
////                }else {
////                    fatherMap.put(bHead,aHead);
////                    sizeMap.put(aHead,aSetSize+bSetSize);
////                }
////            }
////
////        }
//   // }
//}
