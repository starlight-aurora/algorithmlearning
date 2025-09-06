package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Code01_SelectSort {

    public static void selectSort(int[] arr) {

        if (arr == null || arr.length <2){
            return;
        }

        for(int i =0; i< arr.length; i++){
            int minIndex = i;
            for (int j=i+1; j< arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            ArrayUtil.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            selectSort(arr);
            if (ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序成功");
            }else {
                System.out.println("排序失败");
            }
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }

}
