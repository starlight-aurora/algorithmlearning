package com.roy.algorithmlearning.f01_beginner.class04;

public class ReverseLinkedNodeInKGroup {

    // 测试地址： https://leetcode.cn/problems/reverse-nodes-in-k-group/

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        //找到第一组的末尾
        ListNode end = getKNode(head, k);
        // 所给链表不到K长度
        if (end == null) {
            return head;
        }
        //这个将是新的头，不会再改啦
        head = end;
        reverseNode(start, end);
        ListNode lastEndNode = start;
        while (lastEndNode.next != null) {
            start = lastEndNode.next;
            end = getKNode(start, k);
            // 剩下的链表不到K长度
            if (end == null) {
                return head;
            }
            reverseNode(start, end);
            lastEndNode.next = end;
            lastEndNode = start;
        }
        return head;
    }

    //从start Node到 end Node 逆序
    public static void reverseNode(ListNode start, ListNode end) {
        //把所在段落下一个位置记下来，后面会连上
        end = end.next;
        ListNode pre = null;
        ListNode currentNode = start;
        ListNode next = null;
        while ( currentNode != end) {
            next = currentNode.next;
            currentNode.next = pre;
            pre = currentNode;
            currentNode = next;
        }
        start.next = end;
    }

    public static ListNode getKNode(ListNode head, int k) {
        // 不是k--， 是--k, 先数数，再往下跳，不然会多跳了一个
        while (--k != 0 && head != null) {
            head = head.next;
        }
        return head;
    }

    public static class ListNode {
        Integer value;
        ListNode next;

        public ListNode(Integer value) {
            this.value = value;
        }
    }

}
