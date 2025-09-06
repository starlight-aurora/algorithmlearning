package com.roy.algorithmlearning.f02_system.class03;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个数组中其他数字都出现了偶数次， 有一个数字出现了奇数次， 找出这个数
 */
public class Code02_FindTheOdd {

    public static int findTheOdd(int[] arr){
        int ans = 0;
        for(int i=0;i<arr.length;i++){
            ans = ans ^ arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int[] array = generateTheArray(100);
            int theOdd = findTheOdd(array);
            int times = 0;
            for (int j = 0; j < array.length; j++) {
                if (theOdd == array[j]) {
                    times++;
                }
            }
            if (times % 2 == 0) {
                System.out.println("出错了！");
            }
        }
        System.out.println("花费时间（ms）： " + (System.currentTimeMillis() - mills));
    }

    public static int[] generateTheArray(int range){
        List<Integer> list = new ArrayList<>();
        int num = (int)(Math.random() * range) +1 - (int)(Math.random() * range) +1;
        int numTimes = (int)(Math.random() * 10);
        if(numTimes%2 == 0){
            numTimes++;
        }
        for(int i=0;i<numTimes;i++){
            list.add(num);
        }
        int numKind = (int)(Math.random() * 10) + 1;
        for(int j=0;j<numKind;j++){
            int evenNum = (int)(Math.random() * range) + 1 - (int)(Math.random() * range) +1;
            int evenNumTimes = (int)(Math.random() * 10);
            if(evenNumTimes%2 != 0){
                evenNumTimes++;
            }
            for (int i = 0; i < evenNumTimes; i++) {
                list.add(evenNum);
            }
        }
        int[] array = list.stream().mapToInt(i -> i).toArray();
        // 这里是为了打乱这个数组
        for (int i = 0; i < array.length; i++) {
            int j = (int)(Math.random() * array.length);
            ArrayUtil.swap(array, i, j);
        }
        return array;
    }

}
