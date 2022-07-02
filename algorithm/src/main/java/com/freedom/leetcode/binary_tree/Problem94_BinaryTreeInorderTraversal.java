package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Binary tree inorder traversal
 *
 * @author tobebetter9527
 * @create 2022/07/02 11:23
 */
public class Problem94_BinaryTreeInorderTraversal {

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new LinkedList<>();
    traversal(root,list);
    return list;
  }

  private void traversal(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }
    traversal(node.left, list);
    list.add(node.val);
    traversal(node.right, list);
  }
}
