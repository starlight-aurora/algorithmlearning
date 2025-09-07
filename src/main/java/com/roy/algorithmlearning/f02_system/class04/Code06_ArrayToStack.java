package com.roy.algorithmlearning.f02_system.class04;

import java.util.Stack;

/**
 * 数组实现栈
 */
public class Code06_ArrayToStack {

    public static class MyStack {
        private int[] arr = new int[10];
        int index = 0;    // 下一个要放元素的位置

        public MyStack() {}

        public void push(int x) {
            if (index >= (int) (arr.length * 0.8)) {
                expandArray();
            }
            arr[index++] = x;
        }

        public void expandArray() {
            int[] temp = new int[arr.length * 2];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
        }

        public int pop() {
            if (index == 0) {
                throw new RuntimeException("栈已经空了！");
            }
            return arr[--index];
        }

        public boolean isEmpty() {
            return index == 0;
        }

    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        Stack<Integer> stack = new Stack<>();

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = stack.pop();
            if (num1 != num2) {
                System.out.println("出错啦！");
            }
        }

        System.out.println("测试结束！");
    }

}
