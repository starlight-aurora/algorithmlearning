package com.roy.algorithmlearning.f01_beginner.class04;

import java.util.Deque;
import java.util.LinkedList;

public class TwoWaysQueueListToDeque {

    public static void main(String[] args) {
        System.out.println("TwoWaysQueue测试开始！！！");
        int testTimes = 10000;
        int maxValue = 1000;
        TwoWaysQueue<Integer> twoWaysQueue = new TwoWaysQueue<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < testTimes; i++) {
            if (twoWaysQueue.isEmpty() != deque.isEmpty()) {
                System.out.println("出错啦！");
            }
            if (twoWaysQueue.size != deque.size()) {
                System.out.println("出错啦！！");
            }
            double decideValue = Math.random();
            if (decideValue < 0.3) {
                int value = (int) (Math.random() * maxValue);
                double secondDecideValue = Math.random();
                if (secondDecideValue < 0.5){
                    twoWaysQueue.headAddItem(value);
                    deque.addFirst(value);
                }else {
                    twoWaysQueue.tailAddItem(value);
                    deque.addLast(value);
                }
            } else if (decideValue < 0.6) {
                if (!twoWaysQueue.isEmpty()){
                    double secondDecideValue = Math.random();
                    int num1 = 0;
                    int num2 = 0;
                    if (secondDecideValue < 0.5){
                        num1 = twoWaysQueue.headPopItem();
                        num2 = deque.pollFirst();
                    }else {
                        num1 = twoWaysQueue.tailPopItem();
                        num2 = deque.pollLast();
                    }
                    if (num1 != num2){
                        System.out.println("出错啦！！！num1 = " + num1 + ", num2 = " + num2);
                    }
                }
            }else {
                if (!twoWaysQueue.isEmpty()){
                    double secondDecideValue = Math.random();
                    int num1 = 0;
                    int num2 = 0;
                    if (secondDecideValue < 0.5){
                        num1 = twoWaysQueue.headPeekItemValue();
                        num2 = deque.peekFirst();
                    }else {
                        num1 = twoWaysQueue.tailPeekItemValue();
                        num2 = deque.peekLast();
                    }
                    if (num1 != num2){
                        System.out.println("出错啦！！！！num1 = " + num1 + ", num2 = " + num2);
                    }
                }
            }
        }
        System.out.println("TwoWaysQueue测试结束！！！");
    }

    public static class TwoWaysQueue<V> {

        TwoWaysNode<V> head;
        TwoWaysNode<V> tail;

        int size;

        public TwoWaysQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void headAddItem(V value) {
            TwoWaysNode<V> currentNode = new TwoWaysNode<>(value);
            if (head == null) {
                head = currentNode;
                tail = currentNode;
            } else {
                currentNode.next = head;
                head.last = currentNode;
                head = currentNode;
            }
            size++;
        }

        public void tailAddItem(V value) {
            TwoWaysNode<V> currentNode = new TwoWaysNode<>(value);
            if (tail == null) {
                head = currentNode;
                tail = currentNode;
            } else {
                tail.next = currentNode;
                currentNode.last = tail;
                tail = currentNode;
            }
            size ++;
        }

        public V headPopItem() {
            V result = null;
            if (head != null){
                result = head.value;
                head = head.next;
                if (head == null){
                    tail = null;
                }else {
                    head.last = null;
                }
                size --;
            }
            return result;
        }

        public V tailPopItem() {
            V result = null;
            if (tail != null){
                result = tail.value;
                tail = tail.last;
                if (tail == null){
                    head = null;
                }else {
                    tail.next = null;
                }
                size --;
            }
            return result;
        }

        public V headPeekItemValue() {
            V result = null;
            if (head != null){
                result = head.value;
            }
            return result;
        }

        public V tailPeekItemValue() {
            V result = null;
            if (tail != null){
                result = tail.value;
            }
            return result;
        }
    }

    public static class TwoWaysNode<V> {

        V value;

        TwoWaysNode<V> last = null;

        TwoWaysNode<V> next = null;

        public TwoWaysNode(V value) {
            this.value = value;
            this.last = null;
            this.next = null;
        }
    }
}
