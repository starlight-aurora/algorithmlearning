package com.roy.algorithmlearning.util;

public class ArrayUtil {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        // 下面的代码不能是一个位置， 不然会把两个书都变成0
//        array[i] = array[i] ^  array[j];
//        array[j] = array[i] ^  array[j];
//        array[i] = array[i] ^  array[j];
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void printlnArray(int[] array) {
        for (int j : array) {
            System.out.println(j + " ");
        }
        System.out.println();
    }

    //限制最大长度和最大值生成一个数组
    public static int[] generateArray(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }
        return array;
    }

    //限制最大长度和 最大值的绝对值 生成一个数组, 值可能为负数
    public static int[] generateArray(int maxLength, int maxValue, boolean flag) {
        int len = (int) (Math.random() * maxLength);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            if (flag) {
                array[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } else  {
                array[i] = (int) (Math.random() * maxValue);
            }
        }
        return array;
    }

    //复制一个数组
    public static int[] copyArray(int[] array) {
        int[] copiedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copiedArray[i] = array[i];
        }
        return copiedArray;
    }

    public static boolean compareArray(int[] array1, int[] array2) {
        if (array1 == null && array2 == null) {
            return true;
        }
        if ((array1 == null && array2 != null) || (array1 != null && array2 == null)) {
            return false;
        }
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

}
