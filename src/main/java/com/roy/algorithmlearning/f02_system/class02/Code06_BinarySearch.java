package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 二分查找
 */
public class Code06_BinarySearch {

    public static int binarySearch(int[] arr,int num){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
//            int mid = (left+right)/2; // 可能会有越界的风险， 大于int的最大值
            int mid = left +  ((right-left) >> 1);
            if (arr[mid] < num){
                left = mid+1;
            } else if(arr[mid] == num) {
                return mid;
            } else if(arr[mid] > num){
                right = mid-1;
            }
        }
        return -1;  // 没找到
    }

    public static int search(int[] arr,int num){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==num){
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
            int index1 = search(arr, num);
            int index2 = binarySearch(arr, num);
            if (index1 == index2) {
                System.out.println("成功!");
            }else {
                System.out.println("失败!");
            }
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }


}
