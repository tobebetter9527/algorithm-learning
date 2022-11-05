package com.freedom.leetcode.binary_tree;

/**
 * 226. Invert Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/05 20:35
 */
public class Problem226_InvertBinaryTree {

  /**
   * time complexity is O(n), sapce complexity is O(logn).
   *
   * @param root
   * @return
   */
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    invertTree(root.left);
    invertTree(root.right);
    return root;
  }



}
