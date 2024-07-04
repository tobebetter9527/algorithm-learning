package com.freedom.leetcode.binary_tree;

/**
 * 112. Path Sum
 *
 * @author tobebetter9527
 * @create 2022/11/08 20:33
 */
public class Problem112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum - root.val);
    }

    private boolean process(TreeNode root, int targetSum) {
        // 到达叶子节点
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        if (root.left != null) {
            if (process(root.left, targetSum - root.left.val)) {
                return true;
            }
        }
        if (root.right != null) {
            if (process(root.right, targetSum - root.right.val)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum2(root.left, targetSum - root.val) || hasPathSum2(root.right, targetSum - root.val);
    }


}
