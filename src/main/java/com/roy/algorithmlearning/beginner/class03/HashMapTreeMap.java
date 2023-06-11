package com.roy.algorithmlearning.beginner.class03;

import java.util.HashMap;
import java.util.TreeMap;

public class HashMapTreeMap {

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Roy", "我是Roy");
        System.out.println(hashMap.containsKey("Roy"));
        System.out.println(hashMap.containsKey("roy"));
        System.out.println(hashMap.containsKey("aaa"));
        System.out.println(hashMap.get("Roy"));

        hashMap.put("Roy", "他是Roy");
        System.out.println(hashMap.get("Roy"));
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        hashMap.remove("Roy");
        System.out.println(hashMap.containsKey("Roy"));
        System.out.println(hashMap.get("Roy"));    //HashMap的get方法时O(1)的

        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        hashMap.put("Allen", "他是Allen");
        String a = "Allen";
        String b = "Allen";
        System.out.println(hashMap.containsKey(a));
        System.out.println(hashMap.containsKey(b));

        HashMap<Integer, String> hashMap1 = new HashMap<>();
        hashMap1.put(123456, "我是Roy");
        Integer x = 123456;
        Integer y = 123456;
        System.out.println(hashMap1.containsKey(x));
        System.out.println(hashMap1.containsKey(y));   // 对于基础类型，不管是不是包装类，不管它在其他地方是值传递还是引用传递，在HashMap里面是值传递
        System.out.println("====================================");

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        HashMap<Node, String> hashMap2 = new HashMap<>();
        hashMap2.put(node1, "我是Node1");
        System.out.println(hashMap2.containsKey(node1));
        System.out.println(hashMap2.containsKey(node2));  //对于自己定义的类型，在HashMap里面是引用传递

        //由以上可以看出，如果我们用HashMap<String, String> 来放  倚天屠龙记：倚天屠龙记的内容（1M），那么这个HashMap的大小是8K + 1M
        //如果用HashMap<Node, Node>来放  new Node("倚天屠龙记") : new Node("倚天屠龙记的内容(1M)")，那么这个HashMap的大小是8K + 8K， 真正的大小是在Node对应的对象的地址;

        System.out.println("------------------------------------------------");
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "我是3");
        treeMap.put(5, "我是3");
        treeMap.put(4, "我是3");
        treeMap.put(1, "我是3");
        treeMap.put(0, "我是3");
        treeMap.put(7, "我是3");
        treeMap.put(9, "我是3");

        System.out.println(treeMap.containsKey(3));
        System.out.println(treeMap.containsKey(6));
        System.out.println(treeMap.get(3));

        treeMap.put(3, "他是3");
        System.out.println(treeMap.get(3));

        treeMap.remove(3);
        System.out.println(treeMap.get(3));   // TreeMap的get方法是O(log n)的

        System.out.println("------------------------------------------------");
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        // key <= 5, 返回离5最近的key
        System.out.println(treeMap.floorKey(5));
        // key <= 6, 返回离6最近的key
        System.out.println(treeMap.floorKey(6));
        // key >= 5, 返回离5最近的key
        System.out.println(treeMap.ceilingKey(5));
        // key >= 6, 返回离6最近的key
        System.out.println(treeMap.ceilingKey(6));

        System.out.println("------------------------------------------------");
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        TreeMap<Node, String> treeMap1 = new TreeMap<>();
        treeMap1.put(node3, "3");    //会报错，HashMapTreeMap$Node cannot be cast to class java.lang.Comparable
        treeMap1.put(node4, "4");    //排序时不知道该怎么比较，需要重写hashcode 和 equals 方法

    }

    public static class Node{
        Integer a;

        public Node(Integer a) {
            this.a = a;
        }
    }
}
