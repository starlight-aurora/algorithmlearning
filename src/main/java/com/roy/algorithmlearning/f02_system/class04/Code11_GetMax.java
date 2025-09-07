package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 递归实现寻找数组最大值
 *
 * 计算递归的时间复杂度： Master公式
 *
 * 形如 T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数) 的递归函数，即每次分组都是一样的（每次分成两个一样的子任务）
 * 可以直接通过Master公式来确定时间复杂度
 * 如果 log(b,a) < d，复杂度为O(N^d)
 * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
 * 如果 log(b,a) == d，复杂度为O(N^d  * logN)
 *
 */
public class Code11_GetMax {

    public static int getMax(int[] arr){
        return getMaxAtRange(arr,0,arr.length-1);
    }

    public static int getMaxAtRange(int[] arr, int start, int end){
        if (start == end) {
            return arr[start];
        }
        int mid = start + ((end - start) >> 1);
        int max1 = getMaxAtRange(arr, start, mid);
        int max2 = getMaxAtRange(arr, mid + 1, end);
        return Math.max(max1, max2);
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
            if (arr.length == 0) {
                continue;
            }
            int max1 = getMax(arr);
            Arrays.sort(arr);
            int max2 = arr[arr.length-1];
            if(max1 != max2){
                System.out.println("出错啦！");
            }
        }
        System.out.println("花费时间（ms）" + (System.currentTimeMillis() - mills));
    }


}
