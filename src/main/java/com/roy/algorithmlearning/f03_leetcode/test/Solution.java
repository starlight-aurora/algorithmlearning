package com.roy.algorithmlearning.f03_leetcode.test;

/*
 给定第几列, 输出Excel的头(A, B...AB...AAB)
 https://leetcode.cn/problems/excel-sheet-column-title/description/
 */
class Solution {
    public static String numToTitle(int num) {
        StringBuilder sb = new StringBuilder();
        int a = num;
        while(a > 0){
            int b = (a -1) % 26 +1;
            sb.append((char)(b-1 + 'A'));
            a = (a -b) / 26;
        }
        StringBuilder sb1 = new StringBuilder();
        for (int i=sb.length()-1; i >= 0 ; i--){
            sb1.append(sb.charAt(i));
        }
        return sb1.toString();
    }

    public static void main(String[] args) {
        int num =1000;
        String s = numToTitle(num);
        System.out.println(s);
    }
}