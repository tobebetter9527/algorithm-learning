package com.freedom.leetcode.linked_list;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * @author tobebetter9527
 * @create 2022/06/08 22:42
 */
public class Problem206_ReverseList {

  /**
   * 方法写法,时间复杂度O(n),空间复杂度O(1)
   *
   * @param head
   * @return
   */
  public ListNode reverseList2(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null && cur.next != null) {
      ListNode temp = cur.next;
      cur.next = pre;
      pre = cur;
      cur = temp;
    }
    cur.next = pre;
    return cur;
  }

}
