package com.freedom.leetcode.linked_list;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * @author tobebetter9527
 * @create 2022/06/08 22:42
 */
public class Problem206_ReverseList {

  /**
   * time complexity is O(n), space complexity is O(1)
   *
   * @param head
   * @return
   */
  public static ListNode reverseList(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode pre = null;
    ListNode cur = head;
    ListNode next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  /**
   * time complexity is O(n), space complexity is O(n)
   *
   * @return 头节点
   */
  public static ListNode reverseList2(ListNode head) {
    return recursion(null, head);
  }

  /**
   * @param prev 前一个节点
   * @param cur  当前节点
   * @return 头节点
   */
  private static ListNode recursion(ListNode prev, ListNode cur) {
    // 如果cur等于null，说明已经遍历到头，prev节点就是头节点
    if (cur == null) {
      return prev;
    } else {
      // 继续往下递归
      ListNode head = recursion(cur, cur.next);
      // 反转链表
      cur.next = prev;
      return head;
    }
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    ListNode node = reverseList2(node1);
    System.out.println(node);


  }

}
