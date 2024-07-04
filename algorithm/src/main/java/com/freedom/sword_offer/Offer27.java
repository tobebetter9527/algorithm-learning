package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/13 20:38
 */
public class Offer27 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root != null) {
            TreeNode left = mirrorTree(root.left);
            TreeNode right = mirrorTree(root.right);
            root.left = right;
            root.right = left;
        }
        return root;
    }
}
