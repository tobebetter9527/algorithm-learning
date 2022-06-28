package com.freedom.zuo.class10_binary_tree;

public class Code02_RecursiveTraversalBT {

  /**
   * 递归序
   *
   * @param node
   */
  public static void traverse(Node node) {
    if (node == null) {
      return;
    }
    // 先序
    traverse(node.left);
    // 中序
    traverse(node.right);
    // 后序
  }

  /**
   * 先序遍历
   *
   * @param node
   */
  public static void preTraverse(Node node) {
    if (node == null) {
      return;
    }
    System.out.print(node.val + "->");
    preTraverse(node.left);
    preTraverse(node.right);
  }

  /**
   * 中序遍历
   *
   * @param node
   */
  public static void inTraverse(Node node) {
    if (node == null) {
      return;
    }
    inTraverse(node.left);
    System.out.print(node.val + "->");
    inTraverse(node.right);
  }

  /**
   * 后序遍历
   *
   * @param node
   */
  public static void postTraverse(Node node) {
    if (node == null) {
      return;
    }
    postTraverse(node.left);
    postTraverse(node.right);
    System.out.print(node.val + "->");
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);

    preTraverse(head);
    System.out.println("========");
    inTraverse(head);
    System.out.println("========");
    postTraverse(head);
    System.out.println("========");

  }

  private static class Node {

    int val;
    Node left;
    Node right;

    public Node(int val) {
      this.val = val;
    }
  }
}
