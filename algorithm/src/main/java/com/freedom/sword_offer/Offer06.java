package com.freedom.sword_offer;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Offer06 {

  /**
   * time complexity is O(n), space complexity is O(n).
   * @param head
   * @return
   */
  public static int[] reversePrint(ListNode head) {
    if (head == null) {
      return new int[0];
    }

    List<Integer> list = new LinkedList<>();
    recursive(head, list);
    int n = list.size();
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = list.get(i);
    }
    return ans;
  }

  private static void recursive(ListNode head, List<Integer> list) {
    if (head == null) {
      return;
    }
    recursive(head.next, list);
    list.add(head.val);
  }

  public static int[] reversePrint2(ListNode head) {
    if (head == null) {
      return new int[0];
    }
    Stack<Integer> stack = new Stack<>();
    while (head != null) {
      stack.push(head.val);
      head = head.next;
    }
    int n = stack.size();
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = stack.pop();
    }
    return ans;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    node1.next = node2;
    node2.next =node3;
    int[] ints = reversePrint2(node1);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }

}
