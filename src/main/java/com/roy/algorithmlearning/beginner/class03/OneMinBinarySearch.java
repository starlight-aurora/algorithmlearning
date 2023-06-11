package com.roy.algorithmlearning.beginner.class03;

import com.roy.algorithmlearning.util.ArrayUtil;

public class OneMinBinarySearch {

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 100;
        int testTimes = 10000;
        System.out.println("测试开始！！！");
        for (int i = 0; i < testTimes; i++) {
            int[] array = generateArray(maxLength, maxValue);
//            ArrayUtil.printArray(array);
            int oneMinIndex = findOneMinIndex(array);
            if (!checkMinIndex(array, oneMinIndex)){
                System.out.println("出错啦！！！");
                ArrayUtil.printArray(array);
                System.out.println(oneMinIndex);
            }
        }
        System.out.println("测试结束！！！");
    }


    //无序数组，相邻的数字不相等，找到一个局部最小值的位置
    public static int findOneMinIndex(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int len = array.length;
        if (len == 1) {
            return 0;
        }
        if (array[len - 2] > array[len - 1]) {
            return len - 1;
        }
        if (array[0] < array[1]) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        while (left < right -1) { //如果不这么写，在 开头第二个数是最小的数 或者 倒数第二个数是最小的数 时，当middle定位到了开头或者末尾， middle-1 或者 middle+1 会数组越界
            int middle = (left + right) / 2;
            if (array[middle] < array[middle - 1] && array[middle] < array[middle + 1]) {
                return middle;
            } else {
                // 1. 左 < middle  右 < middle  ^
                // 2. 左 < middle  右 > middle  /
                // 3. 左 > middle  右 > middle  \
                if (array[middle - 1] < array[middle]) {   // 1 和 2
                    right = middle - 1;
                } else {   // 3
                    left = middle + 1;
                }
            }
        }
        // left >= right -1, 循环里面没找到，但是这个数组肯定是有局部最小值的（因为前面排除了首尾/...\, 那么数组应该是 \..../）
        // 那么现在哪个小哪个就应该是一个局部最小值
        return array[left] < array[right] ? left : right;
    }

    //生成相邻两个数不相等的数组
    public static int[] generateArray(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength);
        int[] array = new int[len];
        if (len >0){
            array[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    array[i] = (int) (Math.random() * maxValue);
                } while (array[i] == array[i - 1]);
            }
        }
        return array;
    }

    //检查最小值位置
    public static boolean checkMinIndex(int[] array, int minIndex) {
        if (array.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean checkLeft = left >= 0 ? array[left] > array[minIndex] : true;
        boolean checkRight = right < array.length ? array[right] > array[minIndex] : true;
        return checkLeft && checkRight;
    }
}
