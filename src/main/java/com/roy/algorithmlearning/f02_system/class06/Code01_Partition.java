package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.util.ArrayUtil;

/**
 * 荷兰国旗问题 1.0
 *
 * 给定一个数组， 和一个数字， 将小于等于这个数的部分放在左侧， 大于的放右侧
 */
public class Code01_Partition {

    public static void partition(int[] nums, int num) {
        int lessIndex = -1;
        int moreIndex = nums.length;

        int curIndex = 0;
        while (curIndex < moreIndex) {
            if (nums[curIndex] <= num) {
                lessIndex++;
                curIndex++;
            } else {
                ArrayUtil.swap(nums, curIndex, moreIndex-1);
                moreIndex--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayUtil.generateArray(10, 1000, true);
        ArrayUtil.printArray(array);
//        int num = (int)(Math.random() * 1000) - (int)(Math.random() * 1000);
        int i = (int) (Math.random() * array.length-1) + 1;
        int num = array[i];
        System.out.println("比较的数为： " + num);
        partition(array, num);
        ArrayUtil.printArray(array);
    }


}
