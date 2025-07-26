package com.roy.algorithmlearning.beginner.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

// 获取array 的从L到R的和

public class RangeSum {

    public static void main(String[] args) {
        int[] array = {2, 4, 6, 7, 5, 4, 8, 7, 5, 2, 1, 0, 8, 5, 4, 3};
        ArrayUtil.printArray(array);
        int[] preSum = getPreSum(array);
        ArrayUtil.printArray(preSum);

        // 获取array 的从 前缀 5到15 的数的和
        int sum = preSum[15] - preSum[5 - 1];
        System.out.println(sum);
    }

    // 获得前缀和数组
    public static int[] getPreSum(int[] array) {
        //前缀和数组
        int[] preSum = new int[array.length];
        preSum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            preSum[i] = preSum[i - 1] + array[i];
        }
        return preSum;
    }
}
