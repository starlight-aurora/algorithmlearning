package com.roy.algorithmlearning.f01_beginner.class01;

import com.roy.algorithmlearning.util.ArrayUtil;

public class Sort {

    public static void main(String[] args) {
        int[] array = {2, 4, 6, 7, 5, 4, 8, 7, 5, 2, 1, 0, 8, 5, 4, 3};
        ArrayUtil.printArray(array);
//        selectSort(array);
//        bubbleSort(array);
        insertSort(array);
        ArrayUtil.printArray(array);
    }

    //选择排序， 选择一个最小的值放在i位置
    public static void selectSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            //这个用来确定开始位置
            int minValueIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                //这个用来确定比较到哪个位置
                if (array[j] < array[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            ArrayUtil.swap(array, i, minValueIndex);
        }
    }

    //冒泡排序， 把最大的一个个比较上去放在未排序最大的位置
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            //用来表示未排序最大的位置
            for (int j = 0; j < i; j++) {
                //从0 到i 把大数一个个比较冒上去
                if (array[j] > array[j + 1]) {
                    ArrayUtil.swap(array, j, j + 1);
                }
            }
        }
    }

    //插入排序， 和打牌时抓牌一样， 新拿到一张牌， 从最大一张张看过去， 这张牌比较小的话就再往前比较， 大的话就放在这里, 停止循环
    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            //这个用来确定最开始第几个是新来的数
            for (int pre = i - 1; pre >= 0 && array[pre] > array[pre + 1]; pre--) {
                //这个用来表示排好的里面需要比较的数的位置
                //pre+1 和 pre， 也就是前面一个位置比较， 如果前面比较大， 就交换一下， 在看前面是不是还大
                ArrayUtil.swap(array, pre, pre + 1);
            }
        }
    }

}