package com.freedom.zuo.class09_sort_linked_list;

import java.util.Stack;

/**
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构。
 *
 * @author tobebetter9527
 * @create 2022/06/26 10:34
 */
public class Code02_IsPalindromeList {

  private static class Node {

    public int value;
    public Node next;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 栈，判断是否回文
   * <p>
   * 需要n个额外空间
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome1(Node head) {
    if (head == null) {
      return true;
    }
    Node cur = head;
    Stack<Node> stack = new Stack<>();
    while (cur != null) {
      stack.push(cur);
      cur = cur.next;
    }

    cur = head;
    while (cur != null) {
      Node pop = stack.pop();
      if (cur.value != pop.value) {
        return false;
      }
      cur = cur.next;
    }

    return true;
  }

  /**
   * 栈，需要n/2的空间
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome2(Node head) {
    if (head == null) {
      return true;
    }
    // 奇数就找到中点，偶数就找到第二个中点
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    Stack<Node> stack = new Stack<>();
    while (slow != null) {
      stack.push(slow);
      slow = slow.next;
    }

    slow = head;
    while (!stack.isEmpty()) {
      if (stack.pop().value != slow.value) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }


  /**
   * n(1)空间
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome3(Node head) {
    if (head == null || head.next == null) {
      return true;
    }

    // 奇数就找到中点，偶数就找到第一个中点
    Node slow = head;
    Node fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    Node temp = slow.next;
    slow.next = null;

    // 将temp后的反转后，temp是反转之后的头
    Node pre = null;
    while (temp.next != null) {
      Node next = temp.next;
      temp.next = pre;
      pre = temp;
      temp = next;
    }
    temp.next = pre;

    // 将上一步反转的链表与head的比较
    Node originalHead = head;
    Node reverseHead = temp;
    boolean flag = true;
    while (reverseHead != null) {
      if (reverseHead.value != originalHead.value) {
        flag = false;
        break;
      }
      originalHead = originalHead.next;
      reverseHead = reverseHead.next;
    }

    // 恢复链表
    pre = null;
    while (temp.next != null) {
      Node next = temp.next;
      temp.next = pre;
      pre = temp;
      temp = next;
    }
    temp.next = pre;

    slow.next = temp;

    return flag;
  }


  public static void main(String[] args) {
    Node head = null;
    head = new Node(1);
//    head.next = new Node(1);
 //   head.next.next = new Node(3);
  //  head.next.next.next = new Node(3);
  //  head.next.next.next.next = new Node(2);
  //  head.next.next.next.next.next = new Node(1);

    boolean palindrome3 = isPalindrome3(head);
    System.out.println(palindrome3);
  }

}
