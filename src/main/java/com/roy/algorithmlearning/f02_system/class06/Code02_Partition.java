package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.util.ArrayUtil;

/**
 * 荷兰国旗问题 2.0
 *
 * 给定一个数组， 和一个数字， 将小于这个数的部分放在左侧， 等于的放中间， 大于的放右侧
 */
public class Code02_Partition {

    public static void partition(int[] nums, int num) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int lessIndex = -1;
        int moreIndex = nums.length;

        int curIndex = 0;
        while (curIndex < moreIndex) {
            if (nums[curIndex] < num) {
                ArrayUtil.swap(nums, curIndex, lessIndex+1);
                lessIndex++;
                curIndex++;
            } else if(nums[curIndex] == num){
                curIndex++;
            }else {
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
