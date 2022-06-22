package com.freedom.leetcode.heap;

import com.freedom.leetcode.linked_list.ListNode;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * @author tobebetter9527
 * @create 2022/06/22 21:17
 */
public class Problem23_MergeKSortedLists {

  /**
   * 渐进时间复杂度为O(kn×logk)
   * <p>
   * 渐进空间复杂度为 O(k)
   *
   * @param lists
   * @return
   */
  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    ListNode preHead = new ListNode();
    ListNode pre = preHead;
    int length = lists.length;
    // 构建小根堆
    PriorityQueue<ListNode> heap = new PriorityQueue<>(length, (o1, o2) -> o1.val - o2.val);
    for (int i = 0; i < length; i++) {
      if (lists[i] != null) {
        heap.add(lists[i]);
      }
    }

    while (!heap.isEmpty()) {
      ListNode node = heap.poll();
      pre.next = node;
      pre = node;
      if (node.next != null) {
        heap.add(node.next);
      }
    }

    return preHead.next;
  }

  public static void main(String[] args) {
    ListNode[] lists = new ListNode[2];
    lists[0] = null;
    lists[1] = new ListNode(1);

    ListNode node = mergeKLists(lists);
    System.out.println(node);

  }

}
