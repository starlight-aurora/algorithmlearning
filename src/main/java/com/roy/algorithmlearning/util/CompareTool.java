package com.roy.algorithmlearning.util;

import java.lang.reflect.Array;

public class CompareTool {


    //限制最大长度和最大值生成一个数组
    public static int[] generateArray(int maxLength, int maxValue) {
        int[] array = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            array[i] = (int) (Math.random() * maxValue);
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
        for (int i = 0; i < array1.length; i++){
            if (array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }


}
