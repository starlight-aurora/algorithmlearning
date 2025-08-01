package com.roy.algorithmlearning.beginner.class03;

import com.roy.algorithmlearning.beginner.class01.Sort;
import com.roy.algorithmlearning.util.ArrayUtil;

//有序数组，找到一个 <=num 的最右序号

public class MaxRightBinarySearch {

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 100;
        int testTimes = 10000;
        int count = 0;
        System.out.println("测试开始！！！");
        for (int i = 0; i < testTimes; i++) {
            int[] array = ArrayUtil.generateArray(maxLength, maxValue);
            Sort.insertSort(array);
            int num = (int) (Math.random() * maxValue);
            int index = maxRightBinarySearch(array, num);
            int index1 = findLessThanNum(array, num);
            if (index1 != index) {
                System.out.println("index === " + index);
                System.out.println("index1 == " + index1);
                System.out.println("出错啦！！！");
                ArrayUtil.printArray(array);
                System.out.println(num);
                System.out.println(index);
            }
            if (index != -1) {
                if (array[index] > num) {
                    System.out.println("出错啦！！！");
                    ArrayUtil.printArray(array);
                    System.out.println(num);
                    System.out.println(index);
                }
                count++;
            }
        }
        System.out.println("找到概率： " + (double) count / (double) testTimes);
        System.out.println("测试结束！！！");
    }

    //有序数组，找到一个 <=num 的最右序号
    public static int maxRightBinarySearch(int[] array, int num) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        int index = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] <= num) {
                left = middle + 1;
                index = middle;
            } else {
                right = middle - 1;
            }
        }
        return index;
    }

    public static int findLessThanNum(int[] array, int num) {
        for (int i = array.length -1; i >=0 ; i--) {
            if (array[i] <= num) {
                return i;
            }
        }
        return -1;
    }
}
