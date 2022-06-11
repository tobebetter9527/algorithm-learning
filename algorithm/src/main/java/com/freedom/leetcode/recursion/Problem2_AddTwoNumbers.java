package com.freedom.leetcode.recursion;

import com.freedom.leetcode.linked_list.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
 * order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * @author tobebetter9527
 * @create 2022/06/11 12:11
 */
public class Problem2_AddTwoNumbers {

  /**
   * 递归法
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode();
    int carry = 0;
    recursion(l1, l2, carry, preHead);
    return preHead.next;
  }

  private void recursion(ListNode l1, ListNode l2, int carry, ListNode pre) {
    // 递归终止条件
    if (l1 == null && l2 == null) {
      if (carry > 0) {
        pre.next = new ListNode(carry);
      } else {
        pre.next = null;
      }
      return;
    }

    // 计算新节点
    int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
    carry = val / 10;
    pre.next = new ListNode(val % 10);

    // 递归调用
    recursion((l1 != null && l1.next != null) ? l1.next : null, (l2 != null && l2.next != null) ? l2.next : null, carry,
        pre.next);
  }

  /**
   * 迭代法
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode();
    ListNode pre = preHead;
    int carry = 0;
    while (l1 != null && l2 != null) {
      int val = l1.val + l2.val + carry;
      carry = val / 10;
      pre.next = new ListNode(val % 10);
      pre = pre.next;

      l1 = l1.next;
      l2 = l2.next;
    }

    ListNode temp = l1 == null ? l2 : l1;
    while (temp != null) {
      int val = temp.val + carry;
      carry = val / 10;
      pre.next = new ListNode(val % 10);
      pre = pre.next;

      temp = temp.next;
    }

    if (carry > 0) {
      pre.next = new ListNode(carry);
    }
    return preHead.next;
  }

}
