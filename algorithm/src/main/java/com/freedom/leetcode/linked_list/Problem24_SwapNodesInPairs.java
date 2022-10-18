package com.freedom.leetcode.linked_list;

/**
 * 24. Swap Nodes in Pairs
 *
 * @author tobebetter9527
 * @create 2022/10/17 21:08
 */
public class Problem24_SwapNodesInPairs {

  /**
   * time complexity is O(n), space complexity is O(1).
   *
   * @param head
   * @return
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode dummyNode = new ListNode(0, head);
    ListNode prev = dummyNode;
    ListNode cur = head;
    ListNode temp;
    while (cur != null && cur.next != null) {
      temp = cur.next.next;
      prev.next = cur.next;
      cur.next.next = cur;
      cur.next = temp;

      prev = cur;
      cur = temp;
    }
    return dummyNode.next;
  }

  public ListNode swapPairs2(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode dummyNode = new ListNode(0, head);
    ListNode cur = dummyNode;
    ListNode next;
    ListNode nextNext;
    while (cur.next != null && cur.next.next != null) {
      next = cur.next;
      nextNext = cur.next.next;

      next.next = nextNext.next;
      nextNext.next = next;
      cur.next = nextNext;
      cur = next;
    }
    return dummyNode.next;
  }

  public ListNode swapPairs3(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode newHead = head.next;
    head.next = swapPairs3(head.next);
    newHead.next = head;
    return newHead;
  }


}
