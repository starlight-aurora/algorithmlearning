package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.f05_bean.DoubleNode;

import java.util.LinkedList;

/**
 * 双向链表实现队列
 */
public class Code04_NodeToQueue {

    public static class MyQueue<T> {

        DoubleNode<T> head = null;
        DoubleNode<T> tail = null;

        public MyQueue() {}

        public void addFromHead(T value) {
            DoubleNode<T> newNode = new DoubleNode<T>(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.last = newNode;
                head = newNode;
            }
        }

        public void addFromTail(T value) {
            DoubleNode<T> newNode = new DoubleNode<>(value);
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public T popFromHead() {
            if (head != null) {
                DoubleNode<T> temp = head;
                head = head.next;
                if (head == null) {
                    tail = null;
                }
                return temp.value;
            } else  {
                return null;
            }
        }

        public T popFromTail() {
            if (tail != null) {
                DoubleNode<T> temp = tail;
                DoubleNode<T> last = tail.last;
                if (last == null) {
                    head = null;
                }else {
                    last.next = null;
                }
                tail = last;
                return temp.value;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return head == null;
        }
    }


    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        myQueue.addFromHead(1);
        myQueue.addFromHead(2);
        myQueue.addFromHead(3);

        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);

        int num1, num2;
        while (!myQueue.isEmpty()) {
            num1 = myQueue.popFromHead();
            num2 = linkedList.pollFirst();
            if (num1 != num2) {
                System.out.println("出错啦！");
            }
        }

        myQueue.addFromTail(4);
        myQueue.addFromTail(5);
        myQueue.addFromTail(6);
        myQueue.addFromTail(7);

        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(7);

        while (!myQueue.isEmpty()) {
            num1 = myQueue.popFromHead();
            num2 = linkedList.pollFirst();
            if (num1 != num2) {
                System.out.println("出错啦！");
            }
        }

        myQueue.addFromHead(8);
        myQueue.addFromTail(9);

        linkedList.addFirst(8);
        linkedList.addLast(9);

        while (!myQueue.isEmpty()) {
            num1 = myQueue.popFromHead();
            num2 = linkedList.pollFirst();
            if (num1 != num2) {
                System.out.println("出错啦！");
            }
        }

    }

}
