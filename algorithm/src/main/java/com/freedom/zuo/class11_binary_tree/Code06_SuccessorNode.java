package com.freedom.zuo.class11_binary_tree;

/**
 * 获得节点的后继节点
 * <p>
 * 中序遍历
 *
 * @author tobebetter9527
 * @create 2022/07/02 10:05
 */
public class Code06_SuccessorNode {


  public static Node getSuccessorNode(Node node) {
    if (node == null) {
      return null;
    }
    // 如果节点的右孩子不为空，则找右孩子底下最左的孩子
    if (node.right != null) {
      node = node.right;
      while (node.left != null) {
        node = node.left;
      }
      return node;
    } else {
      Node parant = node.parent;
      // 如果节点的父节点存在，并且节点是父节点的右孩子，继续往上找，直到节点是父节点的左孩子；如果是父节点的左孩子，直接返回父节点
      while (parant != null && parant.right == node) {
        node = parant;
        parant = node.parent;
      }
      return parant;
    }
  }

  public static void main(String[] args) {
    Node head = new Node(6);
    head.parent = null;
    head.left = new Node(3);
    head.left.parent = head;
    head.left.left = new Node(1);
    head.left.left.parent = head.left;
    head.left.left.right = new Node(2);
    head.left.left.right.parent = head.left.left;
    head.left.right = new Node(4);
    head.left.right.parent = head.left;
    head.left.right.right = new Node(5);
    head.left.right.right.parent = head.left.right;
    head.right = new Node(9);
    head.right.parent = head;
    head.right.left = new Node(8);
    head.right.left.parent = head.right;
    head.right.left.left = new Node(7);
    head.right.left.left.parent = head.right.left;
    head.right.right = new Node(10);
    head.right.right.parent = head.right;

    Node test = head.left.left;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.left.left.right;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.left;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.left.right;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.left.right.right;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.right.left.left;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.right.left;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.right;
    System.out.println(test.value + " next: " + getSuccessorNode(test).value);
    test = head.right.right; // 10's next is null
    System.out.println(test.value + " next: " + getSuccessorNode(test));
  }

  private static class Node {

    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int data) {
      this.value = data;
    }
  }
}
