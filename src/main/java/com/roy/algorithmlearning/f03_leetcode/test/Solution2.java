package com.roy.algorithmlearning.f03_leetcode.test;

/*
   给定Excel的序号, 判断是第几列
   https://leetcode.cn/problems/excel-sheet-column-number/
 */
class Solution2 {
    public static int titleToNumber(String columnTitle) {
        int number = 0;
        int multiple = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int k = columnTitle.charAt(i) - 'A' + 1;
            number += k * multiple;
            multiple *= 26;
        }
        return number;
    }

    public static void main(String[] args) {
        String s = "ALL";
        int i = titleToNumber(s);
        System.out.println(i);

    }
}