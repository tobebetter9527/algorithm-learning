package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/02/11 11:06
 */
public class Offer25 {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode(0);
    ListNode cur = preHead;
    while (l1 != null && l2 != null) {
      if (l1.val >= l2.val) {
        cur.next = l2;
        l2 = l2.next;
      } else {
        cur.next = l1;
        l1 = l1.next;
      }
      cur = cur.next;
    }
    cur.next = l1 != null ? l1 : l2;
    return preHead.next;
  }
}
