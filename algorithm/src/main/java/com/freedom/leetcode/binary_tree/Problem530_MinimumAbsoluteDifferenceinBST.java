package com.freedom.leetcode.binary_tree;

/**
 * 530. Minimum Absolute Difference in BST
 */
public class Problem530_MinimumAbsoluteDifferenceinBST {

    TreeNode pre;
    int ans;

    /**
     * 利用中序遍历
     * <p>
     * time complexity is O(n), space complexity is O(n)
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        inTraverval(root);
        return ans;
    }

    private void inTraverval(TreeNode root) {
        if (root == null) {
            return;
        }
        inTraverval(root.left);

        if (pre != null) {
            ans = Math.min(root.val - pre.val, ans);
        }
        pre = root;

        inTraverval(root.right);
    }


    /**
     * time complexity is O(n^2), space complexity is O(n)
     * <p>
     * 虽然正确，但是是比较糟糕的方式
     *
     * @param root
     * @return
     */
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, root.val - getFarRightValue(root.left));
            min = Math.min(min, getMinimumDifference2(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, getFarLeftValue(root.right) - root.val);
            min = Math.min(min, getMinimumDifference2(root.right));
        }
        return min;
    }

    private int getFarRightValue(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    private int getFarLeftValue(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

}
