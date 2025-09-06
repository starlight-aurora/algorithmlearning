package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 归并排序
 * 需要整个数组有序， 先把前一半有序（递归做）， 在让后一半有序（递归做）
 * 然后合并
 */
public class Code05_MergeSort {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        sortRange(arr, 0, arr.length - 1);
    }

    public static void sortRange(int[] arr,int low,int high){
        if(low >= high){
            return;
        }
        int mid = (low+high)/2;
        sortRange(arr,low,mid);
        sortRange(arr,mid+1,high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i = 0;
        int index1 = low;
        int index2 = mid+1;

        while (index1 <= mid && index2 <= high) {
            if (arr[index1] <= arr[index2]) {
                temp[i] = arr[index1];
                index1++;
                i++;
            }else  {
                temp[i] = arr[index2];
                index2++;
                i++;
            }
        }
        // 下面两个循环只有一个会出现
        while (index1 <= mid) {
            temp[i] = arr[index1];
            index1++;
            i++;
        }
        while (index2 <= high) {
            temp[i] = arr[index2];
            index2++;
            i++;
        }
        for (int j = 0; j< temp.length; j++) {
            arr[low+j] =  temp[j];
        }
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            mergeSort(arr);
            if (ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序成功");
            }else {
                System.out.println("排序失败");
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
