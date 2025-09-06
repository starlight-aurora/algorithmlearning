package com.roy.algorithmlearning.f02_system.class03;

/**
 * 一个数， 只保留它二进制情况下的最后一位的1， 返回这个数
 */
public class Code03_GetTheRightestOne {

    public static int getTheRightestOne(int num){
        int ans = num & (-num);
        // int ans = num & (~num +1)   // 这种写法也可以, 实际上 (~num +1) == (-num)

        return ans;
    }

    /**
     * 分析：
     * 假设： num = 10， 则 num的二进制为： 000……0001010
     *                  ~num 的二进制为： 111……1110101
     *                ~num+1 的二进制为： 111……1110110
     *                num & (~num+1) :  000……0000010
     *
     *       -num = -10，-num的二进制位：  111……1110110
     *           与上面效果是一样的
     */

    public static void main(String[] args) {
        for(int a = 0; a <= 100; a++){
            int i = getTheRightestOne(a);
            System.out.println(i);
        }
    }

}
