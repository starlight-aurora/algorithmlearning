package com.roy.algorithmlearning.f02_system.class04;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2）设计的栈类型可以使用现成的栈结构。
 *
 */
public class  Code08_SpecialStack {

    public static class SpecialStack{
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public SpecialStack(){
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x){
            stack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            } else {
                int min = getMin();
                if(x<=min){
                    minStack.push(x);
                }
            }
        }

        public int pop(){
            if(stack.isEmpty()){
                throw  new RuntimeException("栈已经空了！");
            }
            int x = stack.pop();
            if (x == getMin()){
                minStack.pop();
            }
            return x;
        }

        public int getMin(){
            if(minStack.isEmpty()){
                throw  new RuntimeException("栈已经空了！");
            }
            return minStack.peek();
        }

        public boolean isEmpty(){
            return stack.isEmpty();
        }
    }

    public static class SpecialStack2{
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public SpecialStack2(){
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x){
            stack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            } else {
                int min = getMin();
                minStack.push(Math.min(x, min));
            }
        }

        public int pop(){
            if(minStack.isEmpty()){
                throw  new RuntimeException("栈已经空了！");
            }
            int x = stack.pop();
            minStack.pop();
            return x;
        }

        public int getMin(){
            if(minStack.isEmpty()){
                throw  new RuntimeException("栈已经空了！");
            }
            return minStack.peek();
        }

        public boolean isEmpty(){
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        SpecialStack ss = new SpecialStack();

        ss.push(3);
        ss.push(2);
        ss.push(1);
        ss.push(2);
        ss.push(1);
        ss.push(3);

        while (!ss.isEmpty()) {
            int min = ss.getMin();
            int popped = ss.pop();

            if(popped < min){
                System.out.println("出错啦！");
            }
        }

        System.out.println("测试结束！");
    }


}
