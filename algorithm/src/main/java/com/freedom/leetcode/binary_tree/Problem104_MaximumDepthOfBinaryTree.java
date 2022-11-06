package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. Maximum Depth of Binary Tree
 *
 * @author tobebetter9527
 * @create 2022/11/06 15:47
 */
public class Problem104_MaximumDepthOfBinaryTree {

  /**
   * time complexity is O(n), space complexity is O(height).
   *
   * @param root
   * @return
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
  }


  public int maxDepth2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int ans = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        size--;
      }
      ans++;
    }
    return ans;
  }

}
