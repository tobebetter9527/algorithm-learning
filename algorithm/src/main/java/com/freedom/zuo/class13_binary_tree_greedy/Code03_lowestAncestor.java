package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.HashMap;
import java.util.HashSet;
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


  static class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }
}
