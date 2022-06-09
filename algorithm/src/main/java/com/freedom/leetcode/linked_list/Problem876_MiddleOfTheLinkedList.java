package com.freedom.leetcode.linked_list;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 * <p>
 * If there are two middle nodes, return the second middle node.
 *
 * @author tobebetter9527
 * @create 2022/06/09 21:10
 */
public class Problem876_MiddleOfTheLinkedList {

  /**
   * 转换为数组法，两次遍历法，快慢指针法
   *
   * @param head
   * @return
   */
  public static ListNode middleNode(ListNode head) {
    int count = 0;
    ListNode cur = head;
    while (cur != null) {
      count++;
      cur = cur.next;
    }

    cur = head;
    int index = count / 2;
    while (index > 0) {
      index--;
      cur = cur.next;
    }
    return cur;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(1);
    ListNode node3 = new ListNode(1);
    node1.next = node2;
    node2.next = node3;

    middleNode(node1);

  }


}
