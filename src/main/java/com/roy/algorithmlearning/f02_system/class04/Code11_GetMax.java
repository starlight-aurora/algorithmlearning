package com.roy.algorithmlearning.f02_system.class04;

/**
 * 递归实现寻找数组最大值
 *
 * 计算递归的时间复杂度： Master公式
 *
 * 形如 T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数) 的递归函数，即每次分组都是一样的（每次分成两个一样的子任务）
 * 可以直接通过Master公式来确定时间复杂度
 * 如果 log(b,a) < d，复杂度为O(N^d)
 * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
 * 如果 log(b,a) == d，复杂度为O(N^d  * logN)
 *
 */
public class Code11_GetMax {


}
