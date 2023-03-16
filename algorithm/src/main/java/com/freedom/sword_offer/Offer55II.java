package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/04 9:07
 */
public class Offer55II {

  public boolean isBalanced(TreeNode root) {
    return recursive(root) >= 0;
  }

  private int recursive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = recursive(root.left);
    int right = recursive(root.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
      return -1;
    }
    return Math.max(left, right) + 1;
  }


  public boolean isBalanced2(TreeNode root) {
    return recursive2(root).isBalanced;
  }

  private Info recursive2(TreeNode root) {
    if (root == null) {
      return new Info(true, 0);
    }
    Info left = recursive2(root.left);
    Info right = recursive2(root.right);
    boolean flag = left.isBalanced && right.isBalanced && Math.abs(left.h - right.h) <= 1;
    return new Info(flag, Math.max(left.h, right.h) + 1);
  }

  static class Info {

    boolean isBalanced;
    int h;

    public Info(boolean isBalanced, int h) {
      this.isBalanced = isBalanced;
      this.h = h;
    }
  }
}
