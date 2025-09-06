package com.roy.algorithmlearning.f02_system.class03;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，
 * M > 1,  K < M
 * 找到，出现了K次的数，
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code05_GetTheKNum {

    public static int getTheKNum(int [] arr, int m, int k){

        int[] bytes =  new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                if(((arr[i] >> j) & 1) != 0){
                    bytes[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bytes[i] % m != 0) {
                ans = ans | (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Params params = generateTheArray(100);
            int[] array = params.arr;
            int num = getTheKNum(array, params.m, params.k);
            if (num != params.kNum) {
                System.out.println("出错了！");
            }
        }
        System.out.println("花费时间（ms）： " + (System.currentTimeMillis() - mills));
    }

    public static Params generateTheArray(int range){
        List<Integer> list = new ArrayList<>();
        int num = (int)(Math.random() * range) +1 - (int)(Math.random() * range) +1;
        int kTimes = (int)(Math.random() * 10) +1;

        for(int i=0;i<kTimes;i++){
            list.add(num);
        }
        int numKind = (int)(Math.random() * 10) + 1;
        int mTimes = (int)(Math.random() * 10) + kTimes;
        if (mTimes <= kTimes){
            mTimes = (int)(Math.random() * 10) + kTimes +1;
        }
        for(int j=0;j<numKind;j++){
            int otherNum = (int)(Math.random() * range) + 1 - (int)(Math.random() * range) +1;
            for (int i = 0; i < mTimes; i++) {
                list.add(otherNum);
            }
        }
        int[] array = list.stream().mapToInt(i -> i).toArray();
        // 这里是为了打乱这个数组
        for (int i = 0; i < array.length; i++) {
            int j = (int)(Math.random() * array.length);
            ArrayUtil.swap(array, i, j);
        }
        return new Params(kTimes, num, mTimes, array);
    }

    public static class Params{
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
