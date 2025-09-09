package com.roy.algorithmlearning.f02_system.class06;

import com.roy.algorithmlearning.f05_bean.DoubleNode;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 双向链表的随机快速排序
 *
 * 排序的是 双向队列
 *
 * < x, = x 和 >x  加上随机最右侧
 */
public class Code08_QuickSort {

    public static DoubleNode<Integer> quickSort(DoubleNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode<Integer> cur = head;
        DoubleNode<Integer> end = null;
        int len = 0;
        while (cur != null) {
            end = cur;
            cur = cur.next;
            len++;
        }
        HeadTail<Integer> headTail = process(head, end, len);
        return headTail.head;
    }

    public static HeadTail<Integer> process(DoubleNode<Integer> head, DoubleNode<Integer> end, int len) {
        if (head == null) {
            return null;
        }
        if (head == end) {
            return new HeadTail<>(head, end);
        }
        // 随机抠出一个节点作为比较的节点
        int num = (int) (Math.random() * len);
        DoubleNode<Integer> cur = head;
        while (num > 0) {
            cur = cur.next;
            num--;
        }
        DoubleNode<Integer> pre;
        DoubleNode<Integer> next;
        if (cur == head) {
            // 直接把头结点定位到下一个节点
            head = cur.next;
            head.last = null;
        }else {
            if (cur != end) {
                // 中间 节点, 直接把前后连起来
                pre = cur.last;
                next = cur.next;
                pre.next = next;
                next.last = pre;
            } else {
                // 选择的是尾节点, 断掉前面一个节点的下一个节点
                cur.last.next = null;
            }
        }
        // 把cur节点孤立出来
        cur.next = null;
        cur.last = null;

        // 可以拿到荷兰国旗的三个部分
        HeadTailWithLen<Integer> headTailWithLen = partition(head, cur);

        // 左半区的结果排序
        HeadTail<Integer> leftHeadTailWithLen = process(headTailWithLen.lessHead, headTailWithLen.lessTail, headTailWithLen.lessLen);
        // 右半区的结果排序
        HeadTail<Integer> rightHeadTailWithLen = process(headTailWithLen.moreHead, headTailWithLen.moreTail, headTailWithLen.moreLen);

        // 把三块的结果拼起来, 即在 区间上已经排好序
        DoubleNode<Integer> newHead = null;
        DoubleNode<Integer> newEnd = null;
        if (leftHeadTailWithLen != null) {
            leftHeadTailWithLen.tail.next = headTailWithLen.equalHead;
            headTailWithLen.equalHead.last = leftHeadTailWithLen.tail;
            newHead = leftHeadTailWithLen.head;
        }
        if (rightHeadTailWithLen != null) {
            headTailWithLen.equalHead.next = rightHeadTailWithLen.head;
            rightHeadTailWithLen.head.last = headTailWithLen.equalTail;
            newEnd = rightHeadTailWithLen.tail;
        }
        if (newHead == null) {
            newHead = headTailWithLen.equalHead;
        }
        if (newEnd == null) {
            newEnd = headTailWithLen.equalTail;
        }
        return new HeadTail<>(newHead, newEnd);
    }

    /**
     * 在head节点链里面, 每个比较与end节点的值得关系, 分类
     * @param head 头结点串联的链
     * @param end 比较的节点, 其实没有尾节点的含义
     * @return 三个区间的信息
     */
    public static HeadTailWithLen<Integer> partition(DoubleNode<Integer> head, DoubleNode<Integer> end) {
        DoubleNode<Integer> lessHead = null;
        DoubleNode<Integer> lessEnd = null;
        int lessLen = 0;

        end.next = null;
        end.last = null;
        DoubleNode<Integer> equalHead = end;
        DoubleNode<Integer> equalEnd = end;
        int equalLen = 1;

        DoubleNode<Integer> moreHead = null;
        DoubleNode<Integer> moreEnd = null;
        int moreLen = 0;

        // 循环查看移动
        DoubleNode<Integer> cur = head;
        while (cur != null) {
            // 先把下一个节点保存下来
            DoubleNode<Integer> next = cur.next;
            // 清空当前节点的指针
            cur.next = null;
            cur.last = null;
            if (cur.value < end.value) {
                // 如果当前的值小于最后节点的值
                if (lessHead == null) {
                    lessHead = cur;
                    lessEnd = cur;
                }  else {
                    lessEnd.next = cur;
                    cur.last = lessEnd;
                    lessEnd = cur;
                }
                lessLen++;
            } else if (cur.value.equals(end.value)) {
                // 当前节点的值等于最后一个节点的值
                equalEnd.next = cur;
                cur.last = equalEnd;
                equalEnd = cur;
                equalLen++;
            } else if (cur.value > end.value) {
                // 如果当前的值大于最后节点的值
                if (moreHead == null) {
                    moreHead = cur;
                    moreEnd = cur;
                } else   {
                    moreEnd.next = cur;
                    cur.last = moreEnd;
                    moreEnd = cur;
                }
                moreLen++;
            }
            cur = next;
        }
        return new HeadTailWithLen<>(lessHead, lessEnd, lessLen, equalHead, equalEnd, equalLen, moreHead, moreEnd, moreLen);
    }

