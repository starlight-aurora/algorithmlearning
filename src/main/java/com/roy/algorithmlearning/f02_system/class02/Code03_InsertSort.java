package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Code03_InsertSort {

    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i< arr.length; i++){
            for (int j = i; j>0; j--){
                if (arr[j] < arr[j-1]){
                    ArrayUtil.swap(arr,j,j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] arr = ArrayUtil.generateArray(100, 1000);
        int[] copyArray = ArrayUtil.copyArray(arr);
        Arrays.sort(copyArray);
        insertSort(arr);
        if (ArrayUtil.compareArray(arr, copyArray)){
            System.out.println("排序成功");
        }else {
            System.out.println("排序失败");
        }

    }
}
