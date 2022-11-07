package com.freedom.leetcode.binary_tree;

/**
 * 404. Sum of Left Leaves
 */
public class Problem404_SumOfLeftLeaves {

  /**
   * time complexity is O(n), space complexity is O(n) worst。
   *
   * @param root
   * @return
   */
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root.left, root.right);
  }

  private int process(TreeNode left, TreeNode right) {
    int sum = 0;
    if (left != null) {
      if (left.left == null && left.right == null) {
        sum += left.val;
      } else {
        sum += process(left.left, left.right);
      }
    }

    if (right != null) {
      sum += process(right.left, right.right);
    }
    return sum;
  }

}
