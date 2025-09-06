package com.roy.algorithmlearning.f01_beginner.class04;

//两个链表的值相加，类似计算两个多位数倒序相加的和
public class AddTwoNumberLink {

    //测试地址： https://leetcode.cn/problems/add-two-numbers/

    public static ListNode addTwoNumberLink(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        int length1 = getLinkLength(node1);
        int length2 = getLinkLength(node2);
        //相加的和记录在longNode里面，后面需要返回这个头
        ListNode longNode = length1 > length2 ? node1 : node2;
        ListNode shortNode = longNode == node1 ? node2 : node1;

        ListNode currentLong = longNode;
        ListNode currentShort = shortNode;

        //记录进位信息
        int carry = 0;
        int currentNum;
        ListNode last = null;

        //第一阶段，两个链表都还有值
        //如果短的序列还有值，那么两个都有值
        while (currentShort != null) {
            currentNum = currentLong.val + currentShort.val + carry;
            currentLong.val = (currentNum % 10);
            carry = currentNum / 10;
            last = currentLong;
            currentLong = currentLong.next;
            currentShort = currentShort.next;
        }
        //第二阶段，短序列已经没有值，长序列还有值
        while (currentLong != null) {
            currentNum = currentLong.val + carry;
            currentLong.val = (currentNum % 10);
            carry = currentNum / 10;
            last = currentLong;
            currentLong = currentLong.next;
        }
        //第三阶段，两个序列都已经没有值，但是进位可能还有值
        if (carry != 0){
            last.next = new ListNode(carry);
        }
        return longNode;
    }

    public static int getLinkLength(ListNode head) {
        int size = 0;
        while (head.next != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static class ListNode {
        Integer val;
        ListNode next;

        public ListNode(Integer value) {
            this.val = value;
        }
    }


}
