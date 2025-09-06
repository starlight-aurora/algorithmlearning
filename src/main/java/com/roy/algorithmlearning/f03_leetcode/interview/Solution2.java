package com.roy.algorithmlearning.f03_leetcode.interview;

public class Solution2 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 反转链表中某一段
     *
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        // write code here
        if (head == null || (m==1 && n==1)){
            return head;
        }
        // 创建一个虚拟节点, 免得需要很多边界判断
        ListNode fakeNode = new ListNode(-1);
        fakeNode.next = head;

        // 找到要反转的上一个节点
        ListNode pre = fakeNode;
        for (int i=0; i<m-1; i++){
            pre = pre.next;
        }
        // 记录待反转的节点头， 待反转的节点尾
        ListNode curr = pre.next;
        ListNode leftNode = curr;
        ListNode rightNode = curr;
        for (int i=m; i<n; i++){
            rightNode = rightNode.next;
        }
        // 记录后一个节点， 后面重新接上
        ListNode nextNode = rightNode.next;

        // 断掉链表， 要反转的成为一个单独的子链表
        pre.next = null;
        rightNode.next = null;

        curr = ReverseList(curr);

        // 重新连接链表
        pre.next = curr;
        leftNode.next = nextNode;

        return fakeNode.next;
    }

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

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = s.reverseBetween(head, 2, 4);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

}