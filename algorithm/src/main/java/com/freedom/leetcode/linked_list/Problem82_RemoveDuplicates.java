package com.freedom.leetcode.linked_list;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 *
 * @author tobebetter9527
 * @create 2022/06/08 22:42
 */
public class Problem82_RemoveDuplicates {

  public ListNode deleteDuplicates(ListNode head) {
    ListNode preHead = new ListNode();
    ListNode pre = preHead;
    ListNode cur = head;
    while (cur.next != null) {
      ListNode temp = cur;
      boolean flag = true;
      while (flag) {
        if (temp.val != cur.next.val) {
          flag = false;
        }
        cur = cur.next;
      }

      pre.next = cur;
    }

    pre.next = cur;
    return preHead.next;
  }

  private static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

}
