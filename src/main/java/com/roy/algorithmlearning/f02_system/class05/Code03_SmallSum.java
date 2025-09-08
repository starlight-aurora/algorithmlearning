package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 小和问题：
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。
 *
 * 求数组小和。
 */
public class Code03_SmallSum {

    /**
     * 时间复杂度 N*logN 思路：
     * 利用归并排序， 在归并排序过程中， 利用已排序的部分，
     * 直接可以看到 当前数 在 当前循环中 有多少个数大于自己
     * 即：
     * merge 的时候， 拷贝的数是右侧的， 当前步骤不产生小和，
     * 拷贝的数是左边的， 产生右侧剩余数个数的当前数的小和
     *
     * [1,3,4,5] [2,3,5,6] merge的时候，合并成 [1,2,3,3,4,5,5,6]
     * 拷贝1的时候， 产生 1 * 4 的小和
     * 拷贝2的时候， 不产生小和
     * 指针都指向3 的时候， 右侧先拷贝往下走， 然后拷贝左侧3时， 可知产生 3 * 2的小和
     * 拷贝4的时候， 产生 4 * 2 的小和
     * 指针都指向5 的时候， 右侧先拷贝往下走， 然后拷贝左侧5时， 可知产生 5 * 1的小和
     * 拷贝6的时候， 不产生小和
     */
    public static int getSmallSum(int[] arr){
        if(arr==null||arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr, int start, int end){
        if (start == end){
            return 0;
        }
        int mid = start + ((end - start) >> 1);
        int sum1 = process(arr, start, mid);
        int sum2 = process(arr, mid+1, end);
        int sum3 = merge(arr, start, mid, end);
        return sum1 + sum2 + sum3;
    }

    private static int merge(int[] arr, int start, int mid, int end){
        int sum = 0;
        int[] help = new int[end - start + 1];
        int indexHelp = 0;
        int index1 = start;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= end) {
            if (arr[index1] < arr[index2]) {
                sum += arr[index1] * (end - index2 + 1);
            }
            help[indexHelp++] = arr[index1] < arr[index2] ? arr[index1++] : arr[index2++];
        }
        while (index1 <= mid) {
            help[indexHelp++] = arr[index1++];
        }
        while (index2 <= end) {
            help[indexHelp++] = arr[index2++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
        return sum;
    }

    /**
     * 时间复杂度 N*N 的方法， 不得分。。。
     *
     */
    public static int getSmallSum1(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for(int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                if (arr[j] < arr[i]){
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            int [] copiedArr = Arrays.copyOf(arr, arr.length);
            int sum1 = getSmallSum(arr);
            int sum2 = getSmallSum1(copiedArr);
            if(sum1!=sum2){
                System.out.println("出错啦！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(copiedArr);
                System.out.println("sum1 = " + sum1 + ", sum2 = " + sum2);
                break;
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
