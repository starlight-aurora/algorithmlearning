package com.roy.algorithmlearning.f01_beginner.class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedListToQueueAndStack {

    public static void main(String[] args) {

        testMyQueue();
        testMyStack();
    }

    public static void testMyQueue(){
        System.out.println("MyQueue测试开始！！！");
        int testTimes = 10000;
        int maxValue = 1000;
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> sysQueue = new LinkedList<>();
        for (int i = 0; i < testTimes; i++){
            if (myQueue.isEmpty() != sysQueue.isEmpty()){
                System.out.println("出错啦！！！");
            }
            if(myQueue.size != sysQueue.size()){
                System.out.println("出错啦！！！");
            }
            double decideValue = Math.random();
            if (decideValue < 0.3){
                int value = (int)(Math.random() * maxValue);
                myQueue.addItem(value);
                sysQueue.add(value);
            } else if (decideValue < 0.6){
                if (!myQueue.isEmpty()){
                    int itemValue = myQueue.getItem();
                    int polledValue = sysQueue.poll();
                    if (itemValue != polledValue){
                        System.out.println("出错啦！！！itemValue = " + itemValue + ", polledValue = " + polledValue);
                    }
                }
            } else {
                if (!myQueue.isEmpty()){
                    int peekedItemValue = myQueue.peekItemValue();
                    int peekedValue = sysQueue.peek();
                    if (peekedItemValue != peekedValue){
                        System.out.println("出错啦！！！peekedItemValue = " + peekedItemValue + ", peekedValue = " + peekedValue);
                    }
                }
            }
        }
        System.out.println("测试结束！！！");
    }

    public static void testMyStack(){
        System.out.println("MyStack测试开始！！！");
        int testTimes = 10000;
        int maxValue = 1000;
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> sysStack = new Stack<>();
        for (int i = 0; i < testTimes; i++){
            if (myStack.isEmpty() != sysStack.isEmpty()){
                System.out.println("出错啦！！！");
            }
            if(myStack.size != sysStack.size()){
                System.out.println("出错啦！！！");
            }
            double decideValue = Math.random();
            if (decideValue < 0.3) {
                int value = (int)(Math.random() * maxValue);
                myStack.addItem(value);
                sysStack.add(value);
            }else if (decideValue < 0.6){
                if (!myStack.isEmpty()){
                    int myStackItemValue = myStack.getItemValue();
                    int sysStackValue = sysStack.pop();
                    if (myStackItemValue != sysStackValue){
                        System.out.println("出错啦！！！");
                    }
                }
            }else {
                if (!myStack.isEmpty()){
                    int myStackItemValue = myStack.peekItemValue();
                    int sysStackValue = sysStack.peek();
                    if (myStackItemValue != sysStackValue){
                        System.out.println("出错啦！！！");
                    }
                }
            }
        }
        System.out.println("测试结束！！！");
    }

    public static class Node<V>{
        V value;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class MyQueue<V>{
        Node<V> head;
        Node<V> tail;
        int size;

        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty(){
            return size ==0;
        }

        public int size(){
            return size;
        }

        public void addItem(V value){
            Node<V> currentNode = new Node<>(value);
            if (this.size == 0){
                head = currentNode;
                tail = currentNode;
            }else {
                tail.next = currentNode;
                tail = currentNode;
            }
            size++;
        }

        public V getItem(){
            V result = null;
            if (head != null) {
                result = head.value;
                head = head.next;
                if (head == null){
                    tail = null;
                }
                size--;
            }
            return result;
        }

        //只获取当前node的值，不弹出
        public V peekItemValue(){
            V result = null;
            if (head != null){
                result = head.value;
            }
            return result;
        }
    }

    public static class MyStack<V>{
        Node<V> head;

        int size;

        public MyStack() {
            this.head = null;
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        public void addItem(V value){
            Node<V> currentNode = new Node<>(value);
            if (size ==0){
                head = currentNode;
            }else {
                currentNode.next = head;
                head = currentNode;
            }
            size++;
        }

        public V getItemValue(){
            V result = null;
            if(head != null){
                result = head.value;
                head = head.next;
                size--;
            }
            return result;
        }

        public V peekItemValue(){
            V result = null;
            if (head != null){
                result = head.value;
            }
            return result;
        }
    }

}
