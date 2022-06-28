package com.freedom.zuo.class10_binary_tree;

import java.util.Stack;

public class Code03_UnRecursiveTraversalBT {


  public static void preTraverse(Node root) {
    if (root == null) {
      return;
    }
    // 先放入根节点
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    // 先压入右子树，再压入左子树
    while (!stack.isEmpty()) {
      Node pop = stack.pop();
      System.out.print(pop.val + "->");
      if (pop.right != null) {
        stack.push(pop.right);
      }
      if (pop.left != null) {
        stack.push(pop.left);
      }
    }
  }

  public static void inTraverse(Node root) {
    if (root == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node cur = root;
    while (!stack.isEmpty() || cur != null) {
      if (cur != null) {
        stack.push(cur);
        cur = cur.left;
      } else {
        cur = stack.pop();
        System.out.print(cur.val + "->");
        cur = cur.right;
      }
    }
  }

  public static void postTraverse(Node root) {
    if (root == null) {
      return;
    }
    Node cur = root;

    Stack<Node> s1 = new Stack<>();
    Stack<Node> s2 = new Stack<>();
    s1.push(cur);
    while (!s1.isEmpty()) {
      cur = s1.pop();
      s2.push(cur);
      if (cur.left != null) {
        s1.push(cur.left);
      }
      if (cur.right != null) {
        s1.push(cur.right);
      }
    }
    while (!s2.isEmpty()) {
      System.out.print(s2.pop().val + "->");
    }
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
