package com.freedom.leetcode.binary_tree;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 */
public class Problem235_LowestCommonAncestorofaBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode maxNode = p.val > q.val ? p : q;
        TreeNode minNode = maxNode == p ? q : p;
        return process(root, maxNode.val, minNode.val);
    }

    private TreeNode process(TreeNode root, int max, int min) {
        if (root == null) {
            return null;
        }
        if (root.val > max) {
            return process(root.left, max, min);
        }
        if (root.val < min) {
            return process(root.right, max, min);
        }

        return root;
    }
}
