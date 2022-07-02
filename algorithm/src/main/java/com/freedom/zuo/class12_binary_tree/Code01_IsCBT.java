package com.freedom.zuo.class12_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是否完成二叉树
 *
 * @author tobebetter9527
 * @create 2022/07/02 15:31
 */
public class Code01_IsCBT {

  public static boolean isCBT(Node head) {
    if (head == null) {
      return true;
    }

    // 按层遍历
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    // 是否碰到双节点不全
    boolean isLeaf = false;
    Node left;
    Node right;
    Node cur;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      left = cur.left;
      right = cur.right;
      // 当前节点无左孩子，却有右孩子
      if ((left == null && right != null)
          // 碰到上个节点的双节点不全，同时当前的节点不是叶子节点，这肯定不是完全二叉树
          || (isLeaf && (left != null || right != null))) {
        return false;
      }
      if (left != null) {
        queue.add(left);
      }
      if (right != null) {
        queue.add(right);
      }

      if (left == null || right == null) {
        isLeaf = true;
      }
    }
    return true;
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
