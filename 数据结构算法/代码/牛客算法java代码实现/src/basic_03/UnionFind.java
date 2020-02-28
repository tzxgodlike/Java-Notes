package basic_03;

import java.util.HashMap;
import java.util.List;

public class UnionFind {
    public static class Node {
        // whatever you like
    }

    public static class UnionFindSet{
        public HashMap<Node,Node> fatherMap;//key:child value:father
        public HashMap<Node,Integer> sizeMap;

        public UnionFindSet(List<Node> nodes){
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
            //一次性接受样本
            makeSets(nodes);

        }

        public void makeSets(List<Node> nodes){
            //最开始每个点自己形成集合
            for(Node node:nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
        }
        }
        //递归找代表节点
        public Node findHeadRecur(Node node){
            Node father = fatherMap.get(node);
            if(father!=node){
                father = findHeadRecur(father);
            }
            fatherMap.put(node,father);
            return father;
        }
        //非递归找代表节点
        public Node findHeadUnRecur(Node node){
            Node father = node;
            //第一次循环找到father
            while(father!=fatherMap.get(father)){
                father = fatherMap.get(father);
            }
            //第二次遍历来优化
            while(node!=father){
                fatherMap.put(node,father);
                node = fatherMap.get(node);
            }
            return father;
        }


        public boolean isSameSet(Node a,Node b){
            return findHeadRecur(a)==findHeadRecur(b);
        }

        public void union(Node a,Node b){
            if(a==null||b==null){
                return;//结束程序 不返回
            }
            Node aHead = findHeadRecur(a);
            Node bHead = findHeadRecur(b);
            if(aHead!=bHead){
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if(aSetSize<=bSetSize){
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aSetSize+bSetSize);
                }else {
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aSetSize+bSetSize);
                }
            }

        }
    }
}
