package com.roy.algorithmlearning.f05_bean;

/**
 * 双向链表
 */
public class DoubleNode<T> {

    public T value;

    public DoubleNode<T> last;
    public DoubleNode<T> next;

    public DoubleNode() {}

    public DoubleNode(T value) {
        this.value = value;
    }

    public DoubleNode(T value, DoubleNode<T> last, DoubleNode<T> next) {
        this.value = value;
        this.last = last;
        this.next = next;
    }

}