    public static class HeadTail<T> {
        DoubleNode<T> head;
        DoubleNode<T> tail;

        public HeadTail(DoubleNode<T> head, DoubleNode<T> tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static class HeadTailWithLen<T> {
        DoubleNode<T> lessHead;
        DoubleNode<T> lessTail;
        int lessLen;

        DoubleNode<T> equalHead;
        DoubleNode<T> equalTail;
        int equalLen;

        DoubleNode<T> moreHead;
        DoubleNode<T> moreTail;
        int moreLen;

        public HeadTailWithLen(DoubleNode<T> lessHead, DoubleNode<T> lessTail, int lessLen,
                               DoubleNode<T> equalHead, DoubleNode<T> equalTail, int equalLen,
                               DoubleNode<T> moreHead, DoubleNode<T> moreTail, int moreLen) {
            this.lessHead = lessHead;
            this.lessTail = lessTail;
            this.lessLen = lessLen;
            this.equalHead = equalHead;
            this.equalTail = equalTail;
            this.equalLen = equalLen;
            this.moreHead = moreHead;
            this.moreTail = moreTail;
            this.moreLen = moreLen;
        }
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            DoubleNode node = generateRandomDoubleNode(100, 1000);
            DoubleNode clonedNode = cloneRandomDoubleNode(node);
            DoubleNode sorted = sortDoubleNode(clonedNode);
            DoubleNode quickSort = quickSort(node);
            if ((int) quickSort.value != (int) sorted.value) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

    /**
     * 生成测试的节点链
     */
    public static DoubleNode generateRandomDoubleNode(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength) +1;
        DoubleNode[] nodeArray = new DoubleNode[len];
        for (int i = 0; i < len; i++) {
            int value = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            DoubleNode<Integer> node = new DoubleNode<>(value);
            nodeArray[i] = node;
        }
        for (int i = 0; i < len-1; i++) {
            nodeArray[i].next = nodeArray[i+1];
            nodeArray[i+1].last = nodeArray[i];
        }

        return nodeArray[0];
    }

    /**
     * 克隆出一个相同的节点链
     */
    public static DoubleNode cloneRandomDoubleNode(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode newHead =  new DoubleNode(head.value);
        DoubleNode newCur = newHead;
        DoubleNode cur = head.next;
        while (cur != null) {
            DoubleNode newNode = new DoubleNode(cur.value);
            newCur.next = newNode;
            newNode.last = newCur;
            newCur = newCur.next;
            cur = cur.next;
        }
        return newHead;
    }

    /**
     * 常规方法排序一个链表
     */
    public static DoubleNode sortDoubleNode(DoubleNode head) {
        if (head == null ||  head.next == null) {
            return head;
        }
        ArrayList<DoubleNode<Integer>> arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head);
            head = head.next;
        }
        arrayList.sort(Comparator.comparingInt(node -> node.value));
        DoubleNode newHead = arrayList.get(0);
        newHead.last = null;
        DoubleNode newCur = newHead;
        for (int i = 1; i < arrayList.size(); i++) {
            DoubleNode next = arrayList.get(i);
            newCur.next = next;
            next.last = newCur;
            next.next = null;
            newCur = next;
        }
        return newHead;
    }

}
