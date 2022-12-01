package com.freedom.leetcode.binary_tree;

/**
 * 二叉树树节点
 *
 * @author tobebetter9527
 * @create 2022/07/02 11:25
 */
public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
