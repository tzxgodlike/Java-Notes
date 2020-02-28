package basic_02;

public class Array_To_Stack_Queue {
    public static class ArrayStack{
        private Integer[] arr;
        private Integer index;//指向栈顶的上一层

        public ArrayStack(int initSize){
            if(initSize<0){
                throw new IllegalArgumentException("size should more than 0");
            }
            arr = new Integer[initSize];
            index = 0;
        }
        //peek不删除栈顶值
        public Integer peek(){
            if(index==0){
                return null;
            }
            return arr[index-1];
        }
        //pop删除栈顶值
        public Integer pop(){
            if(index==0){
                throw new ArrayIndexOutOfBoundsException("stack is empty");
            }
            return arr[--index];
        }
        public void push(int num){
            if(index==arr.length){
                throw new ArrayIndexOutOfBoundsException("stack is full");
            }
            arr[index++] = num;
        }
    }

    public static class ArrayQueue{
        private Integer[] arr;
        private Integer size;
        private Integer front;
        private Integer rear;
        //peek push poll
        public ArrayQueue(int initSize){
            if(initSize<0){
                throw new IllegalArgumentException("size should more than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            front = 0;
            rear = 0;
        }
        //peek查询队首
        public Integer peek(){
            if(size==0){
                return null;
            }
            return arr[front];
        }
        //front指向第一个元素 rear指向最后一个元素的下一位
        //front移动到数组最后一位时，再出队会变到第一位
        public Integer poll(){
            if(size==0){
                throw new ArrayIndexOutOfBoundsException("queue is empty");
            }
            size--;
            int temp = front;
            front = front==arr.length-1?0:front+1;
            return arr[temp];
        }
        public void push(int num){
            if(size==arr.length){
                throw new ArrayIndexOutOfBoundsException("queue is full");

            }
            size++;
            arr[rear] = num;
            rear = rear==arr.length?0:rear+1;

        }

    }

}
