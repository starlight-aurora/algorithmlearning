package com.roy.algorithmlearning.util;

public class ArrayUtil {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void printlnArray(int[] array) {
        for (int j : array) {
            System.out.println(j + " ");
        }
        System.out.println();
    }
}
