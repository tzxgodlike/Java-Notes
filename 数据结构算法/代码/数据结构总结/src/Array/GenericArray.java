package Array;

public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray(int capacity){
        data = (T[])new Object[capacity];
        size = 0;
    }
    // 无参构造方法，默认数组容量为10
    public GenericArray() {
        this(10);
    }

    //获取数组容量
    public int getCapacity(){
        return data.length;
    }

    //获取当前元素个数
    public int getCount(){
        return this.size;
    }
    //判断数组是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }
    //修改index位置的元素
    public void set(int index,T e) {
      checkIndex(index);
      this.data[index] = e;
    }
    //获得index位置的元素
    public T get (int index){
        checkIndex(index);
        return data[index];
    }
    //判断是否包含e
    public boolean contains(T e){
        for(int i =0;i<size;i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    // 获取对应元素的下标, 未找到，返回 -1
    public int find(T e) {
        for ( int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }
    //扩容
    public void reSize (int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0;i<data.length;i++) {
            newData[i] = data[i];
        }
        data = newData;

    }
    //在index位置插入add
    public void add(int index,T e) {
        checkIndex(index);
        if(size==data.length) {
            reSize(data.length*2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    //remove index位置上的元素
    public T remove(int index) {
        checkIndex(index);

        T ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size --;
        data[size] = null;

        // 缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            reSize(data.length / 2);
        }

        return ret;
    }
    //判断index是否有值
    public void checkIndex(int index){
        if (index<0||index>this.size){
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
