package Stack;

public class ArrayStack {
    private int[] items;
    private int count;  //栈元素个数
    private int size;   //栈大小

    public ArrayStack(int capacity) {
        this.size = capacity;
        this.items = new int[capacity];
        this.count = 0;
    }

    //入栈
    public boolean push(int item) {
        if (count == size) return false;
        items[count] = item;
        count++;
        return true;
    }
    //出栈
    public int pop() {
        if (count == 0) return 0;
        int tmp = items[count-1];
        --count;
        return tmp;
    }
}
