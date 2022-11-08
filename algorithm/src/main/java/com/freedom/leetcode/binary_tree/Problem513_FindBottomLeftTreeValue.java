package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 *
 * @author tobebetter9527
 * @create 2022/11/07 21:03
 */
public class Problem513_FindBottomLeftTreeValue {

  private int level;
  private int value;

  /**
   * time complexity is O(n), sapce complexity is O(n)
   * @param root
   * @return
   */
  public int findBottomLeftValue(TreeNode root) {
    process(root, 1);
    return this.value;
  }

  private void process(TreeNode root, int level) {
    // 终止条件
    if (root.left == null && root.right == null) {
      if (level > this.level) {
        this.level = level;
        this.value = root.val;
        return;
      }
    }

    // 先遍历左边，后右边
    if (root.left != null) {
      process(root.left, level + 1);
    }
    if (root.right != null) {
      process(root.right, level + 1);
    }
  }


  public int findBottomLeftValue2(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int value = root.val;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = size - 1; i >= 0; i--) {
        TreeNode poll = queue.poll();
        value = poll.val;
        if (poll.right != null) {
          queue.add(poll.right);
        }
        if (poll.left != null) {
          queue.add(poll.left);
        }
      }
    }
    return value;
  }

  /**
   * 优化版本
   * @param root
   * @return
   */
  public int findBottomLeftValue3(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int value = root.val;
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      value = poll.val;
      if (poll.right != null) {
        queue.add(poll.right);
      }
      if (poll.left != null) {
        queue.add(poll.left);
      }

    }
    return value;
  }

}
