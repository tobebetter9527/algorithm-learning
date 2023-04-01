package com.freedom.sword_offer;

import java.util.ArrayList;
import java.util.List;

public class Offer36 {

  Node pre, head;

  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }
    dfs(root);
    head.left = pre;
    pre.right = head;
    return head;
  }

  /**
   * 中序遍历
   */
  private void dfs(Node cur) {
    if (cur == null) {
      return;
    }
    dfs(cur.left);
    if (pre != null) {
      cur.left = pre;
      pre.right = cur;
    } else {
      // pre为null，说明已经到最左边，可以作为头节点
      head = cur;
    }
    pre = cur;
    dfs(cur.right);
  }


  /**
   * 代码更简洁
   *
   * @param root
   * @return
   */
  public Node treeToDoublyList1(Node root) {
    if (root == null) {
      return null;
    }

    List<Node> list = new ArrayList<>();
    inOrder(list, root);

    int n = list.size();
    for (int i = 0; i < n; i++) {
      Node node = list.get(i);
      node.left = list.get((i - 1 + n) % n);
      node.right = list.get((i + 1) % n);
    }
    return list.get(0);
  }


  /**
   * time complexity is O(n), space complexity is O(n)
   *
   * @param root
   * @return
   */
  public Node treeToDoublyList2(Node root) {
    if (root == null) {
      return null;
    }

    List<Node> list = new ArrayList<>();
    inOrder(list, root);

    for (int i = 0; i < list.size() - 1; i++) {
      Node node = list.get(i);
      node.right = list.get(i + 1);
    }
    for (int i = list.size() - 1; i > 0; i--) {
      Node node = list.get(i);
      node.left = list.get(i - 1);
    }
    list.get(0).left = list.get(list.size() - 1);
    list.get(list.size() - 1).right = list.get(0);

    return list.get(0);
  }

  private void inOrder(List<Node> list, Node root) {
    if (root == null) {
      return;
    }
    inOrder(list, root.left);
    list.add(root);
    inOrder(list, root.right);
  }

  static class Node {

    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  }

  ;
}
