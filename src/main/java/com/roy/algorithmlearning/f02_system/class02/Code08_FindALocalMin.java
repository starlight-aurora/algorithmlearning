package com.roy.algorithmlearning.f02_system.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 无序数组， 相邻两个数不相等， 返回一个局部最小值
 */
public class Code08_FindALocalMin {

    public static int findMinIndex(int[] arr){
        if (arr == null || arr.length < 2)
            return -1;
        if (arr[0] < arr[1])
            return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;
        }
        // 在这个后面的代码， 肯定存在arr[1] < arr[0], 所以最后如果循环没找到的话， 返回的是1
        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left < right){
            mid = left + (right - left) / 2;
            if (arr[mid-1] < arr[mid]){
                right = mid-1;
            } else if (arr[mid] > arr[mid+1]){
                left = mid+1;
            } else {
                // 走到这里， 说明应该是  arr[mid-1] > arr[mid] 并且 arr[mid] < arr[mid+1]， 直接返回当前的位置
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] == arr[j-1]){
                    arr[j] = arr[j-1] -1;
                }
            }
            int index1 = findMinIndex(arr);
            if(index1 == -1){
                if (arr != null && arr.length > 1){
                    System.out.println("失败！");
                    return;
                }else {
                    continue;
                }
            }
            if (index1 == 0 && arr[1]> arr[0]){
                continue;
            }
            if (index1 == arr.length-1 && arr[arr.length-1] < arr[arr.length-2]){
                continue;
            }
            if (arr[index1] < arr[index1-1] && arr[index1] < arr[index1+1]) {
                continue;
            }
            System.out.println("失败!");
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }

}
