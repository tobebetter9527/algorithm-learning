package com.freedom.zuo.class_binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
 *
 * @author tobebetter9527
 * @create 2022/06/29 22:08
 */
public class Code03_EncodeNaryTreeToBinaryTree {

  /**
   * 多叉树变成二叉树
   *
   * @param root
   * @return
   */
  public TreeNode encode(Node root) {
    if (root == null) {
      return null;
    }
    TreeNode head = new TreeNode(root.val);
    head.left = en(root.children);
    return head;
  }

  private TreeNode en(List<Node> children) {
    TreeNode head = null;
    TreeNode cur = null;
    for (Node child : children) {
      if (head == null) {
        head = new TreeNode(child.val);
        cur = head;
      } else {
        cur.right = new TreeNode(child.val);
        cur = cur.right;
      }
      cur.left = en(child.children);
    }
    return head;
  }


  /**
   * 二叉树变回多叉树
   *
   * @param root
   * @return
   */
  public Node decode(TreeNode root) {
    if (root == null) {
      return null;
    }
    Node head = new Node(root.val);
    head.children = de(root.left);
    return head;
  }

  private List<Node> de(TreeNode treeNode) {
    List<Node> children = new ArrayList<>();
    while (treeNode != null) {
      Node node = new Node(treeNode.val);
      node.children = de(treeNode.left);
      children.add(node);
      treeNode = treeNode.right;
    }
    return children;
  }


  private static class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, List<Node> children) {
      this.val = val;
      this.children = children;
    }
  }

  private static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      this.val = x;
    }
  }
}
