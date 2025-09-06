package com.roy.algorithmlearning.f03_leetcode.interview;

import java.util.*;

public class Solution5 {
    /**
     * Leetcode 题目的变种，原题： 90. 子集 II - 力扣（LeetCode）
     * https://leetcode.cn/problems/subsets-ii/description/
     *
     * 给你一个整数数组 nums ，数组中的元素 可能存在重复 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集（可能有重复的元素）
     * 按 字典顺序 返回解集
     *
     * @param nums int整型一维数组
     * @return int整型ArrayList<ArrayList<>>
     */

    private ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        arr.add(new ArrayList<>());

        Arrays.sort(nums);

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            List<List<Integer>> temp = new ArrayList<>(arr);
            for (List<Integer> list : temp) {
                ArrayList<Integer> newList = new ArrayList<>(list);
                newList.add(num);
                newList.sort(Comparator.naturalOrder());
                arr.add(newList);
            }
            set.add(num);
        }
        // 字典序排序
        arr.sort((arr1, arr2) -> {
            int m = Math.min(arr1.size(), arr2.size());
            int res=0;
            for (int i = 0; i < m; i++){
                if (arr1.get(i).intValue() != arr2.get(i).intValue()) {
                    res = arr1.get(i) - arr2.get(i);
                    return res;
                }
            }
            res = arr1.size() - arr2.size();
            return res;
        });

        return arr;
    }

    /**
     * 面试时做的
     *
     * @param nums
     * @return
     */
    public ArrayList<ArrayList<Integer>> subsets1 (int[] nums) {
        // write code here
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        arr.add(new ArrayList<>());
        int startIndex = 0;
        int endIndex =1;
        int length = nums.length;

        Set<Integer> set = new HashSet<>();

        while (startIndex < endIndex && endIndex <= length){
            if(set.contains(nums[startIndex])){
                startIndex++;
                continue;
            }

            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = startIndex; i < endIndex; i++){
                temp.add(nums[i]);
            }
            arr.add(temp);
            if (endIndex ==  length){
                set.add(nums[startIndex]);
                startIndex ++;
                endIndex = startIndex+1;
            }else {
                endIndex++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution5 s = new Solution5();
        int[] nums = new int[]{3,6,3,7,5};
        ArrayList<ArrayList<Integer>> res = s.subsets(nums);
        for (ArrayList<Integer> list : res){
            for (Integer i : list){
                System.out.print(i + ",");
            }

            System.out.println();
        }
    }

}
