package com.freedom.zuo.class30_morris;

/**
 * 最小深度
 *
 * @author tobebetter9527
 * @create 2022/08/06 15:56
 */
public class Code02_MinDepth {

    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    private static int process(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }

        int p1 = Integer.MAX_VALUE;
        if (root.left != null) {
            p1 = process(root.left);
        }

        int p2 = Integer.MAX_VALUE;
        if (root.right != null) {
            p2 = process(root.right);
        }

        return 1 + Math.min(p1, p2);
    }

    // ------------------------------------------------------------------------ //

    public static int minDepth2(TreeNode root) {

        return 0;
    }

    // ------------------------------------------------------------------------ //

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
