package com.roy.algorithmlearning.f05_bean;

/**
 * 单向链表
 */
public class SingleNode<T> {

    public int value;

    public SingleNode<T> next;

    public SingleNode() {}

    public SingleNode(int value) {
        this.value = value;
    }

    public SingleNode(int value, SingleNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
