package com.roy.algorithmlearning.beginner.class02;

import com.roy.algorithmlearning.util.ArrayUtil;

public class Random {

    public static void main(String[] args) {
        int repeateTimes = 10000000;
        int [] countNum = new int[10];
        for (int i = 0; i < repeateTimes; i ++){
            int ans = random0To9();
            countNum[ans]++;
        }
        ArrayUtil.printlnArray(countNum);


    }

    //由Math等概率发生器
    public static int random0To9(){
        return (int) (Math.random() * 10);
    }


}
