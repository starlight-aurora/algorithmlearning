package com.roy.algorithmlearning.f02_system.class04;

import com.roy.algorithmlearning.f05_bean.SingleNode;

import java.util.LinkedList;

/**
 * 单向链表中删除value为某个值的节点
 */
public class Code03_DeleteGivenValue {

    public static SingleNode<Integer> deleteGivenValue(SingleNode<Integer> head, int value) {
        while (head != null) {
            if (head.value == value) {
                head = head.next;
            }else {
                break;
            }
        }
        SingleNode<Integer> pre = head;
        SingleNode<Integer> cur = head.next;
        while (cur != null) {
            if (value == cur.value) {
                pre.next = cur.next;
                cur = cur.next;
            } else  {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SingleNode<Integer> head = new SingleNode<>(1);
        head.next = new SingleNode<>(2);
        head.next.next = new SingleNode<>(3);
        head.next.next.next = new SingleNode<>(2);
        head.next.next.next.next = new SingleNode<>(3);
        head.next.next.next.next.next = new SingleNode<>(2);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(2);

        SingleNode<Integer> res = deleteGivenValue(head, 2);

        list.removeIf(value -> value == 2);

        while (res!=null && !list.isEmpty()){
            int num1 = res.value;
            int num2 = list.poll();
            if (num1 != num2){
                System.out.println("出错了！");
            }
            res = res.next;
        }
    }

}
