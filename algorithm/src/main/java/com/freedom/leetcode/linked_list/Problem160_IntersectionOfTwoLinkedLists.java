package com.freedom.leetcode.linked_list;

/**
 * Intersection of Two Linked Lists LCCI
 */
public class Problem160_IntersectionOfTwoLinkedLists {

  /**
   * time complexity: O(n)
   * <p>
   * space complexity: O(1)
   *
   * @param headA
   * @param headB
   * @return
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    int sizeA = getLength(headA);
    int sizeB = getLength(headB);

    int gap = sizeA >= sizeB ? sizeA - sizeB : sizeB - sizeA;
    ListNode big = sizeA >= sizeB ? headA : headB;
    ListNode small = sizeA >= sizeB ? headB : headA;

    while (gap > 0) {
      big = big.next;
      gap--;
    }

    while (big != null) {
      if (big == small) {
        return big;
      }
      big = big.next;
      small = small.next;
    }
    return null;
  }

  private int getLength(ListNode headA) {
    int sizeA = 0;
    ListNode curA = headA;
    while (curA != null) {
      sizeA++;
      curA = curA.next;
    }
    return sizeA;
  }

  public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode pa = headA, pb = headB;
    while (pa != pb) {
      pa = pa == null ? headB : pa.next;
      pb = pb == null ? headA : pb.next;
    }
    return pa;
  }
}
