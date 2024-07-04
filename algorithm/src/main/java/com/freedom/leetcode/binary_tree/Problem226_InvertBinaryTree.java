package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. Invert Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/05 20:35
 */
public class Problem226_InvertBinaryTree {

    /**
     * time complexity is O(n), sapce complexity is O(logn).
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }

}
