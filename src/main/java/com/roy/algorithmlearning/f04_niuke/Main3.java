package com.roy.algorithmlearning.f04_niuke;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        String [] strArr = new String[Integer.parseInt(s2)];
        int i=0;
        while(in.hasNextLine()) {
            String str = in.nextLine();
            strArr[i] = str;
            if (i==strArr.length-1){
                break;
            }
            i++;
        }

        Arrays.sort(strArr);
        for (String s : strArr) {
            System.out.print(s);
        }
    }
}