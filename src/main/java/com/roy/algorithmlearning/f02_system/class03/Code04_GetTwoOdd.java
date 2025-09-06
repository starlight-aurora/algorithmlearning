package com.roy.algorithmlearning.f02_system.class03;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 一个数组中， 有两个数出现了奇数次， 其他的数字均出现了偶数次， 请找出这两个数
 */
public class Code04_GetTwoOdd {

    public static int [] getTwoOdd(int [] arr){

        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num = num ^ arr[i];
        }
        // 假设 这两个数字是 a 和 b
        // 这里 num的结果 相当于两个这个数字的异或结果 num = a ^ b

        int num1 = num & (-num);   // 这里拿到了num这个数字的最后一位1, 可以得出结论， a 和 b 这两个数字， 必然有一个数字的这一位是0， 另一个是1

        int num2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & num1) != 0) {
                // 进到这个判断中的， 说明这个数和 num1 那个1在的位置一样也是1
                num2 = num2 ^ arr[i];
            }
        }
        // 结束之后， 拿到的num2 就是 a 和 b 这两个数之中， num一样， 最后那位是1 的数
        return new  int []{num2, num ^ num2};
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int[] array = generateTheArray(100);
            int[] twoOdds = getTwoOdd(array);
            int times1 = 0;
            for (int j = 0; j < array.length; j++) {
                if (twoOdds[0] == array[j]) {
                    times1++;
                }
            }
            boolean errorFlag = false;
            if (times1 % 2 == 0) {
                errorFlag = true;
            }
            int times2 = 0;
            for (int j = 0; j < array.length; j++) {
                if (twoOdds[1] == array[j]) {
                    times2++;
                }
            }
            if (times2 % 2 == 0) {
                errorFlag = true;
            }
            if (errorFlag) {
                System.out.println("出错了！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(array);
                HashMap<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < array.length; j++) {
                    if (map.containsKey(array[j])) {
                        map.put(array[j], map.get(array[j]) + 1);
                    }  else {
                        map.put(array[j], 1);
                    }
                }
                System.out.println("数组中： " );
                for (Integer key : map.keySet()) {
                    System.out.println("数字: " + key + ", 出现了 " + map.get(key) + "次！");
                }
                System.out.println("我的结果是： " );
                System.out.println("数1是： " + twoOdds[0] + ", 出现了 " + times1 + "次！" );
                System.out.println("数2是： " + twoOdds[1] + ", 出现了 " + times2 + "次！" );
                System.out.println("----------------------------------------------------------------");
            }
        }
        System.out.println("花费时间（ms）： " + (System.currentTimeMillis() - mills));

    }

    public static int[] generateTheArray(int range){
        List<Integer> list = new ArrayList<>();
        int num1 = (int)(Math.random() * range) +1 - (int)(Math.random() * range) +1;
        int num1Times = (int)(Math.random() * 10);
        if(num1Times%2 == 0){
            num1Times++;
        }
        for(int i=0;i<num1Times;i++){
            list.add(num1);
        }
        int num2 = (int)(Math.random() * range) +1 - (int)(Math.random() * range) +1;
        while (num2 == num1) {
            num2 = (int)(Math.random() * range) +1 - (int)(Math.random() * range) +1;
        }
        int num2Times = (int)(Math.random() * 10);
        if(num2Times%2 == 0){
            num2Times++;
        }
        for(int i=0;i<num2Times;i++){
            list.add(num2);
        }
        int numKind = (int)(Math.random() * 10) + 1;
        for(int j=0;j<numKind;j++){
            int evenTimesNum = (int)(Math.random() * range) + 1 - (int)(Math.random() * range) +1;
            int evenTimes = (int)(Math.random() * 10);
            if(evenTimes%2 != 0){
                evenTimes++;
            }
            for (int i = 0; i < evenTimes; i++) {
                list.add(evenTimesNum);
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
