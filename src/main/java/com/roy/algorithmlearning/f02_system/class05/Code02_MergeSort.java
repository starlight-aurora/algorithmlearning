package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 非递归实现: 步长
 */
public class Code02_MergeSort {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length <2 ){
            return;
        }
        int stepWidth = 1;
        while (stepWidth < arr.length){
            int start = 0;
            int lStart = start;
            int lEnd = start+stepWidth-1;
            int rEnd = start+stepWidth-1 +1 +stepWidth-1;
            rEnd = Math.min(rEnd, arr.length-1);
            while (lEnd+1 < arr.length) {
                merge(arr,lStart,lEnd,rEnd);
                start = rEnd+1;

                lStart = start;
                lEnd = start+stepWidth-1;
                rEnd = start+stepWidth-1 +1 +stepWidth-1;
                rEnd = Math.min(rEnd, arr.length-1);
            }

            if (stepWidth > arr.length/2){
                break;
            }
            stepWidth *= 2;
        }
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
            if (ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序成功！");
            } else {
                System.out.println("排序失败！");
                break;
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
