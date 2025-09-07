package com.roy.algorithmlearning.f02_system.class04;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 哈希表  和  有序表
 */
public class Code12_HashMapAndSortedMap {

    public static void main(String[] args) {

        HashMap<String,Integer> map = new HashMap<>();
        String s1 = new String("abc");
        String s2 = new String("abc");

        map.put(s1, 1);
        map.put(s2, 2);

        System.out.println(map.size());    // 1

        HashMap<Chen,Integer> map1 = new HashMap<>();

        Chen chen1 = new Chen("abc");
        Chen chen2 = new Chen("abc");

        map1.put(chen1, 1);
        map1.put(chen2, 2);

        System.out.println(map1.size());   // 2

        System.out.println("===============================");

        System.out.println("有序表测试开始");
        // TreeMap 有序表：接口名
        // 红黑树、avl、sb树、跳表
        // O(logN)
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(8, "我是8");
        treeMap.put(5, "我是5");
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(2, "我是2");

        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.containsKey(10));

        System.out.println(treeMap.get(4));
        System.out.println(treeMap.get(10));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println("新鲜(与HashMap不同的api)：");

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        // <= 4
        System.out.println(treeMap.floorKey(4));
        // >= 4
        System.out.println(treeMap.ceilingKey(4));
        // O(logN)






    }

    public static class Chen{
        String name;

        public Chen(String name) {
            this.name = name;
        }
    }


}
