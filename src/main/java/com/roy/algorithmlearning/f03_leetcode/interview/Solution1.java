package com.roy.algorithmlearning.f03_leetcode.interview;

public class Solution1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *  翻转整个链表
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public static ListNode ReverseList(ListNode head) {
        // write code here
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null){
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
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

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode newNode = ReverseList(node1);

        while (newNode != null){
            System.out.println(newNode.val);
            newNode = newNode.next;
        }

    }
}