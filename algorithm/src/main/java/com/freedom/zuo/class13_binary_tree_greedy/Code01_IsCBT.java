package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是否完成二叉树
 *
 * @author tobebetter9527
 * @create 2022/07/03 15:13
 */
public class Code01_IsCBT {

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }

        // 按层遍历
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 是否碰到双节点不全
        boolean isLeaf = false;
        Node left;
        Node right;
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            left = cur.left;
            right = cur.right;
            // 当前节点无左孩子，却有右孩子
            if ((left == null && right != null)
                    // 碰到上个节点的双节点不全，同时当前的节点不是叶子节点，这肯定不是完全二叉树
                    || (isLeaf && (left != null || right != null))) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }

            if (left == null || right == null) {
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 递归套路
     *
     * @param head
     * @return
     */
    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private static Info process(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isFull = false;
        boolean isCBT = false;

        int leftHeight = leftInfo.height;
        int rightHeight = rightInfo.height;
        // 如果左右子树都满，并且高度相等，即是满二叉树，又是完成二叉树
        if (leftInfo.isFull && rightInfo.isFull && leftHeight == rightHeight) {
            isFull = true;
            isCBT = true;
            // 左完全，右满，左高=右高+1
        } else if (leftInfo.isCBT && rightInfo.isFull && leftHeight == rightHeight + 1) {
            isCBT = true;
            // 左满，右满，左高=右高+1
        } else if (leftInfo.isFull && rightInfo.isFull && leftHeight == rightHeight + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftHeight == rightHeight) {
            isCBT = true;
        }

        return new Info(isFull, isCBT, Math.max(leftHeight, rightHeight) + 1);
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
            if (isCBT(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    static class Info {

        // 当前是否为满二叉树
        boolean isFull;
        // 是否为完全二叉树
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
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
