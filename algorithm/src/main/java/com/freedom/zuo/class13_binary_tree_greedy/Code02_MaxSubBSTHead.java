package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 *
 * @author tobebetter9527
 * @create 2022/07/03 15:51
 */
public class Code02_MaxSubBSTHead {

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getMaxSubSize(head) != 0) {
            return head;
        }

        Node leftNode = maxSubBSTHead1(head.left);
        Node rightNode = maxSubBSTHead1(head.right);

        return getMaxSubSize(leftNode) >= getMaxSubSize(rightNode) ? leftNode : rightNode;
    }

    private static int getMaxSubSize(Node head) {
        if (head == null) {
            return 0;
        }
        List<Node> list = new ArrayList<>();
        inOrder(head, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).value <= list.get(i - 1).value) {
                return 0;
            }
        }
        return list.size();
    }

    private static void inOrder(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }


    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    private static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int max = node.value;
        int min = node.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        // 可能最大BST在左右子树中，不经过node节点
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

        // 最大BST树经过node节点

        // 保证左右子树的BST树就是它们自身
        boolean flag1 = leftInfo == null ? true : (leftInfo.maxSubBSTHead == node.left && leftInfo.max < node.value);
        boolean flag2 = rightInfo == null ? true : (rightInfo.maxSubBSTHead == node.right && rightInfo.min > node.value);
        if (flag1 && flag2) {
            maxSubBSTHead = node;
            maxSubBSTSize =
                    (leftInfo != null ? leftInfo.maxSubBSTSize : 0) + (rightInfo != null ? rightInfo.maxSubBSTSize : 0) + 1;
        }

        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
                maxSubBSTHead1(head);
                maxSubBSTHead2(head);
            }
        }
        System.out.println("finish!");
    }

    static class Info {

        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(Node h, int size, int mi, int ma) {
            maxSubBSTHead = h;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }

    static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
}
