package com.freedom.leetcode.binary_tree;

/**
 * 701. Insert into a Binary Search Tree
 *
 * @author tobebetter9527
 * @create 2022/11/11 21:32
 */
public class Problem701_InsertintoaBinarySearchTree {

  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }
    TreeNode pre = root;
    TreeNode cur = root;
    while (cur != null) {
      pre = cur;
      if (cur.val < val) {
        cur = cur.right;
      } else {
        cur = cur.left;
      }
    }

    if (pre.val < val) {
      pre.right = new TreeNode(val);
    } else {
      pre.left = new TreeNode(val);
    }
    return root;
  }


  public TreeNode insertIntoBST1(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }
    if (root.val < val) {
      root.right = insertIntoBST(root.right, val);
    }
    if (root.val > val) {
      root.left = insertIntoBST(root.left, val);
    }
    return root;
  }


  public TreeNode insertIntoBST2(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }

    TreeNode node = getLastNode(root, val);
    if (node.val < val) {
      node.right = new TreeNode(val);
    } else {
      node.left = new TreeNode(val);
    }
    return root;
  }

  private TreeNode getLastNode(TreeNode root, int val) {
    if (root.val < val) {
      if (root.right == null) {
        return root;
      } else {
        return getLastNode(root.right, val);
      }
    } else {
      if (root.left == null) {
        return root;
      } else {
        return getLastNode(root.left, val);
      }
    }
  }


}
