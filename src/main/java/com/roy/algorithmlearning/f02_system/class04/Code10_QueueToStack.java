package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.f05_bean.SingleNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用单向队列去实现栈
 * <p>
 * ps： 两个队列实现
 */
public class Code10_QueueToStack {

    public static class MyQueueStack<T> {

        Queue<T> queue;
        Queue<T> helpQueue;

        public MyQueueStack() {
            queue = new LinkedList<>();
            helpQueue = new LinkedList<>();
        }

        public void push(T t) {
            queue.offer(t);
        }

        public T pop() {
            while (queue.size() > 1) {
                helpQueue.offer(queue.poll());
            }
            T ans = queue.poll();
            fallBack();
            return ans;
        }

        public T peek() {
            T ans = queue.peek();
            while (!queue.isEmpty()) {
                if (queue.size() == 1) {
                    ans = queue.peek();
                }
                helpQueue.offer(queue.poll());
            }
            fallBack();
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void fallBack() {
            Queue<T> temp = helpQueue;
            helpQueue = queue;
            queue = temp;
        }

    }

    public static void main(String[] args) {
        MyQueueStack<Integer> myQueueStack = new MyQueueStack<>();
        Stack<Integer> stack = new Stack<>();

        myQueueStack.push(1);
        myQueueStack.push(2);
        myQueueStack.push(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        while (!myQueueStack.isEmpty()) {
            int num1 = myQueueStack.pop();
            int num2 = stack.pop();

            if (num1 != num2) {
                System.out.println("出错啦！");
            } else {
                System.out.println("num1: " + num1 + " num2: " + num2);
            }
        }

        myQueueStack.push(4);
        myQueueStack.push(5);
        myQueueStack.push(6);

        stack.push(4);
        stack.push(5);
        stack.push(6);

        int num3 = myQueueStack.peek();
        int num4 = stack.peek();
        if (num3 != num4) {
            System.out.println("出错啦！");
        }

        while (!myQueueStack.isEmpty()) {
            int num1 = myQueueStack.pop();
            int num2 = stack.pop();

            if (num1 != num2) {
                System.out.println("出错啦！");
            } else {
                System.out.println("num1: " + num1 + " num2: " + num2);
            }
        }

        System.out.println("测试结束！");
    }

}
