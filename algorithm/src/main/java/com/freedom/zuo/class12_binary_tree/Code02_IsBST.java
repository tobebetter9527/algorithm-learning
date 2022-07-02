package com.freedom.zuo.class12_binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断是否为二叉搜索树
 *
 * @author tobebetter9527
 * @create 2022/07/02 17:59
 */
public class Code02_IsBST {

  /**
   * 利用中序遍历
   *
   * @param head
   * @return
   */
  public static boolean isBST1(Node head) {
    if (head == null) {
      return true;
    }
    List<Node> list = new ArrayList<>();
    inOrder(head, list);
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i).value <= list.get(i - 1).value) {
        return false;
      }
    }
    return true;
  }

  private static void inOrder(Node head, List<Node> list) {
    if (head == null) {
      return;
    }
    inOrder(head.left, list);
    list.add(head);
    inOrder(head.right, list);
  }


  public static boolean isBST2(Node head) {
    if (head == null) {
      return true;
    }
    return process(head).isBST;
  }

  private static Info process(Node head) {
    if (head == null) {
      return null;
    }
    Info leftInfo = process(head.left);
    Info rightInfo = process(head.right);

    boolean isBST = true;
    if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= head.value)) {
      isBST = false;
    }
    if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= head.value)) {
      isBST = false;
    }

    // 求最大值和最小值
    int max = head.value;
    int min = head.value;
    if (leftInfo != null) {
      max = Math.max(max, leftInfo.max);
      min = Math.min(min, leftInfo.min);
    }
    if (rightInfo != null) {
      max = Math.max(max, rightInfo.max);
      min = Math.min(min, rightInfo.min);
    }

    return new Info(max, min, isBST);
  }


  public static void main(String[] args) {
    int maxLevel = 10;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBT(maxLevel, maxValue);
      if (isBST1(head) != isBST2(head)) {
        System.out.println("Something is wrong!");
      }
    }
    System.out.println("done!");
  }

  private static Node generateRandomBT(int maxLevel, int maxValue) {
    return generate(0, maxLevel, maxValue);
  }

  private static Node generate(int i, int maxLevel, int maxValue) {
    if (i > maxLevel || Math.random() > 0.5D) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(i + 1, maxLevel, maxValue);
    head.right = generate(i + 1, maxLevel, maxValue);
    return head;
  }

  static class Info {

    int max;
    int min;
    boolean isBST;

    public Info(int max, int min, boolean isBST) {
      this.max = max;
      this.min = min;
      this.isBST = isBST;
    }
  }

  static class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }
}
