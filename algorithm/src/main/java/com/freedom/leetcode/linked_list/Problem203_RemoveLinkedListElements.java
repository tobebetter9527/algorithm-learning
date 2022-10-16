package com.freedom.leetcode.linked_list;

/**
 * 203. Remove Linked List Elements
 *
 * @author tobebetter9527
 * @create 2022/10/16 9:10
 */
public class Problem203_RemoveLinkedListElements {

  /**
   * time complexxity is O(n), space complexity is O(1)
   *
   * @param head
   * @param val
   * @return
   */
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return null;
    }

    ListNode preHead = new ListNode(0, head);
    ListNode pre = preHead, cur = head;
    while (cur != null) {
      if (cur.val == val) {
        pre.next = cur.next;
      } else {
        pre = cur;
      }
      cur = cur.next;
    }

    return preHead.next;
  }


  public static void main(String[] args) {
    System.out.println("");
  }
}
