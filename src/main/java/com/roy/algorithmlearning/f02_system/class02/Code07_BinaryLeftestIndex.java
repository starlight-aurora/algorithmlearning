package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 有序数组， 二分查找大于等于某个数字的最左位置
 */
public class Code07_BinaryLeftestIndex {

    public static int binarySearchLeftestIndex(int[] arr,int num){
        int left=0;
        int right=arr.length-1;
        int leftIndex=-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]>=num){
                right=mid-1;
                leftIndex =mid;
            } else {
                left=mid+1;
            }
        }
        return leftIndex;
    }

    public static int getLeftestIndex(int[] arr,int num){
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=num){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            Arrays.sort(arr);
            int num = (int) (Math.random() * 1000);
            int index1 = getLeftestIndex(arr, num);
            int index2 = binarySearchLeftestIndex(arr, num);
            if (index1 != index2) {
                System.out.println("失败!");
            }
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }
}
