package com.freedom.zuo.class11_binary_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点
 *
 * @author tobebetter9527
 * @create 2022/06/30 22:23
 */
public class Code05_TreeMaxWidth {

  public static int maxWidthUseMap(Node head) {
    if (head == null) {
      return 0;
    }
    // 按层遍历
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    // 最大个数
    int max = 0;
    // 当前层
    int curLevel = 1;
    // 当前层节点数
    int curLevelNodes = 0;
    // 保存节点与层数关系
    Map<Node, Integer> map = new HashMap<>();
    map.put(head, curLevel);

    Node cur;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      int curNodeLevel = map.get(cur);
      if (cur.left != null) {
        map.put(cur.left, curNodeLevel + 1);
        queue.add(cur.left);
      }
      if (cur.right != null) {
        map.put(cur.right, curNodeLevel + 1);
        queue.add(cur.right);
      }

      // 说明当前层遍历美有完成
      if (curNodeLevel == curLevel) {
        curLevelNodes++;
      } else {
        // 说明上一层已经遍历完成
        curLevel++;
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 1;
      }
    }

    // 最右边节点没有更下一层比较
    return Math.max(max, curLevelNodes);
  }

  public static int maxWidthUseNoMap(Node head) {
    if (head == null) {
      return 0;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(head);

    int max = 0;
    int curLevelNodes = 0;
    // 下一层最后一个节点
    Node nextEnd = head;
    // 当前层的最后一个节点
    Node curEnd = head;
    Node cur;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      if (cur.left != null) {
        queue.add(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.add(cur.right);
        nextEnd = cur.right;
      }
      curLevelNodes++;
      // 说明当前层已经遍历完成
      if (cur == curEnd) {
        curEnd = nextEnd;
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 0;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int maxLevel = 10;
    int maxValue = 200;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBinaryTree(maxLevel, maxValue);
      int i1 = maxWidthUseMap(head);
      int i2 = maxWidthUseNoMap(head);
      System.out.println("i1:" + i1 + ",i2:" + i2);
      if (maxWidthUseMap(head) != maxWidthUseNoMap(head)) {
        System.out.println("Something is wrong!");
      }
    }
    System.out.println("Good!");
  }

  private static Node generateRandomBinaryTree(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  private static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() > 0.5D) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxLevel));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }


  private static class Node {

    int value;
    Node left;
    Node right;

    public Node(int data) {
      this.value = data;
    }
  }
}
