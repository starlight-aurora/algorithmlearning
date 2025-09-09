package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序 3.0
 *
 * < x, = x 和 >x  加上随机最右侧
 * 非递归版本： 栈实现
 */
public class Code06_QuickSort {

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        // 返回的是小于区的边界 和 大于区的边界
        int[] partitioned = partition(nums, 0, nums.length-1);
        Stack<Operation> stack = new Stack<>();
        stack.push(new Operation(0, partitioned[0]));
        stack.push(new Operation(partitioned[1], nums.length-1));
        while (!stack.isEmpty()) {
            Operation operation = stack.pop();
            int[] partitioned1 = partition(nums, operation.start, operation.end);
            if (partitioned1[0] != partitioned1[1]) {
                stack.push(new Operation(operation.start, partitioned1[0]));
                stack.push(new Operation(partitioned1[1], operation.end));
            }
        }
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
        // 随机交换一个未排序数到对尾
        ArrayUtil.swap(nums,start+ (int)(Math.random()* (end -start +1)), end);
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

    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Operation {
        public int start;
        public int end;

        public Operation(int start, int end) {
            this.start = start;
            this.end = end;
        }
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
