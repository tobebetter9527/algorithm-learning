package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tobebetter9527
 * @create 2023/03/26 8:25
 */
public class Offer35 {

  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    Node cur = head;
    while (cur != null) {
      Node copyCur = new Node(cur.val);
      copyCur.next = cur.next;
      cur.next = copyCur;
      cur = copyCur.next;
    }

    cur = head;
    while (cur != null) {
      Node random = cur.random;
      Node copyCur = cur.next;
      if (random != null) {
        copyCur.random = random.next;
      }
      cur = copyCur.next;
    }

    cur = head;
    Node copyHead = head.next;
    Node copyCur = copyHead;
    while (cur != null) {
      Node next = copyCur.next;
      cur.next = next;
      cur = cur.next;
      if (next != null) {
        copyCur.next = next.next;
      }
      copyCur = copyCur.next;
    }
    return copyHead;
  }

  /**
   * time complexity is O(n), space complexity O(n)
   *
   * @param head
   * @return
   */
  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }

    Map<Node, Node> map = new HashMap<>();
    // 1.build parent child relationship
    Node cur = head;
    while (cur != null) {
      Node node = new Node(cur.val);
      map.put(cur, node);
      cur = cur.next;
    }

    // 2.copy relationship
    Node copyHead = map.get(head);
    Node copyCur = copyHead;
    cur = head;
    while (cur != null) {
      Node copyNext = map.getOrDefault(cur.next, null);
      Node copyRandom = map.getOrDefault(cur.random, null);
      copyCur.next = copyNext;
      copyCur.random = copyRandom;
      copyCur = copyNext;
      cur = cur.next;
    }

    return copyHead;
  }


  static class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
}
