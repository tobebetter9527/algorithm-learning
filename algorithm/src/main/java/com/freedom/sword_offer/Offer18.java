package com.freedom.sword_offer;

/**
 * 剑指 Offer 18. 删除链表的节点
 *
 * @author tobebetter9527
 * @create 2023/01/08 20:40
 */
public class Offer18 {


  public ListNode deleteNode(ListNode head, int val) {
    ListNode preHead = new ListNode(0);
    preHead.next = head;
    ListNode pre = preHead, cur = head;
    while (cur != null) {
      if (cur.val == val) {
        pre.next = cur.next;
        return preHead.next;
      }
      pre = cur;
      cur = cur.next;
    }
    return preHead.next;
  }

}
