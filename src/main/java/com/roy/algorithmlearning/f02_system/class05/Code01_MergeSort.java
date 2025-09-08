package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 递归实现 和 非递归实现
 */
public class Code01_MergeSort {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length <2 ){
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        process(arr, start, mid);
        process(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] help = new int[end - start + 1];
        int indexHelp = 0;
        int index1 = start;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= end) {
            help[indexHelp++] = arr[index1] <= arr[index2] ? arr[index1++] : arr[index2++];
        }
        while (index1 <= mid) {
            help[indexHelp++] = arr[index1++];
        }
        while (index2 <= end) {
            help[indexHelp++] = arr[index2++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            mergeSort(arr);
            if (!ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序失败");
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
