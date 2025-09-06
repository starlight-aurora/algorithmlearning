package com.roy.algorithmlearning.f03_leetcode.interview;

public class Solution3 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * K个一组反转链表
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        ListNode tail = head;
        for (int i=0; i<k; i++){
            if (tail == null){
                return head;
            }
            tail = tail.next;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != tail){
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
        head.next = reverseKGroup(tail, k);

        return pre;
    }

    public static class ListNode{
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }

    }
}
