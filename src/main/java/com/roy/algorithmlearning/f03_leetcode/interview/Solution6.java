package com.roy.algorithmlearning.f03_leetcode.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 寻找数组中第K大的数字
 */
public class Solution6 {

    public int findKthLargest(int[] nums, int k) {
        // write your code here.

        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return -1;
        }

        Map<Integer, Integer>  map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry.getKey());
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            int count = map.get(num);
            if (sum + count >= k) {
                return num;
            } else  {
                sum += count;
            }
        }
        return -1;
    }
}
