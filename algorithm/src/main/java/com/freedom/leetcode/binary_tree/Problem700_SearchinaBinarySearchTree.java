package com.freedom.leetcode.binary_tree;

/**
 * 700. Search in a Binary Search Tree
 */
public class Problem700_SearchinaBinarySearchTree {

    /**
     * time complexity is O（n), space complexity is O(n)
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            root = root.val > val ? root.left : root.right;
        }
        return null;
    }
}
