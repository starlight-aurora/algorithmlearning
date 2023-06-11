package com.roy.algorithmlearning.beginner.class01;

public class ByteCaculate {

    public static void main(String[] args) {
        printByte(5);
        printByte(1000);
        printByte(Integer.MAX_VALUE);
        printByte(Integer.MIN_VALUE);
        printByte(0);
        printByte(-1);
    }

    public static void printByte(int num) {
        for (int i = 31; i >= 0; i--) {
            String x = (num & (1 << i)) == 0 ? "0" : "1";
            System.out.print(x);
        }
        System.out.print("       print byte of: " + num + "      ");
        System.out.println();
    }
}
