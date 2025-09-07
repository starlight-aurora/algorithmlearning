package com.roy.algorithmlearning.f02_system.class04;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 用栈去实现队列
 *
 * ps： 两个栈去实现
 */
public class Code09_StackToQueue {

    public static class MyStackQueue{

        Stack<Integer> stack;
        Stack<Integer> helpStack;

        public MyStackQueue(){
            stack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void add(int x){
            stack.push(x);
            help();
        }

        public int poll(){
            help();
            if(helpStack.isEmpty()){
                throw new RuntimeException("队列已经空了！");
            }
            return helpStack.pop();
        }

        public int peek(){
            help();
            if(helpStack.isEmpty()){
                throw new RuntimeException("队列已经空了！");
            }
            return helpStack.peek();
        }

        public void help(){
            if(helpStack.isEmpty() ){
                while(!stack.isEmpty()){
                    helpStack.push(stack.pop());
                }
            }
        }

        public boolean isEmpty(){
            help();
            return helpStack.isEmpty();
        }

    }

    public static void main(String[] args) {
        MyStackQueue myStackQueue = new MyStackQueue();
        LinkedList<Integer> linkedList = new LinkedList<>();

        myStackQueue.add(1);
        myStackQueue.add(2);
        myStackQueue.add(3);

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        while (!myStackQueue.isEmpty()){
            int num1 = myStackQueue.poll();
            int num2 = linkedList.poll();

            if (num1 != num2){
                System.out.println("出错啦！");
            } else  {
                System.out.println("num1: " +  num1 + " num2: " + num2);
            }
        }

        myStackQueue.add(4);
        myStackQueue.add(5);
        myStackQueue.add(6);

        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        int num3 = myStackQueue.peek();
        int num4 = linkedList.peek();

        if (num3 != num4){
            System.out.println("出错啦！");
        }

        while (!myStackQueue.isEmpty()){
            int num1 = myStackQueue.poll();
            int num2 = linkedList.poll();

            if (num1 != num2){
                System.out.println("出错啦！");
            } else  {
                System.out.println("num1: " +  num1 + " num2: " + num2);
            }
        }

        System.out.println("测试结束！");
    }

}
