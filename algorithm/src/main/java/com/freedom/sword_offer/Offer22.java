package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/09 21:40
 */
public class Offer22 {

  /**
   * two pointer
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode getKthFromEnd(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    ListNode fast = head;
    for (int i = 0; i < k; i++) {
      fast = fast.next;
    }
    ListNode slow = head;
    while (fast != null) {
      fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }

  /**
   * count
   * @param head
   * @param k
   * @return
   */
  public ListNode getKthFromEnd2(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    int num = 0;
    ListNode cur = head;
    while (cur != null) {
      num++;
      cur = cur.next;
    }
    num = num - k;
    cur = head;
    while (num > 0) {
      num--;
      cur = cur.next;
    }
    return cur;
  }

}
