package basic_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class StackAndQueueConvert {
    public static class TwoQueueStack{
        private Queue<Integer> data;
        private Queue<Integer> help;

        public TwoQueueStack(){
            this.data = new LinkedList<Integer>();
            this.help = new LinkedList<Integer>();
        }
        public void push(Integer num){
            data.add(num);
        }
        public Integer peek(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            while(data.size()!=1){
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }
        public Integer pop(){
            if(data.isEmpty()){
                throw new RuntimeException("Stack is empty!");
            }
            while(data.size()!=1){
                help.add(data.poll());
            }
            int res = data.poll();
            //help.add(res);
            swap();
            return res;
        }
        public void swap(){
            Queue<Integer> temp = help;
            help = data;
            data = temp;
        }
    }

    public static class TwoStackQueue{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue(){
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }
        public void push(int num){
            stackPush.push(num);
        }
        public Integer poll(){
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }else if(stackPop.isEmpty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPush.pop());
            }}
            int res = stackPop.pop();
            while(!stackPop.isEmpty()){
                stackPush.push(stackPop.pop());
            }
            return res;
        }
        public Integer peek(){
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }else if(stackPop.isEmpty()){
                while (!stackPush.empty()){
                    stackPop.push(stackPop.pop());
                }
            }
            int res = stackPop.peek();
            while(!stackPop.isEmpty()){
                stackPush.push(stackPop.pop());
            }
            return res;
        }
        public boolean empty(){ //判断队是否为空
            return stackPush.isEmpty();
        }

    }
    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int data = rand.nextInt(100);
            System.out.print(data + " ");
            queue.push(data);
        }
        System.out.println();
        System.out.println("出队：");
        while (!queue.empty()) {
            System.out.print(queue.poll()+" ");
        }
    }
}
