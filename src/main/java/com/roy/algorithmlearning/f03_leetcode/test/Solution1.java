package com.roy.algorithmlearning.f03_leetcode.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 二叉树的层序遍历
 */
public class Solution1 {
    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode nowNode = q.peek();
            q.poll();
            resultList.add(nowNode.val);
            if (nowNode.left != null) {
                q.add(nowNode.left);
            }
            if (nowNode.right != null) {
                q.add(nowNode.right);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(15);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        ArrayList<Integer> list = PrintFromTopToBottom(root);
        list.forEach(System.out::println);
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}