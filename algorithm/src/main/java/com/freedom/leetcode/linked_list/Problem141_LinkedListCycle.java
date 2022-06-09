package com.freedom.leetcode.linked_list;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
 * connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * @author tobebetter9527
 * @create 2022/06/09 20:35
 */
public class Problem141_LinkedListCycle {

  /**
   * 时间复杂度：O(N)，空间复杂度：O(1)
   *
   * @param head
   * @return
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }

}
