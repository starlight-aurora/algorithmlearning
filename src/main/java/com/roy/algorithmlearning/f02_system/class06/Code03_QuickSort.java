package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 快速排序 1.0
 *
 * <= x 和 >x
 */
public class Code03_QuickSort {

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        process(nums, 0, nums.length - 1);
    }

    public static void process(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        // 相当于 index 位置得数已经排好了， 现在来排两边
        int index = partition(nums, start, end);
        process(nums, start, index - 1);
        process(nums, index + 1, end);
    }

    /**
     * partition 1.0
     * 在数组 start - end 范围上， 拿最后一个数 nums[end]作为比较，<=的放在左边， >的放在左边，>的放在右边
     */
    public static int partition(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return start;
        }
        int lessIndex = start-1;
        int moreIndex = end;

        int curIndex = 0;
        while (curIndex < moreIndex) {
            if (nums[curIndex] <= nums[end]) {
                lessIndex++;
                curIndex++;
            } else {
                ArrayUtil.swap(nums, curIndex, moreIndex-1);
                moreIndex--;
            }
        }
        ArrayUtil.swap(nums, curIndex, end);
        return curIndex;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(10, 100);
            int[] copyArray = ArrayUtil.copyArray(arr);
            Arrays.sort(copyArray);
            quickSort(arr);
            if (!ArrayUtil.compareArray(arr, copyArray)){
                System.out.println("排序失败");
                System.out.println("数组1是： " );
                ArrayUtil.printArray(arr);
                System.out.println("数组2是： " );
                ArrayUtil.printArray(copyArray);
                break;
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }


}
