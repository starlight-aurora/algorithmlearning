package com.roy.algorithmlearning.beginner.class04;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedNode {

    public static void main(String[] args) {

        OneWayNode node1 = new OneWayNode(1);
        node1.next = new OneWayNode(2);
        node1.next.next = new OneWayNode(3);

        printOneWayNode(node1);

        node1 = reverseOneWayNode(node1);
        printOneWayNode(node1);

        TwoWaysNode twoWaysNode1 = new TwoWaysNode(1.5);
        TwoWaysNode twoWaysNode2 = new TwoWaysNode(2.5);
        TwoWaysNode twoWaysNode3 = new TwoWaysNode(3.5);

        twoWaysNode1.next = twoWaysNode2;
        twoWaysNode2.next = twoWaysNode3;
        twoWaysNode3.next = null;

        twoWaysNode3.last = twoWaysNode2;
        twoWaysNode2.last = twoWaysNode1;
        twoWaysNode1.last = null;

        printTwoWaysNode(twoWaysNode1);

        twoWaysNode1 = reverseTwoWaysNode(twoWaysNode1);
        printTwoWaysNode(twoWaysNode1);

        int maxLength = 10;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            OneWayNode oneWayNode = generateOneWayNode(maxLength, maxValue);
            boolean result1 = checkGeneratedOneWayNode(oneWayNode);
            if (!result1){
                System.out.println("check1 出错啦！！！");
            }
            List<OneWayNode> oneWayNodeList = getOneWayNodeList(oneWayNode);
//            printOneWayNode(oneWayNode);
            OneWayNode reversedOneWayNode = reverseOneWayNode(oneWayNode);
            boolean result2 = checkReversedOneWayNode(oneWayNodeList, reversedOneWayNode);
            if (!result2){
                System.out.println("check2 出错啦！！！");
            }

            TwoWaysNode twoWaysNode = generateTwoWaysNode(maxLength, maxValue);
            boolean result3 = checkGeneratedTwoWaysNode(twoWaysNode);
            if (!result3){
                System.out.println("check3 出错啦！！！");
            }
            List<TwoWaysNode> twoWaysNodeList = getTwoWaysNodeList(twoWaysNode);
//            printTwoWaysNode(twoWaysNode);
            TwoWaysNode reversedTwoWaysNode = reverseTwoWaysNode(twoWaysNode);
            boolean result4 = checkReversedTwoWaysNode(twoWaysNodeList, reversedTwoWaysNode);
            if (!result4){
                System.out.println("check4 出错啦！！！");
            }
        }
        System.out.println("测试完成！！！");
    }

    // 单向链表的逆序
    public static OneWayNode reverseOneWayNode(OneWayNode head) {
        OneWayNode pre = null;
        OneWayNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 双向链表的逆序
    public static TwoWaysNode reverseTwoWaysNode(TwoWaysNode head) {
        TwoWaysNode pre = null;
        TwoWaysNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static OneWayNode generateOneWayNode(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        if (length == 0) {
            return null;
        }
        OneWayNode headNode = new OneWayNode((int) (Math.random() * maxValue));
        length--;
        OneWayNode preNode = headNode;
        while (length > 0) {
            OneWayNode nextNode = new OneWayNode((int) (Math.random() * maxValue));
            preNode.next = nextNode;
            preNode = nextNode;
            length--;
        }
        return headNode;
    }

    public static TwoWaysNode generateTwoWaysNode(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        if (length == 0) {
            return null;
        }
        TwoWaysNode headNode = new TwoWaysNode(Double.valueOf(String.format("%.1f", Math.random() * maxValue)));
        headNode.last = null;
        length--;
        TwoWaysNode preNode = headNode;
        while (length > 0) {
            TwoWaysNode nextNode = new TwoWaysNode(Double.valueOf(String.format("%.1f", Math.random() * maxValue)));
            preNode.next = nextNode;
            nextNode.last = preNode;
            preNode = nextNode;
            length--;
        }
        return headNode;
    }

    public static boolean checkGeneratedOneWayNode(OneWayNode oneWayNode){
        if (oneWayNode == null){
            return true;
        }
        List<OneWayNode> oneWayNodeList = getOneWayNodeList(oneWayNode);
        if (oneWayNode != oneWayNodeList.get(0)){
            return false;
        }
        for (int i = 0; i< oneWayNodeList.size() -1; i++){
            if (oneWayNode.next != oneWayNodeList.get(i+1)){
                return false;
            }
            oneWayNode = oneWayNode.next;
        }
        return true;
    }

    public static boolean checkGeneratedTwoWaysNode(TwoWaysNode twoWaysNode){
        if (twoWaysNode == null){
            return true;
        }
        List<TwoWaysNode> twoWaysNodeList = getTwoWaysNodeList(twoWaysNode);
        if (twoWaysNode != twoWaysNodeList.get(0)){
            return false;
        }
        for (int i = 1; i< twoWaysNodeList.size() -1; i++){
            twoWaysNode = twoWaysNode.next;
            if (twoWaysNode.next != twoWaysNodeList.get(i+1)){
                return false;
            }
            if (twoWaysNode.last != twoWaysNodeList.get(i-1)){
                return false;
            }
        }
        if (twoWaysNodeList.size() > 1){
            twoWaysNode = twoWaysNode.next;
            if (twoWaysNode != twoWaysNodeList.get(twoWaysNodeList.size()-1)){
                return false;
            }
        }
        return true;
    }

    public static List<OneWayNode> getOneWayNodeList(OneWayNode oneWayNode){
        List<OneWayNode> nodeList = new ArrayList<>();
        while (oneWayNode != null){
            nodeList.add(oneWayNode);
            oneWayNode = oneWayNode.next;
        }
        return nodeList;
    }

    public static List<TwoWaysNode> getTwoWaysNodeList(TwoWaysNode twoWaysNode){
        List<TwoWaysNode> nodeList = new ArrayList<>();
        while (twoWaysNode != null){
            nodeList.add(twoWaysNode);
            twoWaysNode = twoWaysNode.next;
        }
        return nodeList;
    }

    public static boolean checkReversedOneWayNode(List<OneWayNode> nodeList, OneWayNode reversedNode){
        for (int i = nodeList.size()-1; i >= 0; i--){
            if (nodeList.get(i) != reversedNode){
                return false;
            }
            reversedNode = reversedNode.next;
        }
        return true;
    }

    public static boolean checkReversedTwoWaysNode(List<TwoWaysNode> nodeList, TwoWaysNode reversedNode){
        for (int i = nodeList.size()-1; i >= 0; i--){
            if (nodeList.get(i) != reversedNode){
                return false;
            }
            reversedNode = reversedNode.next;
        }
        return true;
    }

    public static class OneWayNode {
        Integer value;
        OneWayNode next;

        public OneWayNode(Integer value) {
            this.value = value;
        }
    }

    public static class TwoWaysNode {
        Double value;
        TwoWaysNode next;
        TwoWaysNode last;

        public TwoWaysNode(Double value) {
            this.value = value;
        }

    }

    public static void printOneWayNode(OneWayNode node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void printTwoWaysNode(TwoWaysNode node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

}
