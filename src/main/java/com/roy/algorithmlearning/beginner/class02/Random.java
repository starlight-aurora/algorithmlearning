package com.roy.algorithmlearning.beginner.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

public class Random {

    public static void main(String[] args) {
        int repeatTimes = 10000000;
        int [] countNum = new int[10];
        for (int i = 0; i < repeatTimes; i ++){
            int ans = random0To9();
            countNum[ans]++;
        }
        ArrayUtil.printlnArray(countNum);

        //说明random生成小于x（0-1）的概率就是x
        double x = 0.3;
        int count = 0;
        for (int i = 0; i < repeatTimes; i ++){
            double y = Math.random();
            if (y < x){
                count++;
            }
        }
        System.out.println(count);
        System.out.println((double) count / (double) repeatTimes);
        System.out.println();

        //说明 randomPower2 生成小于x（0-1）的概率就是 x的平方
        double x1 = 0.3;
        int count1 = 0;
        for (int i = 0; i < repeatTimes; i ++){
            double y1 = randomPower2();
            if (y1 < x1){
                count1++;
            }
        }
        System.out.println(count1);
        System.out.println((double) count1 / (double) repeatTimes);
        System.out.println(Math.pow(x1, 2));
        System.out.println();

        //说明 randomPower2Min 生成小于x（0-1）的概率就是 1-(1-x)的平方, 即： 1-（1-x）^2
        double x2 = 0.3;
        int count2 = 0;
        for (int i = 0; i < repeatTimes; i ++){
            double y2 = randomPower2Min();
            if (y2 < x2){
                count2++;
            }
        }
        System.out.println(count2);
        System.out.println((double) count2 / (double) repeatTimes);
        System.out.println(1-Math.pow(1-x2, 2));
        System.out.println();

        //说明 randomPower3 生成小于x  [0-1）的概率就是 x的3次方
        double x3 = 0.3;
        int count3 = 0;
        for (int i = 0; i < repeatTimes; i ++){
            double y3 = randomPower3();
            if (y3 < x3){
                count3++;
            }
        }
        System.out.println(count3);
        System.out.println((double) count3 / (double) repeatTimes);
        System.out.println(Math.pow(x3, 3));
        System.out.println();

    }

    //由Math.random()产生等概率发生器： 0-9
    public static int random0To9(){
        return (int) (Math.random() * 10);
    }

    public static double randomPower2(){
        return Math.max(Math.random(), Math.random());
    }

    public static double randomPower2Min(){
        return Math.min(Math.random(), Math.random());
    }

    public static double randomPower3(){
        return Math.max(Math.random(), Math.max(Math.random(), Math.random()));
    }


}
