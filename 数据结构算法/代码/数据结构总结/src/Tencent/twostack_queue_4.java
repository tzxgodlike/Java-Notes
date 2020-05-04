package Tencent;

import java.util.Scanner;
import java.util.Stack;

public class twostack_queue_4 {
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
                    stackPop.push(stackPush.pop());
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
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.nextLine();

        TwoStackQueue twoStackQueue = new TwoStackQueue();

        for (int i = 0;i<n;i++) {
            String[] s = sc.nextLine().split(" ");
            //System.out.println(s[0]);
            //System.out.println(s[0]=="add");
            //System.out.println(twoStackQueue);
            if (s[0].equals("add")) {
                //System.out.println(Integer.parseInt(s[1]));
                twoStackQueue.push(Integer.parseInt(s[1]));
                //System.out.println(twoStackQueue.peek());
            }else if (s[0].equals("poll")) {
                twoStackQueue.poll();
            }else if (s[0].equals("peek")) {
                System.out.println(twoStackQueue.peek());
            }
        }
    }
}
