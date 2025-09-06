package com.roy.algorithmlearning.f04_niuke;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case

            int a = in.nextInt();
            if (a != 0){
                System.out.println(caculateTheNum(a));
            }

        }

    }

    public static int caculateTheNum(int m){
        if (m > 2){
            int a = m/3;
            int b = m%3;

            return caculateTheNum(b + a) + a;
        }else if(m == 2){
            return 1;
        }else {
            return 0;
        }
    }
}