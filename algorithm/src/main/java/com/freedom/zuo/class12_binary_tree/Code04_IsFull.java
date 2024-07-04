package com.freedom.zuo.class12_binary_tree;

/**
 * 判断二叉树是否为满二叉树
 *
 * @author tobebetter9527
 * @create 2022/07/03 9:50
 */
public class Code04_IsFull {

    /**
     * 2^h - 1 = n
     *
     * @param head
     * @return
     */
    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        Info1 info1 = process1(head);
        return (1 << info1.height) - 1 == info1.nodes;
    }

    private static Info1 process1(Node node) {
        if (node == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(node.left);
        Info1 rightInfo = process1(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }

    /**
     * @param head
     * @return
     */
    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }

    private static Info2 process2(Node node) {
        if (node == null) {
            return new Info2(0, true);
        }
        Info2 leftInfo = process2(node.left);
        Info2 rightInfo = process2(node.right);
//    boolean isFull = true;
//    if (!leftInfo.isFull || !rightInfo.isFull) {
//      isFull = false;
//    }
//    if (leftInfo.height != rightInfo.height) {
//      isFull = false;
//    }
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info2(height, isFull);
    }

    public static void main(String[] args) {
        int maxLevel = 7;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBT(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Something is wrong！");
            }
        }
        System.out.println("done!");
    }

    private static Node generateRandomBT(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int i, int maxLevel, int maxValue) {
        if (i > maxLevel || Math.random() > 0.5D) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(i + 1, maxLevel, maxLevel);
        head.right = generate(i + 1, maxLevel, maxValue);
        return head;
    }


    static class Info2 {

        int height;
        boolean isFull;

        public Info2(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }


    static class Info1 {

        int height;
        int nodes;

        public Info1(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
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
