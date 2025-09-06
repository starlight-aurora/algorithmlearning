package com.roy.algorithmlearning.f01_beginner.class04;

//两个有序链表的合并
public class MergeTwoSortedLinkedList {

    // 测试链接：https://leetcode.com/problems/merge-two-sorted-lists

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2){
        if (head1 == null || head2 == null){
            return head1 != null ? head1 : head2;
        }
        //最终要返回的头，不能再改了
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode pre = head;
        ListNode current1 = head.next;
        ListNode current2 = head == head1 ? head2 : head1;
        while (current1 != null && current2 != null){
            if (current1.val <= current2.val) {
                pre.next = current1;
                current1 = current1.next;
            } else {
                pre.next = current2;
                current2 = current2.next;
            }
            pre = pre.next;
        }
        //至少有一个为空了
        pre.next = current1 == null ? current2 : current1;
        return head;
    }


    public static class ListNode {
        Integer val;
        ListNode next;

        public ListNode(Integer value) {
            this.val = value;
        }
    }
}
