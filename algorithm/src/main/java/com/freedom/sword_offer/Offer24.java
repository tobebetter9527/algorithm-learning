package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/11 10:02
 */
public class Offer24 {

  /**
   * time complexity is O(n），space comlexity is O(1)
   *
   * @param head
   * @return
   */
  public ListNode reverseList(ListNode head) {
    ListNode pre = null, cur = head, next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  public ListNode reverseList2(ListNode head) {
    return recursive(null, head);
  }

  private ListNode recursive(ListNode pre, ListNode cur) {
    if (cur == null) {
      return pre;
    }
    ListNode next = recursive(cur, cur.next);
    cur.next = pre;
    return next;
  }


}
