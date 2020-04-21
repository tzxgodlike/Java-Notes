package basic_04;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    /*
    [IPO]一个项目有两个数组，cost[]成本和profit[]纯利润。
    给初始资金，一次做一个项目，一个项目只能做一次。做K个项目，最多能有多少钱？
     */
    public static class Node{
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
    public static class MinCostComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;        }
    }
    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k,int W,int[] Profits,int[] Capital){
        Node[] nodes = new Node[Profits.length];
        for(int i = 0;i < nodes.length;i ++){
            nodes[i] = new Node(Profits[i],Capital[i]);
        }
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for(int i = 0;i<nodes.length;i++){
            minCostQ.add(nodes[i]);
        }
        for (int i = 0;i < k;i++){
            while(!minCostQ.isEmpty()&&minCostQ.peek().c<=W){
                maxProfitQ.add(minCostQ.poll());
            }
            //某种数据会导致 资金不够做下一个项目
            //
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
    public static void main(String[] args) {

    }
}
