package com.freedom.leetcode.linked_list;

/**
 * 19. Remove Nth Node From End of List
 *
 * @author tobebetter9527
 * @create 2022/10/17 22:39
 */
public class Problem19_RemoveNthNodeFromEndOfList {

  /**
   * time complexity is O(n), space complexity is O(1).
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    ListNode preHead = new ListNode(0, head);
    ListNode cur = preHead;
    int size = 0;
    while (cur.next != null) {
      cur = cur.next;
      size++;
    }

    int index = size - n;
    cur = preHead;
    for (int i = 0; i < index; i++) {
      cur = cur.next;
    }
    cur.next = cur.next.next;
    return preHead.next;
  }

  /**
   * time complexity is O(n), space complexity is O(1).
   * <p>
   * 双指针法
   */
  public ListNode removeNthFromEnd2(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    ListNode dummyNode = new ListNode(0, head);
    ListNode fast = dummyNode;
    ListNode slow = dummyNode;

    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;
    return dummyNode.next;
  }


}
