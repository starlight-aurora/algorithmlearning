package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * https://leetcode.cn/problems/count-of-range-sum/
 */
public class Code07_CountRangeSum {

    /**
     * 思路解析：
     * 1. 将数组转化成 前缀和 数组 sums
     * 2. [x,y] 区间和，转化成 sums[y] - sums[x]
     * 3. 将 [x,y] 区间和 在 [lower, upper] 之间, 转化成 某个 [x - (y-1)] 的 区间和 在 [(sum[y] - upper), (sums[y] - lower)] 之间
     * 4. 归并排序
     * 5. merge方法， 变量不回退， 时间复杂度 O(N)
     *
     * 公式： sums[y] - sums[x] 在 [lower, upper] 区间， 则 sums[x] 在 [(sums[y] - upper), (sums[y] - lower)] 区间
     * 原来的问题我们就转换成了： 在确定了第二个数的情况下， 我们能不能找到一个前缀和在新区间内
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return process(sums, 0, sums.length - 1, lower, upper);
    }

    public static int process(long[] sums, int start, int end, int lower, int upper) {
        if (start == end) {
            if (lower <= sums[start] && sums[start] <= upper) {
                // 这里代表0-start（或end）这个区间和就在[lower, upper]区间内
                return 1;
            } else {
                return 0;
            }
        }
        int mid = start + (end - start)/2;
        int left = process(sums, start, mid, lower, upper);
        int right = process(sums, mid + 1, end, lower, upper);
        int merged = merge(sums, start, mid, end, lower, upper);
        return left + right + merged;
    }

    /**
     * 假设原数组: arr = [1, 3, 4, 2, 5, 6, 7, 2, 3]， 范围是： [2, 6]
     * 则： sums = [1, 4, 8, 10, 15, 21, 28, 30, 33] ps: arr数组都是非负数， sums天然有序
     * 当 递归在最底层的时候， 即 左右分别为1个数， 即 左：1（0-0） 右：4（0-1）
     * 减去最后的数， 新范围是： [-2, 2] -> [4-6, 4-2], 可以看出 sums[0] 满足条件，意思是 [1, 2)(就是x,y 都是1时满足)
     *
     * 再做倒数第二层递归时， 左：[1,4] 右：[8, 10]
     * 减去右边的一个数，第一次新范围是：[2, 6], 可以看出 sums[1] 满足条件， 意思是 sums[2] - sums[1], x,y 都是2时满足
     *                第二次新范围是：[4, 8], 可以看出 sums[1] 满足条件， 意思是 sums[3] - sums[1], 即x=2, y=3 时满足
     *
     * 倒数第三层递归时， 左：[1, 4, 8, 10] 右：[15, 21, 28, 30]
     * 减去右边的一个数，第一次新范围是：[9, 13], 可以看出 sums[3] 满足条件， 意思是 sums[4] - sums[3], x, y 都是4时满足条件
     *                第二次新范围是：[15, 19], 没有满足条件的， 后面的就更不可能满足条件，不需要往下走
     *
     * 我们可以发现， 在某一层的某一次递归时，当前面满足， 因为我们的每个序列是递增的，我们的新的满足的窗口一定是一直往后移的，避免了回退的情况
     */
    public static int merge(long[] sums, int start, int mid, int end, int lower, int upper) {
        int ans = 0;
        // 这两个代表找到的数的左右下标， 可以通过这两个数字相减算出满足条件的个数
        int findL = start;
        int findR = start;
        // 对于右组的数来说， 想知道左组里面有哪些值在要的新范围内
        for (int i = mid+1; i <= end; i++) {
            long newLower = sums[i] - upper;
            long newUpper = sums[i] - lower;
            while (findL <= mid && sums[findL] < newLower) {
                // 一直找到 sums[findL] >= newLower, 即满足条件的第一个数
                findL++;
            }
            while (findR <= mid && sums[findR] <= newUpper) {
                // 一直找到 sums[findR] > newUpper, 即不满足条件的第一个数
                findR++;
            }
            ans += findR - findL;
        }

        long[] help = new long[end-start+1];
        int helpIndex = 0;

        int leftIndex = start;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= end) {
            help[helpIndex++] = sums[leftIndex] <= sums[rightIndex] ? sums[leftIndex++] : sums[rightIndex++];
        }
        while (leftIndex <= mid) {
            help[helpIndex++] = sums[leftIndex++];
        }
        while (rightIndex <= end) {
            help[helpIndex++] = sums[rightIndex++];
        }
        System.arraycopy(help, 0, sums, start, help.length);
        return ans;
    }

    /**
     * 暴力解
     *
     * 普通暴力解： 时间复杂度 O(N^3)
     */
    public static int countRangeSum2(int[] nums, int lower, int upper) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= lower && sum <= upper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 暴力解
     *
     * 利用前缀和数组: 时间复杂度 O(N^2)
     */
    public static int countRangeSum3(int[] nums, int lower, int upper) {
        int ans = 0;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            // 这里是 0 - i 的前缀和就满足条件的情况
            if (sums[i] >= lower && sums[i] <= upper) {
                ans++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int sum = sums[j] -  sums[i];
                if (sum >= lower && sum <= upper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 暴力解
     * 在确定了第二个数的情况下， 我们能不能找到一个 之前的 前缀和 在新区间内
     * 利用前缀和数组: 时间复杂度 O(N^2)
     */
    public static int countRangeSum4(int[] nums, int lower, int upper) {
        int ans = 0;
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        for (int i = 0; i < sums.length; i++) {
            long num =  sums[i];
            if (num >= lower && num <= upper) {
                ans++;
            }
            long newLower = num - upper;
            long newUpper = num - lower;
            for (int j =0; j < i; j ++) {
                if (newLower <= sums[j] && sums[j] <= newUpper) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(100, 1000, true);
            if (arr.length == 0) {
                continue;
            }
            int [] copiedArr = Arrays.copyOf(arr, arr.length);
            int num1 = (int) (Math.random() * 1000) + 1 - (int) (Math.random() * 1000) + 1;
            int num2 = (int) (Math.random() * 1000) + 1 - (int) (Math.random() * 1000) + 1;
            int lower = Math.min(num1, num2);
            int upper = Math.max(num1, num2);

            int count1 = countRangeSum(arr, lower, upper);
            int count2 = countRangeSum2(arr, lower, upper);
            int count3 = countRangeSum3(arr, lower, upper);
            int count4 = countRangeSum4(arr, lower, upper);

            if(count2!=count3){
                System.out.println("出错啦！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(copiedArr);
                System.out.println("范围是：" + lower + " - " + upper);
                System.out.println("count2 = " + count2 + ", count3 = " + count3);
                break;
            }

            if(count3!=count4){
                System.out.println("出错啦！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(copiedArr);
                System.out.println("范围是：" + lower + " - " + upper);
                System.out.println("count3 = " + count3 + ", count4 = " + count4);
                break;
            }

            if(count1!=count2){
                System.out.println("出错啦！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(copiedArr);
                System.out.println("范围是：" + lower + " - " + upper);
                System.out.println("count1 = " + count1 + ", count2 = " + count2);
                break;
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
