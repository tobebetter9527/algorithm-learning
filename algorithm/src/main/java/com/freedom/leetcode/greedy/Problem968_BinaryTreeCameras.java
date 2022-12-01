package com.freedom.leetcode.greedy;


import com.freedom.leetcode.binary_tree.TreeNode;

/**
 * 968. Binary Tree Cameras
 *
 * @author tobebetter9527
 * @create 2022/12/01 20:19
 */
public class Problem968_BinaryTreeCameras {

  int count;

  public int minCameraCover(TreeNode root) {
    // 有可能根节点左右为空
    if (procees(root) == 0) {
      count++;
    }
    return count;
  }

  /**
   * 0无覆盖，1放监控，2有覆盖
   *
   * @param root
   * @return
   */
  private int procees(TreeNode root) {
    if (root == null) {
      // 空节点，默认覆盖
      return 2;
    }
    int left = procees(root.left);
    int right = procees(root.right);
    // 如果都覆盖，当前节点就是没有覆盖
    if (left == 2 && right == 2) {
      return 0;
    } else if (left == 0 || right == 0) {
      // 如果左右有没覆盖的，当前节点需要放监控
      count++;
      return 1;
    } else {
      // 左右有一个监控
      return 2;
    }
  }

}
