package com.freedom.leetcode.linked_list;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two
 * lists.
 * <p>
 * Return the head of the merged linked list.
 *
 * @author tobebetter9527
 * @create 2022/06/07 22:55
 */
public class Problem21_MergeTwoSortedLists {

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null && list2 != null) {
      return list2;
    }
    if (list1 != null && list2 == null) {
      return list1;
    }
    if (list1 == null && list2 == null) {
      return null;
    }

    ListNode pre = new ListNode();
    ListNode cur1 = list1;
    ListNode cur2 = list2;
    while (cur1 != null && cur2 != null) {
      if (cur1.val >= cur2.val) {
        pre.next = cur2;
        pre = cur2;
        cur2 = cur2.next;
      } else {
        pre.next = cur1;
        pre = cur1;
        cur1 = cur1.next;
      }
    }

    if (cur1 != null) {
      pre.next = cur1;
    }
    if (cur2 != null) {
      pre.next = cur2;
    }

    return list1.val <= list2.val ? list1 : list2;
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


