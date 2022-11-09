package com.freedom.leetcode.binary_tree;

/**
 * 617. Merge Two Binary Trees
 */
public class Problem617_MergeTwoBinaryTrees {

  public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return null;
    }
    if (root1 != null && root2 == null) {
      return root1;
    }
    if (root1 == null && root2 != null) {
      return root2;
    }

    TreeNode root = new TreeNode(root1.val + root2.val);
    root.left = mergeTrees(root1.left, root2.left);
    root.right = mergeTrees(root1.right, root2.right);
    return root;
  }
}
