package basic_03;

import java.util.HashMap;

public class RandomPool<K> {
    public HashMap<K,Integer> keyIndexMap;
    public HashMap<Integer,K> indexKeyMap;

    public int size;

    public RandomPool(){
        this.keyIndexMap = new HashMap<K, Integer>();
        this.indexKeyMap = new HashMap<Integer, K>();
        this.size = 0;
    }

    public void insert(K key){
        if(!this.keyIndexMap.containsKey(key)){
            this.keyIndexMap.put(key,this.size);
            this.indexKeyMap.put(this.size++,key);
        }
    }
    //要保证map中的Integer是0~size-1 所以每移除一个key,就把最大的index
    // 对应的key放到被移除的位置
    public void detele(K key){
        if(this.keyIndexMap.containsKey(key)){
            int deleteIndex = this.keyIndexMap.get(key);
            int lastIndex = --this.size;    //size-1为最后一个标号
            K lastKey = this.indexKeyMap.get(lastIndex);
            //覆盖之前的key
            this.keyIndexMap.put(lastKey,deleteIndex);
            this.indexKeyMap.put(deleteIndex,lastKey);
            this.keyIndexMap.remove(key);
            this.indexKeyMap.remove(lastIndex);
        }
    }

    public K getRandom(){
        if(this.size==0){
            return null;
        }
        int randomIndex = (int)(Math.random()*this.size); //0~size-1
        return indexKeyMap.get(randomIndex);
    }
    public static void main(String[] args) {
        RandomPool<String> pool = new RandomPool<String>();
        pool.insert("tang");
        pool.insert("zhi");
        pool.insert("tang");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }
}
