package com.freedom.leetcode.binary_tree;


/**
 * 110. Balanced Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/06 21:39
 */
public class Problem110_BalancedBinaryTree {


  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    return process(root).isBalanced;
  }

  private Info process(TreeNode root) {
    if (root == null) {
      return new Info(true, 0);
    }

    Info left = process(root.left);
    Info right = process(root.right);
    boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.maxHeight - right.maxHeight) <= 1;
    return new Info(isBalanced, Math.max(left.maxHeight, right.maxHeight) + 1);
  }

  static class Info {

    boolean isBalanced;
    int maxHeight;

    public Info(boolean isBalanced, int h) {
      this.isBalanced = isBalanced;
      this.maxHeight = h;
    }
  }
}
