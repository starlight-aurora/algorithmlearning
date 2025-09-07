package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.f05_bean.DoubleNode;

import java.util.Stack;

/**
 * 双向链表实现栈(先进后出， 后进先出)
 */
public class Code05_NodeToStack {

    public static class MyStack<T> {

        DoubleNode<T> head = null;
        DoubleNode<T> tail = null;

        public void push(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else  {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public T pop() {
            if (head == null) {
                return null;
            } else {
                DoubleNode<T> node = tail;
                tail = tail.last;
                if (tail == null){
                    head = null;
                }else {
                    tail.next = null;
                }
                return node.value;
            }
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> stack = new Stack<>();

        myStack.push(1);
        myStack.push(2);

        stack.push(1);
        stack.push(2);

        while (!myStack.isEmpty()){
            int num1 = myStack.pop();
            int num2 = stack.pop();
            if (num1 != num2){
                System.out.println("出错啦！");
            }
        }

        myStack.push(3);
        myStack.push(4);

        stack.push(3);
        stack.push(4);

        while (!stack.isEmpty()){
            int num1 = myStack.pop();
            int num2 = stack.pop();
            if (num1 != num2){
                System.out.println("出错啦！");
            }
        }

        System.out.println("测试结束！");
    }

}
