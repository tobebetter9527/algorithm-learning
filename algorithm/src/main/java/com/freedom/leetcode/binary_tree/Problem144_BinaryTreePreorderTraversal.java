package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Binary tree preorder traversal
 *
 * @author tobebetter9527
 * @create 2022/07/02 11:44
 */
public class Problem144_BinaryTreePreorderTraversal {

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new LinkedList<>();
    return traversal(root, list);
  }

  private List<Integer> traversal(TreeNode root, List<Integer> list) {
    if (root == null) {
      return list;
    }
    list.add(root.val);
    traversal(root.left, list);
    traversal(root.right, list);
    return list;
  }

}
