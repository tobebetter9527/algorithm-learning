package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. Symmetric Tree
 *
 * @author tobebetter9527
 * @create 2022/11/06 9:53
 */
public class Problem101_SymmetricTree {

    /**
     * time complexity is O(n),space complexity is O(n)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        }

        return left.val == right.val && compare(left.left, right.right) && compare(left.right, right.left);
    }


    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }


}
