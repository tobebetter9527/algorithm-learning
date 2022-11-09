package com.freedom.leetcode.binary_tree;

/**
 * 98. Validate Binary Search Tree
 *
 * @author tobebetter9527
 * @create 2022/11/09 20:24
 */
public class Problem98_ValidateBinarySearchTree {

  public boolean isValidBST(TreeNode root) {
    return process(root).isBST;
  }

  private Info process(TreeNode root) {
    if (root == null) {
      return new Info(true, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    Info left = process(root.left);
    Info right = process(root.right);
    boolean isBST = left.isBST && right.isBST && root.val > left.max && root.val < right.min;
    long max = Math.max(Math.max(root.val, left.max), right.max);
    long min = Math.min(Math.min(root.val, left.min), right.min);
    return new Info(isBST, max, min);
  }

  static class Info {

    boolean isBST;
    long max;
    long min;

    public Info(boolean isBST, long max, long min) {
      this.isBST = isBST;
      this.max = max;
      this.min = min;
    }
  }


  /**
   * 更好的方法
   * <p>
   * time complexity is O(n),space complexity is O(n)
   *
   * @param root
   * @return
   */
  public boolean isValidBST2(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isValidBST(TreeNode root, long minValue, long maxValue) {
    if (root == null) {
      return true;
    }
    if (root.val >= maxValue || root.val <= minValue) {
      return false;
    }
    return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
  }

}
