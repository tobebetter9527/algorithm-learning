package com.freedom.leetcode.binary_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 538. Convert BST to Greater Tree
 *
 * @author tobebetter9527
 * @create 2022/11/12 21:48
 */
public class Problem538_ConvertBSTtoGreaterTree {

  static int sum;

  public static TreeNode convertBST(TreeNode root) {
    inTraversal(root);
    return root;
  }

  private static void inTraversal(TreeNode root) {
    if (root == null) {
      return;
    }
    inTraversal(root.right);
    sum += root.val;
    root.val = sum;
    inTraversal(root.left);
  }


  public static TreeNode convertBST2(TreeNode root) {
    List<TreeNode> list = new LinkedList<>();
    inOrder(root, list);
    int sum = 0;
    for (TreeNode node : list) {
      sum += node.val;
      node.val = sum;
    }
    return root;
  }

  private static void inOrder(TreeNode root, List<TreeNode> list) {
    if (root == null) {
      return;
    }
    inOrder(root.right, list);
    list.add(root);
    inOrder(root.left, list);
    return;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(2);
    root.right = new TreeNode(4);
    root.left.left = new TreeNode(1);
    TreeNode node = convertBST(root);
    System.out.println(node);
  }

}