package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
 *
 * @author tobebetter9527
 * @create 2022/07/03 22:51
 */
public class Code03_lowestAncestor {

  public static Node lowestAncestor1(Node head, Node nodeA, Node nodeB) {
    if (head == null) {
      return null;
    }
    // 节点的父节点映射关系
    Map<Node, Node> parantMap = new HashMap<>();
    parantMap.put(head, null);
    fillParantMap(head, parantMap);

    // 寻找nodeA的祖先节点
    Set<Node> nodeAParentSet = new HashSet<>();
    nodeAParentSet.add(nodeA);
    Node cur = nodeA;
    while (parantMap.get(cur) != null) {
      cur = parantMap.get(cur);
      nodeAParentSet.add(cur);
    }

    // 依次往上找nodeB的祖先，判断是否在nodeAParentSet，如果有，那么第一个匹配到的就是A,B的最低公共祖先
    cur = nodeB;
    while (!nodeAParentSet.contains(cur)) {
      cur = parantMap.get(cur);
    }
    return cur;
  }

  private static void fillParantMap(Node head, Map<Node, Node> parantMap) {
    if (head.left != null) {
      parantMap.put(head.left, head);
      fillParantMap(head.left, parantMap);
    }
    if (head.right != null) {
      parantMap.put(head.right, head);
      fillParantMap(head.right, parantMap);
    }
  }


  public static Node lowestAncestor2(Node head, Node a, Node b) {
    return process(head, a, b).ansNode;
  }

  private static Info process(Node head, Node a, Node b) {
    if (head == null) {
      return new Info(false, false, null);
    }
    Info leftInfo = process(head.left, a, b);
    Info rightInfo = process(head.right, a, b);

    boolean findA = head == a || leftInfo.findA || rightInfo.findA;
    boolean findB = head == b || leftInfo.findB || rightInfo.findB;

    Node ansNode = null;
    if (leftInfo.ansNode != null) {
      ansNode = leftInfo.ansNode;
    } else if (rightInfo.ansNode != null) {
      ansNode = rightInfo.ansNode;
    } else {
      // 左右子树都没有答案，如果此时a和b都发现了，head节点必是最低公共祖先
      if (findA && findB) {
        ansNode = head;
      }
    }
    return new Info(findA, findB, ansNode);
  }


  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 10000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBT(maxLevel, maxValue);
      Node a = pickRandomNode(head);
      Node b = pickRandomNode(head);
      if (lowestAncestor1(head, a, b) != lowestAncestor2(head, a, b)) {
        System.out.println("Something is wrong");
      }
    }

    System.out.println("done!");
  }

  private static Node pickRandomNode(Node head) {
    if (head == null) {
      return null;
    }

    List<Node> list = new ArrayList<>();
    inOrder(head, list);
    return list.get((int) (Math.random() * list.size()));
  }

  private static void inOrder(Node head, List<Node> list) {
    if (head == null) {
      return;
    }
    inOrder(head.left, list);
    list.add(head);
    inOrder(head.right, list);
  }

  private static Node generateRandomBT(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  private static Node generate(int i, int maxLevel, int maxValue) {
    if (i > maxLevel || Math.random() > .5D) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(i + 1, maxLevel, maxValue);
    head.right = generate(i + 1, maxLevel, maxValue);
    return head;
  }

  static class Info {

    // 是否找到A节点
    boolean findA;
    // 是否找道B节点
    boolean findB;
    Node ansNode;

    public Info(boolean findA, boolean findB, Node ansNode) {
      this.findA = findA;
      this.findB = findB;
      this.ansNode = ansNode;
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
