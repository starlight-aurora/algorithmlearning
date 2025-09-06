package com.roy.algorithmlearning.f02_system.class03;

/**
 * 不借助额外的变量交换两个变量的值
 */
public class Code01_Swap {

    // arr[i] 与 arr[j] 不能相同， 相同的话会使两个值都变成0
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 分析：
     * a ^ 0 = a
     * a ^ a = 0
     *
     * a = a ^ b
     * b = (a ^ b) ^ b = a
     * a = (a ^ b) ^ a = b
     */



}
