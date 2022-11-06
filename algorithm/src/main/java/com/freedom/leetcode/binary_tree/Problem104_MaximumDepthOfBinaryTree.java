package com.freedom.leetcode.binary_tree;

/**
 * 104. Maximum Depth of Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/06 15:47
 */
public class Problem104_MaximumDepthOfBinaryTree {


  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
  }

}
