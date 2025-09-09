package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 快速排序 2.0
 *
 * < x, = x 和 >x
 *
 * 固定拿最右侧做区分值， 考虑最差情况， 数组本来就是单调递增或递减的， 时间复杂度 O(N^2)
 */
public class Code04_QuickSort {

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
        // 返回的是小于区的边界 和 大于区的边界
        int[] partitioned = partition(nums, start, end);
        process(nums, start, partitioned[0]);
        process(nums, partitioned[1], end);
    }

    /**
     * partition 2.0
     * 在数组 start - end 范围上， 拿最后一个数 nums[end]作为比较，<的放在左边， =的放在中间，>的放在右边
     */
    public static int[] partition(int[] nums, int start, int end) {
        if (start > end) {
            return new int[]{-1, -1};
        }
        if (start == end) {
            return new int[]{start, end};
        }
        int lessIndex = start-1;
        int moreIndex = end;

        int curIndex = start;
        while (curIndex < moreIndex) {
            if (nums[curIndex] < nums[end]) {
                ArrayUtil.swap(nums, curIndex++, ++lessIndex);
//                ArrayUtil.swap(nums, curIndex, lessIndex+1);
//                lessIndex++;
//                curIndex++;
            } else if (nums[curIndex] == nums[end]) {
                curIndex++;
            }else {
                ArrayUtil.swap(nums, curIndex, --moreIndex);
//                ArrayUtil.swap(nums, curIndex, moreIndex-1);
//                moreIndex--;
            }
        }
        // 循环出来就是 curIndex == moreIndex
        ArrayUtil.swap(nums, moreIndex, end);
        return new int[]{lessIndex, moreIndex+1};
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000);
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
