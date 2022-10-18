package com.freedom.leetcode.linked_list;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. Linked List Cycle II
 *
 * @author tobebetter9527
 * @create 2022/10/18 21:07
 */
public class Problem142_LinkedListCycleII {

  /**
   * hash function
   * <p>
   * time complexity is O(n), space complextiy is O(n)
   *
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }

    Set<ListNode> set = new HashSet<>();
    ListNode cur = head;
    while (cur != null) {
      if (set.contains(cur)) {
        return cur;
      }
      set.add(cur);
      cur = cur.next;
    }

    return null;
  }

  /**
   * 双指针法
   * <p>
   * time complexity is O(n), space complextiy is O(1)
   *
   * @param head
   * @return
   */
  public ListNode detectCycle2(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;
    while (fast != null) {
      if (fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
      } else {
        return null;
      }

      if (fast == slow) {
        ListNode p = head;
        while (p != slow) {
          p = p.next;
          slow = slow.next;
        }
        return p;
      }
    }
    return null;
  }
}
