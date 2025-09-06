package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Code04_QuickSort {

    public static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        sortRange(arr,0,arr.length-1);
    }

    public static void sortRange(int[] arr, int left, int right){
        if (left >= right){
            return;
        }
        int[] range = splitRange(arr, left,right);
        sortRange(arr, left, range[0]-1);
        sortRange(arr, range[1] + 1, right);
    }

    public static int [] splitRange(int[] arr, int start, int end){
        int leftIndex = start-1;
        int rightIndex = end;

        int temp = arr[end];
        int index = start;
        while (index < rightIndex){
            if(arr[index] < temp){
                ArrayUtil.swap(arr, index, leftIndex+1);
                leftIndex++;
                index++;
            } else if(arr[index] > temp){
                ArrayUtil.swap(arr, index, rightIndex-1);
                rightIndex--;
            } else {
                index++;
            }
        }
        ArrayUtil.swap(arr, index, end);
        return new int[] {leftIndex+1, index};
    }

    public static void splitArr(int[] arr){
        int left = -1;
        int right = arr.length-1;

        int num = arr[right];
        int i = 0;
        while(i < right){
            if (arr[i] < num){
                ArrayUtil.swap(arr, i, left+1);
                i++;
                left++;
            } else if(arr[i] > num){
                ArrayUtil.swap(arr, i, right-1);
                right--;
            } else {
                i++;
            }
        }
        ArrayUtil.swap(arr, i, arr.length-1);
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            quickSort(arr);
            if (ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序成功");
            }else {
                System.out.println("排序失败");
            }
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }

}
