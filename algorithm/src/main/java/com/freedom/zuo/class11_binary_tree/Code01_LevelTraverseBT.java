package com.freedom.zuo.class11_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历
 *
 * @author tobebetter9527
 * @create 2022/06/28 20:37
 */
public class Code01_LevelTraverseBT {


    public static void levelTraverse(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.val + "->");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraverse(head);
    }


    private static class Node {

        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

}
