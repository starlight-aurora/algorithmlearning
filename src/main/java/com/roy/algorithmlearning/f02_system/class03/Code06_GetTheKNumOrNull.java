package com.roy.algorithmlearning.f02_system.class03;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 一个数组中有一种数出现小于M次，其他数都出现了M次，
 * 寻找到出现了K次的数
 * M > 1,  K < M
 * 找到，返回出现了K次的数，如果没有数出现了K次， 返回-1
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code06_GetTheKNumOrNull {

    public static int getTheKNum(int[] arr, int m, int k) {

        int[] bytes = new int[32];
        for (int value : arr) {
            for (int j = 0; j < 32; j++) {
                if (((value >> j) & 1) != 0) {
                    bytes[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bytes[i] % m == 0) {
                continue;
            }
            if (bytes[i] % m == k) {
                ans = ans | (1 << i);
            } else {
                return -1;
            }
        }
        // 这里是因为如果出现了0， 不知道是结果中出现的， 还是没找到对应的数
        // 所以这里加了检验的代码
        if (ans == 0) {
            int count = 0;
            for (int j : arr) {
                if (j == 0) {
                    count++;
                }
            }
            if (k == count) {
                return 0;
            } else {
                return -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Params params = generateTheArray(100);
            int[] array = params.arr;

            int k = Math.random() < 0.5 ? params.k : (int) (Math.random() * (params.m - 1)) + 1;  // 找的K次是随机的

            int num = getTheKNum(array, params.m, k);

            boolean errorFlag = false;
            if (num == -1) {
                if (k == params.k) {
                    errorFlag = true;
                }
            } else {
                if (num != params.kNum) {
                    errorFlag = true;
                }
            }
            if (errorFlag) {
                System.out.println("出错了！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(array);
                HashMap<Integer, Integer> map = new HashMap<>();
                for (int value : array) {
                    if (map.containsKey(value)) {
                        map.put(value, map.get(value) + 1);
                    } else {
                        map.put(value, 1);
                    }
                }
                System.out.println("k= " + params.k + ", m= " + params.m );
                System.out.println("数组中： " );
                for (Integer key : map.keySet()) {
                    System.out.println("数字: " + key + ", 出现了 " + map.get(key) + "次！");
                }
                System.out.println("我的结果是： " );
                System.out.println("数是： " + num + ", 出现了 " + k + "次！" );
                System.out.println("----------------------------------------------------------------");
            }

        }
        System.out.println("花费时间（ms）： " + (System.currentTimeMillis() - mills));
    }

    public static Params generateTheArray(int range) {
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        set.add(-1);
        int num;
        do {
            num = (int) (Math.random() * range) + 1 - (int) (Math.random() * range) + 1;
        } while (set.contains(num));
        set.add(num);
        int mTimes = (int) (Math.random() * 10) + 2;
        int kTimes = (int) (Math.random() * (mTimes - 1)) + 1;             // 这样写kTimes出现的范围就是  0 - mTimes-1
        for(int i=0;i<kTimes;i++){
            list.add(num);
        }

        int numKind = (int) (Math.random() * 10) + 1;
        for (int j = 0; j < numKind; j++) {
            int otherNum;
            do {
                otherNum = (int) (Math.random() * range) + 1 - (int) (Math.random() * range) + 1;
            } while (set.contains(otherNum));
            set.add(otherNum);
            for (int i = 0; i < mTimes; i++) {
                list.add(otherNum);
            }
        }
        int[] array = list.stream().mapToInt(i -> i).toArray();
        // 这里是为了打乱这个数组
        for (int i = 0; i < array.length; i++) {
            int j = (int) (Math.random() * array.length);
            ArrayUtil.swap(array, i, j);
        }
        return new Params(kTimes, num, mTimes, array);
    }

    public static class Params {
        int k;
        int kNum;

        int m;

        int[] arr;

        public Params(int k, int kNum, int m, int[] arr) {
            this.k = k;
            this.kNum = kNum;
            this.m = m;
            this.arr = arr;
        }
    }
}
