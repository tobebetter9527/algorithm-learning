package com.freedom.leetcode.binary_tree;

/**
 * 222. Count Complete Tree Nodes
 *
 * @author tobebetter9527
 * @create 2022/11/06 17:03
 */
public class Problem222_CountCompleteTreeNodes {

    /**
     * time complexity is O(n), space complexity is O(height).
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }


    /**
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) + countNodes2(root.right);
        } else {
            return (1 << rightDepth) + countNodes2(root.left);
        }
    }

    private int getDepth(TreeNode left) {
        int depth = 0;
        while (left != null) {
            left = left.left;
            depth++;
        }
        return depth;
    }

}
