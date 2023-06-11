package com.roy.algorithmlearning.beginner.class02;

import com.roy.algorithmlearning.beginner.class01.Sort;
import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

public class SortFunctionTest {

    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 1000;
        int testTimes = 10000;

        for(int i = 0; i < testTimes; i ++){
            int [] testArray = ArrayUtil.generateArray(maxLength, maxValue);
            int [] testArray1 = ArrayUtil.copyArray(testArray);
            int [] testArray2 = ArrayUtil.copyArray(testArray);
            int [] testArray3 = ArrayUtil.copyArray(testArray);

            Arrays.sort(testArray);

            Sort.selectSort(testArray1);
            Sort.bubbleSort(testArray2);
            Sort.insertSort(testArray3);

            if (!ArrayUtil.compareArray(testArray1, testArray)){
                System.out.println("系统排好的数组为： ");
                ArrayUtil.printArray(testArray);
                System.out.println("选择排序错了！！！");
                ArrayUtil.printArray(testArray1);
                break;
            }

            if (!ArrayUtil.compareArray(testArray2, testArray)){
                System.out.println("系统排好的数组为： ");
                ArrayUtil.printArray(testArray);
                System.out.println("冒泡排序错了！！！");
                ArrayUtil.printArray(testArray2);
                break;
            }

            if (!ArrayUtil.compareArray(testArray3, testArray)){
                System.out.println("系统排好的数组为： ");
                ArrayUtil.printArray(testArray);
                System.out.println("插入排序错了！！！");
                ArrayUtil.printArray(testArray3);
                break;
            }
        }
        System.out.println("检验完成啦！！！");

        //检查比较方法
//        int[] a1 = new int[10];
//        int[] a2 = null;
//        boolean b = ArrayUtil.compareArray(a1, a2);
//        System.out.println("b = " + b);
    }


}
