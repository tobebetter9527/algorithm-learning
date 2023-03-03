package com.freedom.sword_offer;

public class Offer52 {

  ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode nodeA = headA, nodeB = headB;
    while (nodeA != nodeB) {
      nodeA = nodeA == null ? headB : nodeA.next;
      nodeB = nodeB == null ? headA : nodeB.next;
    }
    return nodeA;
  }

}
