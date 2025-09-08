package com.roy.algorithmlearning.f02_system.class05;

import com.roy.algorithmlearning.util.ArrayUtil;

import java.util.*;

/**
 * 二倍逆序对问题， 能找出多少二倍逆序对
 * <p>
 * 给定一个数组，如果前面一个数比数组右侧任何位置的数的两倍还要大， 则这两个数可以构成一个二倍逆序对。
 * 返回这个数组中一共可以找到多少个二倍逆序对
 * <p>
 * [1,2,3,2,2,1] -> (3,1) -> 返回 1
 * 其实就是找 右侧数的两倍 比 左侧的数 还要小   这种情况出现了多少次
 */
public class Code06_BiggerThanRightTwice {

    public static List<List<Integer>> getBiggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new ArrayList<>();
        }
        return process(arr, 0, arr.length - 1);
    }

    public static List<List<Integer>> process(int[] arr, int start, int end) {
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

    public static List<List<Integer>> merge(int[] arr, int start, int mid, int end) {

        List<List<Integer>> list = new ArrayList<>();
        for (int i = mid + 1; i <= end; i++) {
            for (int j = start; j <= mid; j++) {
                if ((long) arr[j] > (long) arr[i] * 2) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[j]);
                    temp.add(arr[i]);
                    list.add(temp);
                }
            }
        }

        int[] help = new int[end - start + 1];
        int helpIndex = 0;

        int lPoint = start;
        int rPoint = mid + 1;

        while (lPoint <= mid && rPoint <= end) {
            help[helpIndex++] += arr[lPoint] <= arr[rPoint] ? arr[lPoint++] : arr[rPoint++];
        }
        while (lPoint <= mid) {
            help[helpIndex++] = arr[lPoint++];
        }
        while (rPoint <= end) {
            help[helpIndex++] = arr[rPoint++];
        }
        System.arraycopy(help, 0, arr, start, help.length);

        return list;
    }

    public static List<List<Integer>> getBiggerThanRightTwice1(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return new ArrayList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] * 2) {
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
            int[] arr = ArrayUtil.generateArray(10, 100);
            int[] copiedArr = Arrays.copyOf(arr, arr.length);
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
            List<List<Integer>> biggerThanRight = getBiggerThanRightTwice(arr);
            biggerThanRight.sort(comparator);
            List<List<Integer>> biggerThanRight1 = getBiggerThanRightTwice1(copiedArr);
            biggerThanRight1.sort(comparator);

            if (biggerThanRight.size() != biggerThanRight1.size()) {
                System.out.println("出错啦！");
                System.out.println("数组是： ");
                ArrayUtil.printArray(copiedArr);
                System.out.println("我的方法集合：" + biggerThanRight);
                System.out.println("验证方法集合：" + biggerThanRight1);
                break;
            } else {
                for (int j = 0; j < biggerThanRight.size(); j++) {
                    List<Integer> temp = biggerThanRight.get(j);
                    List<Integer> temp1 = biggerThanRight1.get(j);
                    for (int k = 0; k < temp.size(); k++) {
                        int num = temp.get(k);
                        int num1 = temp1.get(k);
                        if (num != num1) {
                            System.out.println("出错啦!!");
                            System.out.println("数组是： ");
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
