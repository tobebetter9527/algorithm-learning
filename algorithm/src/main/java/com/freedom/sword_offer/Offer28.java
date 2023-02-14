package com.freedom.sword_offer;

import java.util.LinkedList;
import java.util.Queue;

public class Offer28 {

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return recursive(root.left, root.right);
  }

  private boolean recursive(TreeNode left, TreeNode right) {
    if (left == null && right != null) {
      return false;
    } else if (left != null && right == null) {
      return false;
    } else if (left == null && right == null) {
      return true;
    }
    return left.val == right.val && recursive(left.left, right.right) && recursive(left.right, right.left);
  }

  public boolean isSymmetric2(TreeNode root) {
    if (root == null) {
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root.left);
    queue.add(root.right);
    while (!queue.isEmpty()) {
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if (left == null && right == null) {
        continue;
      }
      if (left == null || right == null || left.val != right.val) {
        return false;
      }
      queue.add(left.left);
      queue.add(right.right);
      queue.add(left.right);
      queue.add(right.left);
    }
    return true;
  }


}
