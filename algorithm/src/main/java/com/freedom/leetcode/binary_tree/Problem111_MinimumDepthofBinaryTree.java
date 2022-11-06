package com.freedom.leetcode.binary_tree;

/**
 * 111. Minimum Depth of Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/06 16:10
 */
public class Problem111_MinimumDepthofBinaryTree {


  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left != 0 && right != 0) {
      return Math.min(left, right) + 1;
    } else if (left == 0) {
      return right + 1;
    } else if (right == 0) {
      return left + 1;
    } else {
      return 1;
    }
  }

  public int minDepth3(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left != 0 && right != 0) {
      return Math.min(left, right) + 1;
    }
    if (left == 0) {
      return right + 1;
    }
    if (right == 0) {
      return left + 1;
    }
    return 1;
  }

  public int minDepth2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root);
  }

  private int process(TreeNode root) {
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

}
