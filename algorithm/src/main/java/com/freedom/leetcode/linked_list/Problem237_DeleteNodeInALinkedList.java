package com.freedom.leetcode.linked_list;

/**
 * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list,
 * instead you will be given access to the node to be deleted directly.
 * <p>
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 *
 * @author tobebetter9527
 * @create 2022/06/09 21:00
 */
public class Problem237_DeleteNodeInALinkedList {

  /**
   * 时间复杂度：O(1)。空间复杂度：O(1)。
   * @param node
   */
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

}
