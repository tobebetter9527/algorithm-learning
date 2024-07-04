package com.freedom.zuo.class12_binary_tree;

/**
 * 是否是平衡二叉树
 * <p>
 * 平衡二叉树：任何节点的左右节点的高度差不超过1
 *
 * @author tobebetter9527
 * @create 2022/07/02 16:41
 */
public class Code03_IsBalanced {

    public static boolean isBalanced1(Node head) {
        WrapBoolean wrapBoolean = new WrapBoolean(true);
        process1(head, wrapBoolean);
        return wrapBoolean.flag;
    }

    private static int process1(Node node, WrapBoolean wrapBoolean) {
        // !wrapBoolean.flag放前面主要目的：已经是非平衡数，那么node节点就不用再继续执行计算高度了
        if (!wrapBoolean.flag || node == null) {
            return -1;
        }
        int leftHeight = process1(node.left, wrapBoolean);
        int rightHeight = process1(node.right, wrapBoolean);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            wrapBoolean.flag = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced2(Node head) {
        return process2(head).balanced;
    }

    private static Info process2(Node node) {
        if (node == null) {
            // 如果节点为空，则默认为平衡，且高度为0
            return new Info(true, 0);
        }
        Info leftInfo = process2(node.left);
        Info rightInfo = process2(node.right);
        boolean balanced = true;
        if (!leftInfo.balanced || !rightInfo.balanced) {
            balanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            balanced = false;
        }
        return new Info(balanced, Math.max(leftInfo.height, rightInfo.height) + 1);
    }


    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBT(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Something is wrong!");
            }
        }
        System.out.println("done!");
    }

    private static Node generateRandomBT(int maxLevel, int maxValue) {
        return generate(0, maxLevel, maxValue);
    }

    private static Node generate(int i, int maxLevel, int maxValue) {
        if (i > maxLevel || Math.random() > 0.5D) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(i + 1, maxLevel, maxValue);
        head.right = generate(i + 1, maxLevel, maxValue);
        return head;
    }


    static class Info {

        boolean balanced;
        int height;

        public Info(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }


    static class WrapBoolean {

        boolean flag;

        public WrapBoolean(boolean flag) {
            this.flag = flag;
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
