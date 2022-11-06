package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 *
 * @author tobebetter9527
 * @create 2022/11/06 22:08
 */
public class Problem257_BinaryTreePaths {

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> list = new LinkedList<>();
    process(root, root.val + "", list);
    return list;
  }

  private void process(TreeNode root, String path, List<String> list) {
    if (root.left == null && root.right == null) {
      list.add(path);
      return;
    }
    if (root.left != null) {
      process(root.left, path + "->" + root.left.val, list);
    }
    if (root.right != null) {
      process(root.right, path + "->" + root.right.val, list);
    }
  }
}
