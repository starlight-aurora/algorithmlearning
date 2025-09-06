package com.roy.algorithmlearning.f01_beginner.class03;

import com.roy.algorithmlearning.f01_beginner.class01.Sort;
import com.roy.algorithmlearning.util.ArrayUtil;

public class BinarySearch {

    public static void main(String[] args) {
        int maxLength = 20;
        int maxValue = 100;
        int testTimes = 10000;
        int count = 0;
        System.out.println("测试开始！！！");
        for (int i = 0; i<testTimes; i++){
            int[] array = ArrayUtil.generateArray(maxLength, maxValue);
            Sort.insertSort(array);
            int num = (int)(Math.random()* maxValue);
            int index = baseBinarySearch(array, num);
            int index1 = findNum(array,num);
            if (index1 != index){
                System.out.println("index === "+ index);
                System.out.println("index1 == "+ index1);
                if (index == -1){
                    if (array[index1] == num){
                        System.out.println("出错啦！！！");
                        ArrayUtil.printArray(array);
                        System.out.println(num);
                        System.out.println(index);
                    } else {
                        System.out.println("你猜！！！");
                    }
                }else {
                    System.out.println("num有重复！！！");
                    ArrayUtil.printArray(array);
                    System.out.println(num);
                    System.out.println(index);
                    if (array[index] != num){
                        System.out.println("出错啦！！！");
                        ArrayUtil.printArray(array);
                        System.out.println(num);
                        System.out.println(index);
                    }
                }
            }
            if (index != -1){
                if (array[index] != num){
                    System.out.println("出错啦！！！");
                    ArrayUtil.printArray(array);
                    System.out.println(num);
                    System.out.println(index);
                }
                count++;
            }
        }
        System.out.println("找到概率： " + (double)count / (double) testTimes);
        System.out.println("测试结束！！！");
    }

    //有序数组，找到一个num的序号
    public static int baseBinarySearch(int [] array, int num){
        if (array == null || array.length == 0){
            return -1;
        }
        int left = 0;
        int right = array.length -1;
        while (left <= right){
            int middle = (left + right) / 2;
            if (array[middle] == num){
                return middle;
            }else if (array[middle] < num){
                left = middle +1;
            }else {  // array[middle] > num
                right = middle -1;
            }
        }
        return -1;
    }

    //最古老的方法来找一个值
    public static int findNum(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }
}
