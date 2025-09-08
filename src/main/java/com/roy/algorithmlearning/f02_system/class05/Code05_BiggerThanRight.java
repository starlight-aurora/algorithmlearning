package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.*;

/**
 * 逆序对问题， 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为逆序对
 *
 * 返回所有的逆序对
 *
 * [1,2,3,2,2,1] -> (2,1), (3,2), (3,2), (3,1), (2,1), (2,1)
 * 其实就是找右侧比左侧的数小这种情况出现了多少次
 *
 * merge方法指针从后往前，先拿较大的数
 * merge过程中，移动右组的数不产生逆序对，移动左组的数时，右组剩下的的每个数都生成
 */
public class Code05_BiggerThanRight {

    public static List<List<Integer>> getBiggerThanRight(int[] arr){
        if(arr==null||arr.length < 2){
            return new ArrayList<>();
        }
        return process(arr, 0, arr.length-1);
    }

    public static List<List<Integer>> process(int[] arr, int start, int end){
        if (start == end) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        int mid = start + ((end - start) >> 1);
        List<List<Integer>> list1 = process(arr, start, mid);
        if (list1 != null) {
            list.addAll(list1);
        }
        List<List<Integer>> list2 = process(arr, mid + 1, end);
        if (list2 != null) {
            list.addAll(list2);
        }
        list.addAll(merge(arr, start, mid, end));
        return list;
    }

    public static List<List<Integer>> merge(int[] arr, int start, int mid, int end){
        int[] help =  new int[end-start+1];
        int helpIndex = help.length-1;

        int lPoint = mid;
        int rPoint = end;
        List<List<Integer>> list = new ArrayList<>();
        while (lPoint >= start && rPoint >= mid+1){
            if (arr[lPoint] > arr[rPoint]){
                for (int i= mid+1; i <= rPoint; i++){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[lPoint]);
                    temp.add(arr[i]);
                    list.add(temp);
                }
                help[helpIndex--] = arr[lPoint--];
            } else {
                help[helpIndex--] = arr[rPoint--];
            }
        }
        while (lPoint >= start){
            help[helpIndex--] = arr[lPoint--];
        }
        while (rPoint >= mid+1){
            help[helpIndex--] = arr[rPoint--];
        }
        System.arraycopy(help,0,arr,start,help.length);
        return list;
    }

    public static List<List<Integer>> getBiggerThanRight1(int[] arr){
        List<List<Integer>> list = new ArrayList<>();
        if(arr==null||arr.length < 2){
            return new ArrayList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[i]);
                    temp.add(arr[j]);
                    list.add(temp);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            int [] arr = ArrayUtil.generateArray(10, 100);
            int [] copiedArr = Arrays.copyOf(arr, arr.length);
            Comparator<List<Integer>> comparator = (list1, list2) -> {
                int index = 0;
                for (int j = 0; j < 2; j++) {
                    Integer i1 = list1.get(index);
                    Integer i2 = list2.get(index);
                    if (!Objects.equals(i1, i2)) {
                        return i1.compareTo(i2);
                    }
                    index++;
                }
                return 0;
            };
            List<List<Integer>> biggerThanRight = getBiggerThanRight(arr);
            biggerThanRight.sort(comparator);
            List<List<Integer>> biggerThanRight1 = getBiggerThanRight1(copiedArr);
            biggerThanRight1.sort(comparator);

            if (biggerThanRight.size() != biggerThanRight1.size()) {
                System.out.println("出错啦！");
                System.out.println("数组是： " );
                ArrayUtil.printArray(copiedArr);
                System.out.println("我的方法集合：" + biggerThanRight);
                System.out.println("验证方法集合：" + biggerThanRight1);
                break;
            } else {
                for (int j=0; j<biggerThanRight.size(); j++) {
                    List<Integer> temp = biggerThanRight.get(j);
                    List<Integer> temp1 = biggerThanRight1.get(j);
                    for (int k = 0; k < temp.size(); k++) {
                        int num = temp.get(k);
                        int num1 = temp1.get(k);
                        if (num != num1) {
                            System.out.println("出错啦!!");
                            System.out.println("数组是： " );
                            ArrayUtil.printArray(copiedArr);
                            System.out.println("我的方法集合：" + biggerThanRight);
                            System.out.println("验证方法集合：" + biggerThanRight1);
                            break;
                        }
                    }
                }
            }

        }
        System.out.println("花费时间（ms）：" + (System.currentTimeMillis() - mills));
    }

}
