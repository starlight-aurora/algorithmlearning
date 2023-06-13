package com.roy.algorithmlearning.beginner.class04;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListToQueueAndStack {

    public static void main(String[] args) {
        System.out.println("测试开始！！！");
        int testTimes = 10000;
        int maxValue = 100;
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
            if (decideValue < 0.5){
                int value = (int)(Math.random() * maxValue);
                myQueue.addItem(value);
                sysQueue.add(value);
            }else {
                if (!myQueue.isEmpty()){
                    int itemValue = myQueue.getItem();
                    int polledValue = sysQueue.poll();
                    if (itemValue != polledValue){
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
    }

}
