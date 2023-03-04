package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/03 22:45
 */
public class Offer55I {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
  }
}
