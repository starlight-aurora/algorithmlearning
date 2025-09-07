package com.roy.algorithmlearning.f02_system.class04;


import com.roy.algorithmlearning.f05_bean.SingleNode;

/**
 * 单向链表反转
 *
 * https://leetcode.cn/problems/UHnkqh/description/
 * https://leetcode.cn/problems/reverse-linked-list/description/
 */
public class Code01_SingleNodeReverse {

    public static SingleNode<Integer> reverseSingle(SingleNode<Integer> head){

        SingleNode<Integer> pre = null;
        SingleNode<Integer> next = null;

        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        SingleNode<Integer> head = new SingleNode<>(1);
        head.next = new SingleNode<>(2);
        head.next.next = new SingleNode<>(3);

        SingleNode<Integer> res = reverseSingle(head);

        while (res!=null){
            System.out.println(res.value);
            res = res.next;
        }
    }

}
