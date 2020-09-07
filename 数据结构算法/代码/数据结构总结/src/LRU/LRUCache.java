package LRU;

import java.util.HashMap;
import java.util.Map;

/**
 *  * 功能描述
 *  * 1.双向链表头部为最近使用 尾部为最久未使用
 *  * 2.get 若key不存在 则返回-1 若存在则返回value 并把该节点移动到头部
 *  * 3.put 若key不存在 创建新节点加入头部和哈希表中 并判断容量是否超过 来决定删除尾部节点
 *  * 若key存在 则更新value 并移动到头部
 *  * 4.复杂度都为O(1) 把节点移到头部 可以分解为删除节点和在头部添加该节点 都是O(1)
 *  * 
 *  * @since 2020-08-11
 *  
 */
public class LRUCache {

    // 双向链表+hashmap实现 即Linkedhashmap


    class DLinkedNode {
/*
         * 双向链表的功能
         * 1.在头部添加节点
         * 2.移除节点
         * 3.移动到头部 (可分解为 1 2)
         * 4.删除尾部
         */

        int key;


        int value;


        DLinkedNode prev;


        DLinkedNode next;


        public DLinkedNode() {

        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }


    public void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }


    public void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

// 要返回该节点 去再map中删除

    private DLinkedNode removeTail() {
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

 /*
     * 结合
     */

    private Map<Integer, DLinkedNode> map = new HashMap<>();


    private int size;


    private int capacity;


    private DLinkedNode head, tail;


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头节点和伪尾节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        if (!map.containsKey(key))
            return -1; // 没有key
        DLinkedNode node = map.get(key);
        moveToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        DLinkedNode node;
        // 不存在key
        if (!map.containsKey(key)) {
            node = new DLinkedNode(key, value);
            // 加入哈希表
            map.put(key, node);
            // 加入链表头部
            // 先加入再判断
            if (size > capacity) {
                DLinkedNode tail = removeTail();
                // 通过key来remove
                map.remove(tail.key);
            }
        } else {
            node = map.get(key);
            // 更新map
            node.value = value;
// 移到链表头部
            moveToHead(node);
        }
    }
}
