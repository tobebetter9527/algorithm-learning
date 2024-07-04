package com.freedom.leetcode.binary_tree;

/**
 * 669. Trim a Binary Search Tree
 *
 * @author tobebetter9527
 * @create 2022/11/12 11:32
 */
public class Problem669_TrimaBinarySearchTree {

    /**
     * time complexity is O(n), space complextiy is O(n）.
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (low <= root.val && root.val <= high) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        } else if (root.val < low) {
            root = trimBST(root.right, low, high);
        } else {
            root = trimBST(root.left, low, high);
        }

        return root;
    }

}
