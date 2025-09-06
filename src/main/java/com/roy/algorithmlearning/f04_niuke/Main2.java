package com.roy.algorithmlearning.f04_niuke;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        getBest(b);

    }

    static void getBest(String b){
        String [] s = b.split(" ");
        if(s.length <= 1){
            System.out.println(0);
            return;
        }
        int[] arr = new int [s.length];
        for(int i=0; i < s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        long minNum = Long.MAX_VALUE;
        int i = 0;
        int sum1 = 0;
        int sum2 = Arrays.stream(arr).sum();

        while(i< arr.length-1){
            sum1 += arr[i];
            sum2 -= arr[i];

            long num = (long)sum1 * (long)sum2;
            if(num < 0){
                continue;
            }
            if (num < minNum){
                minNum = num;
            }
            i++;
        }

//        int[] preSum = new int [s.length+1];
//        for(int i=0; i < arr.length; i++){
//            preSum[i+1] = preSum[i] + arr[i];
//        }
//
//        for(int i=0; i<preSum.length-2; i++){
//            int sum1 = preSum[i+1];
//            int sum2 = preSum[preSum.length-1] - sum1;
//
//            int num = sum1 * sum2;
//            if(num < 0){
//                continue;
//            }
//            if (num < minNum){
//                minNum = num;
//            }
//        }
        System.out.println(minNum);
    }
}