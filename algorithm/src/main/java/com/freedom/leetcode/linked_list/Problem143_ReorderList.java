package com.freedom.leetcode.linked_list;

/**
 * 143. Reorder List
 *
 * @author tobebetter9527
 * @create 2022/12/17 15:46
 */
public class Problem143_ReorderList {

  /**
   * time complexity is O(n^2),space complexity is O(1)
   * <p>
   * 更有的解法：寻找链表中点，后半部链表反转，然后合并两个链表。
   *
   * @param head
   */
  public static void reorderList(ListNode head) {
    ListNode cur = head;
    while (cur.next != null) {
      ListNode next = cur.next;
      ListNode lastNode = findLastNode(cur);
      cur.next = lastNode;
      if (lastNode != null && next != lastNode) {
        lastNode.next = next;
      }
      cur = next;
    }
  }

  private static ListNode findLastNode(ListNode cur) {
    while (cur != null && cur.next != null && cur.next.next != null) {
      cur = cur.next;
    }
    ListNode next = cur.next;
    cur.next = null;
    return next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode node1 = new ListNode(2);
    ListNode node2 = new ListNode(3);
    ListNode node3 = new ListNode(4);
    ListNode node4 = new ListNode(5);
    head.next = node1;
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    reorderList(head);
    System.out.println("1");
  }
}
