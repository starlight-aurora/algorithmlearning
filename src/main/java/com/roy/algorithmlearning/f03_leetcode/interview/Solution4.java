package com.roy.algorithmlearning.f03_leetcode.interview;

public class Solution4 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     * @param pHead1 ListNode类
     * @param pHead2 ListNode类
     * @return ListNode类
     */
    public ListNode Merge (ListNode pHead1, ListNode pHead2) {
        // write code here
        if (pHead1 == null){
            return pHead2;
        }
        if (pHead2 == null){
            return pHead1;
        }

        if (pHead1.val < pHead2.val){
            pHead1.next = Merge(pHead1.next, pHead2);
            return pHead1;
        }else{
            pHead2.next = Merge(pHead1, pHead2.next);
            return pHead2;
        }

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(5);
        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(4);
        node2.next.next = new ListNode(6);
        ListNode res = new Solution4().Merge(node1, node2);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
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
