package com.freedom.leetcode.binary_tree;

/**
 * 450. Delete Node in a BST
 *
 * @author tobebetter9527
 * @create 2022/11/11 22:53
 */
public class Problem450_DeleteNodeinaBST {

  /**
   * time complexity is O(n), space is complexity is O(n)
   *
   * @param root
   * @param key
   * @return
   */
  public static TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val < key) {
      root.right = deleteNode(root.right, key);
    } else if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else {
      if (root.left == null && root.right == null) {
        root = null;
      } else if (root.left != null && root.right == null) {
        root = root.left;
      } else if (root.left == null && root.right != null) {
        root = root.right;
      } else {
        TreeNode cur = root.right;
        while (cur.left != null) {
          cur = cur.left;
        }

        root.right = deleteNode(root.right, cur.val);

        cur.left = root.left;
        cur.right = root.right;
        root = cur;
      }
    }

    return root;
  }

  /**
   * 优化版本
   *
   * @param root
   * @param key
   * @return
   */
  public static TreeNode deleteNode2(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val < key) {
      root.right = deleteNode2(root.right, key);
    } else if (root.val > key) {
      root.left = deleteNode2(root.left, key);
    } else {
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }

      TreeNode cur = root.right;
      while (cur.left != null) {
        cur = cur.left;
      }

      root.right = deleteNode2(root.right, cur.val);

      cur.left = root.left;
      cur.right = root.right;
      root = cur;
    }
    return root;
  }



  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(6);
    TreeNode node3 = new TreeNode(2);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(7);
    root.left = node1;
    root.right = node2;
    node1.left = node3;
    node1.right = node4;
    node2.right = node5;
    TreeNode node = deleteNode(root, 3);
    System.out.println(node.val);

  }

}
