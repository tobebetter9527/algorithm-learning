package com.freedom.leetcode.linked_list;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 *
 * @author tobebetter9527
 * @create 2022/06/08 22:42
 */
public class Problem82_RemoveDuplicates {

  /**
   * 官方解法：时间复杂度：O(n)O(n)，空间复杂度：O(1)O(1)
   *
   * @param head
   * @return
   */
  public static ListNode deleteDuplicates2(ListNode head) {
    if (head == null) {
      return null;
    }
    // 哨兵节点，简化代码
    ListNode preHead = new ListNode(-1, head);
    ListNode cur = preHead;
    while (cur.next != null && cur.next.next != null) {
      // 连续两个节点的值相等
      if (cur.next.val == cur.next.next.val) {
        int val = cur.next.val;
        // 判断接下来的点是不是跟val相等，相等，cur.next不停的后跳
        while (cur.next != null && cur.next.val == val) {
          cur.next = cur.next.next;
        }
      } else {
        cur = cur.next;
      }
    }
    return preHead.next;
  }


  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode preHead = new ListNode(-1, head);
    ListNode pre = preHead;
    ListNode cur = head;
    while (cur != null && cur.next != null) {
      ListNode temp = pre.next;
      boolean flag = false;
      while (cur.next != null) {
        if (temp.val == cur.next.val) {
          flag = true;
          cur = cur.next;
        } else {
          break;
        }
      }
      if (flag) {
        pre.next = cur.next;
      } else {
        pre = pre.next;
      }
      cur = cur.next;
    }
    return preHead.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode listNode = deleteDuplicates(head);
    System.out.println(listNode);
  }

}
