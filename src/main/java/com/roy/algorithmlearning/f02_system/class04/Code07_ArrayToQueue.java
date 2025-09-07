package com.roy.algorithmlearning.f02_system.class04;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数组实现队列（循环数组）
 */
public class Code07_ArrayToQueue {

    public static class MyQueue{
        private final int[] arr;
        int capacity;

        public MyQueue(int capacity){
            this.capacity = capacity;
            arr = new int[capacity];
        }

        int getIndex = 0;    // 从头上拿
        int addIndex = 0;    // 从尾巴加

        int size = 0;

        public void add(int num){
            if (size == arr.length){
                throw  new RuntimeException("队列已经满了！");
            }
            arr[addIndex] = num;
            addIndex = nextIndex(addIndex);
            size++;
        }

        public int poll(){
            if (size == 0){
                throw  new RuntimeException("队列已经空了！");
            }
            size--;
            int ans = arr[getIndex];
            getIndex = nextIndex(getIndex);
            return ans;
        }

        public int nextIndex(int index){
            if (index == arr.length -1) {
                return 0;
            } else {
                return index + 1;
            }
        }

        public boolean isEmpty(){
            return size == 0;
        }

    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(10);
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        myQueue.add(7);
        myQueue.add(8);
        myQueue.add(9);
        myQueue.add(10);

        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(3);
        arrayBlockingQueue.add(4);
        arrayBlockingQueue.add(5);
        arrayBlockingQueue.add(6);
        arrayBlockingQueue.add(7);
        arrayBlockingQueue.add(8);
        arrayBlockingQueue.add(9);
        arrayBlockingQueue.add(10);

        while (!myQueue.isEmpty()){
            int num1 = myQueue.poll();
            int num2 = arrayBlockingQueue.poll();

            if (num1 != num2){
                System.out.println("出错啦！");
            }
        }

        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        myQueue.add(7);
        myQueue.add(8);

        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(3);
        arrayBlockingQueue.add(4);
        arrayBlockingQueue.add(5);
        arrayBlockingQueue.add(6);
        arrayBlockingQueue.add(7);
        arrayBlockingQueue.add(8);

        int num3 = myQueue.poll();
        int num4 = arrayBlockingQueue.poll();
        if (num3 != num4){
            System.out.println("出错啦！");
        }

        myQueue.add(9);
        myQueue.add(10);
        myQueue.add(11);

        arrayBlockingQueue.add(9);
        arrayBlockingQueue.add(10);
        arrayBlockingQueue.add(11);

        while (!myQueue.isEmpty()){
            int num1 = myQueue.poll();
            int num2 = arrayBlockingQueue.poll();

            if (num1 != num2){
                System.out.println("出错啦！");
            }
        }

        System.out.println("测试结束！");
    }




}
