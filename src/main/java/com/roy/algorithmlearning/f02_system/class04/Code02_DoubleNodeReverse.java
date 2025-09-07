package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.f05_bean.DoubleNode;

/**
 * 双向链表反转
 */
public class Code02_DoubleNodeReverse {

    public static DoubleNode<Integer> reverseDouble(DoubleNode<Integer> head){
        DoubleNode<Integer> pre = null;
        DoubleNode<Integer> next = null;

        while(head!=null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        DoubleNode<Integer> node1 = new DoubleNode<>(1);
        DoubleNode<Integer> node2 = new DoubleNode<>(2);
        DoubleNode<Integer> node3 = new DoubleNode<>(3);

        node1.next = node2;
        node1.last = null;

        node2.next = node3;
        node2.last = node1;

        node3.next = null;
        node3.last = node2;

        DoubleNode<Integer> res = reverseDouble(node1);

        while (res != null) {
            System.out.println(res.value);
            res = res.next;
        }

    }

}
