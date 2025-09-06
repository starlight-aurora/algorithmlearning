package com.roy.algorithmlearning.f03_leetcode.interview;

import java.util.*;

/**
 * 给定一个表示若干个区间的集合数组intervals，每个区间由起始位置start和结束位置end组成。
 * 请合并所有重叠的区间，返回一个不重叠的区间数组，该数组需包含输入中的所有区间，并确保区间之间没有重叠。
 * https://leetcode.cn/problems/merge-intervals/
 */
class Solution {

    // 最佳答案
    public int[][] merge5(int[][] intervals) {
        int max = 0;
        for(int[] interval : intervals) {
            max = Math.max(max, interval[1]);
        }
        // 不能算整数， [1,4], [4,5], 称两倍算相当于算0.5
        int[] a = new int[2 * max + 2];
        for(int[] interval : intervals){
            a[interval[0] * 2]++;
            a[interval[1] * 2 + 1]--;
        }
        List<int[]> ans = new ArrayList<>();
        int sum = 0;
        int start = -1;
        for(int i = 0;i < 2 * max + 2;i++){
            sum += a[i];
            if(start == -1 && sum > 0) start = i;
            if(start != -1 && sum == 0){
                ans.add(new int[]{start / 2, i / 2});
                start = -1;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] merge4(int[][] intervals) {
        int max = 0;
        int min = 0;
        for(int[] interval : intervals){
            min = Math.min(min, interval[1]);
            max = Math.max(max, interval[1]);
        }
        int[] a = new int[max+1];
        for(int[] interval : intervals){
            int start = interval[0];
            int end = interval[1];
            for(int i = start; i <= end; i++){
                a[i] = 1;
            }
        }
        List<List<Integer>> resList = new ArrayList<>();
        int start = -1;
        int end = -1;
        for(int i =1;i < a.length;i++){
            if(a[i] == 0){
                continue;
            }
            if ( i == 1 && a[0] == 1){
                start = 0;
            }
            if (i== a.length-1 && a[a.length-1] == 1){
                end = a.length-1;
                if (start == -1){
                    start = end;
                }
                resList.add(Arrays.asList(start, end));
                break;
            }
            if (a[i] == 1 && a[i - 1] == 0){
                start = i;
            }
            if (a[i] == 1 && a[i + 1] == 0){
                end = i;
            }
            if(start != -1 && end != -1){
                resList.add(Arrays.asList(start, end));
                start = -1;
                end = -1;
            }
        }
        return resList.stream().map(list->new int[]{list.get(0), list.get(1)}).toArray(int[][]::new);
    }

    public int[][] merge3(int[][] intervals) {
        // write your code here.
        Arrays.sort(intervals, (a, b)->a[0]-b[0]);

        List<List<Integer>> resList = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i<intervals.length; i++){
            pq.add(intervals[i][1]);
        }
        for (int i = 0; i < intervals.length; ) {
            int L = intervals[i][0];
            int R;
            int j = i + 1;
            while (true) {
                if (j == intervals.length) {
                    R = intervals[j - 1][1];
                    i = j;
                    break;
                }
                int next_start = intervals[j][0];
                int end = pq.poll();
                if (next_start > end) {
                    R = end;
                    i = j;
                    break;
                } else {
                    j++;
                }
            }
            resList.add(Arrays.asList(L, R));
        }
        return resList.stream().map(list->new int[]{list.get(0), list.get(1)}).toArray(int[][]::new);
    }

    public int[][] merge2(int[][] intervals) {
        // write your code here.
        Arrays.sort(intervals, (a, b)->a[0]-b[0]);

        List<List<Integer>> resList = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i<intervals.length; i++){
            pq.add(intervals[i][1]);
        }
        a: for (int i = 0; i<intervals.length; ){
            int L = intervals[i][0];
            int j = i+1;
            while (true){
                if (pq.isEmpty()){
                    break;
                }
                if (j== intervals.length){
                    resList.add(Arrays.asList(L, pq.poll()));
                    break a;
                }
                int next_start = intervals[j][0];
                int end = pq.poll();
                if (next_start > end){
                    resList.add(Arrays.asList(L, end));
                    i=j;
                    break;
                } else {
                    j++;
                }
            }
        }
        return resList.stream().map(list->new int[]{list.get(0), list.get(1)}).toArray(int[][]::new);
    }


    /**
     * 面试时写的
     * @param intervals
     * @return
     */
    public int[][] merge1(int[][] intervals) {
        // write your code here.

        List<Integer> startList = new ArrayList<>();
        for (int i = 0; i<intervals.length; i++){
            startList.add(intervals[i][0]);
        }
        int[][] res = new int [intervals.length][2];
        int index = 0;
        int lastend = -1;
        for (int i = 0; i<intervals.length -1 ; i++){
            int end = intervals[i][1];
            if (lastend != -1 && lastend > end){
                continue;
            }

            int start = startList.get(i+1);
            int newEnd = end;
            if(end >= start){
                if(end < intervals[i][1]){
                    newEnd= intervals[i][1];
                }else{
                    i++;
                }
            }
            lastend = newEnd;
            int [] arr = new int []{start, newEnd};
            res[index] = arr;
            index++;
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] res = new Solution().merge1(intervals);
        for (int i = 0; i<res.length; i++){
            System.out.println(res[i][0] + " " + res[i][1]);
        }
//        test();
    }

    public static void test(){
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        pq.add(1);
        pq.add(2);
        pq.add(3);
        pq.add(2);
        while (!pq.isEmpty()){
            int L = pq.poll();
            System.out.println(L);
        }
    }
}