package basic_04;

import java.util.HashMap;

public class TrieTree {
    public static class TrieNode {
        public int path;
        public int end;
       // public HashMap<Character,TrieNode> hashMap; 数组也可换成hashmap
        public TrieNode[] nexts;
        // 如果是小写字母的话，每个结点后面最多跟26个节点
        // 看成每个结点下面有26个节点 分别是字母和null

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }
    public static class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        public void insert(String word){
            if(word==null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0;i<chs.length;i++){
                index = chs[i] - 'a';
                if(node.nexts[index] ==null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }
        //首先search 确保有这个word(如abc)
        // 沿途的点path减一 如果某个节点的path减一后为0
        // 可以直接把后面置空 因为必有这个Word 且只有一个
        public void delete(String word){
            if(search(word)!=0){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for(int i =0;i<chs.length;i++){
                    index = chs[i]-'a';
                    if(--node.nexts[index].path==0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }


        //查询word出现了几次
        public int search(String word){
            if(word==null){
                return 0;
            }
            char[] chs =word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i =0;i<chs.length;i++){
                index = chs[i]-'a';
                if(node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        //查某个字符串前缀数量是多少
        public int prefixNumber(String pre){
            if(pre==null){
                return 0;
            }
            char[] chs =pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i]-'a';
                if(node.nexts[index]==null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("tang"));
        trie.insert("tang");
        System.out.println(trie.search("tang"));
        trie.delete("tang");
        System.out.println(trie.search("tang"));
        trie.insert("tang");
        trie.insert("tang");
        trie.delete("tang");
        System.out.println(trie.search("tang"));
        trie.delete("tang");
        System.out.println(trie.search("tang"));
        trie.insert("tanga");
        trie.insert("tangb");
        trie.insert("tangc");
        trie.insert("tangd");
        trie.delete("tanga");
        System.out.println(trie.search("tanga"));
        System.out.println(trie.prefixNumber("tang"));

    }
}
