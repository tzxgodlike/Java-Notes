package basic_02;

import java.util.Stack;

public class GetMinStack {
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int num) {
            if (stackMin.isEmpty()) {
                stackMin.push(num);
            } else if (num <= this.getMin()) {
                stackMin.push(num);
            }else{
                int newNum = stackMin.peek();
                stackMin.push(newNum);
            }
            stackData.push(num);
        }

        public Integer pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public Integer getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("empty");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }
}
