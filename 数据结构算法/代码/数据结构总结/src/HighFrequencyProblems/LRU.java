package HighFrequencyProblems;

import java.util.HashMap;

public class LRU {
    /*
    最近未使用缓存淘汰策略
    https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/LRU%E7%AE%97%E6%B3%95.md

    1.算法描述
    LRU 算法实际上是让你设计数据结构：首先要接收一个 capacity 参数作为缓存的最大容量，然后实现两个 API，
    一个是 put(key, val) 方法存入键值对，另一个是 get(key) 方法获取 key 对应的 val，如果 key 不存在则返回 -1。
    get 和 put 方法必须都是 $O(1)$ 的时间复杂度

    2.数据结构  使用哈希表+双向链表 最近的放在链尾
     */
    class Node {
        public int key, val;
        public Node next, prev;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
            this.next  = null;
            this.prev  = null;
        }
    }

    //构建双链表
    class DoubleList{
        public Node head,tail;  //头部是最老的 尾部最新
        public DoubleList() {
            this.head = null;
            this.tail = null;
        }
        //在链表尾部添加节点  O(1)
        public void addTail(Node x) {
            if (x==null) return ;
            if (head==null) {
                head = x;
                tail = x;
            }else {
                tail.next = x;
                //x.next = null;
                x.prev = tail;
                tail = x;
            }


        }

        //访问某节点并移到尾部
        public void moveTail(Node x) {
            if (x == null || x == tail) return;
            //先把该节点从链表删除
            //当该节点头节点为头结点时
            if (x == head) {
                head = head.next;
                head.prev = null;
            } else {
                //双向链表 便于删除结点
                x.prev.next = x.next;
                x.next.prev = x.prev;
            }
            tail.next = x;
            x.prev = tail;
            x.next = null;
            tail = tail.next;
        }
        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
//        public void remove(Node x);

        // 删除链表中最老的节点，并返回该节点，时间 O(1)
        public Node removeHead() {
            if (head == null) return null;
            Node n = head;
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                head = head.next;
                head.prev = null;
            }
            return n;
        }

        // 返回链表长度，时间 O(1)
        //public int size();
    }


    private DoubleList list;
    private HashMap<Integer, Node> map;
    private int capacity;

    public LRU(int capacity) {
        this.list = new DoubleList();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node n = map.get(key);
        list.moveTail(n);
        return n.val;
    }

    public void put(int key,int value) {
        if(!map.containsKey(key)) {
            Node node = new Node(key,value);
            map.put(key,node);
            list.addTail(node);

            if (map.size()>capacity) {
                Node rmv = list.removeHead();
                //如果只在map中保存value 那么map就无法删除队头的点
                map.remove(rmv.key);
            }
        } else {
            //把value更新 并移到队尾
            Node n = map.get(key);
            n.val = value;
            list.moveTail(n);
        }
    }

    public static void main(String[] args) {
        LRU set = new LRU(3);
        set.put(3,3);
        set.put(2,2);
        set.put(1,1);
        set.get(3);
    }

}
