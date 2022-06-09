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

  /**
   * 时间复杂度O(n+m),空间复杂度O(n+m)
   *
   * @param list1
   * @param list2
   * @return
   */
  public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    } else if (list2 == null) {
      return list1;
    } else if (list1.val >= list2.val) {
      list2.next = mergeTwoLists3(list1, list2.next);
      return list2;
    } else {
      list1.next = mergeTwoLists3(list1.next, list2);
      return list1;
    }
  }


  /**
   * 时间复杂度O(n+m),空间复杂度O(1)
   *
   * @param list1
   * @param list2
   * @return
   */
  public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
    ListNode preHead = new ListNode();
    ListNode pre = preHead;
    while (list1 != null && list2 != null) {
      if (list1.val >= list2.val) {
        pre.next = list2;
        pre = list2;
        list2 = list2.next;
      } else {
        pre.next = list1;
        pre = list1;
        list1 = list1.next;
      }
    }

    pre.next = list1 == null ? list2 : list1;

    return preHead.next;
  }


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

}


