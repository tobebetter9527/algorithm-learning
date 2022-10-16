package com.freedom.leetcode.linked_list;

/**
 * 707. Design Linked List
 *
 * 不算是良好的实现
 *
 * @author tobebetter9527
 * @create 2022/10/16 10:12
 */
public class MyLinkedList1_707 {

  private Node head;
  private Node tail;
  private int size;

  public MyLinkedList1_707() {

  }

  /**
   * Get the value of the index<sup>th</sup> node in the linked list. If the index is invalid, return -1.
   */
  public int get(int index) {
    if (index + 1 > size) {
      return -1;
    }
    Node cur = head;
    while (index > 0) {
      cur = cur.next;
      index--;
    }

    return cur.val;
  }

  /**
   * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the
   * first node of the linked list.
   */
  public void addAtHead(int val) {
    if (!isEmpty()) {
      Node node = new Node(val, head);
      head = node;
    } else {
      Node node = new Node(val);
      head = node;
      tail = node;
    }
    size++;
  }

  /**
   * Append a node of value val as the last element of the linked list.
   */
  public void addAtTail(int val) {
    if (!isEmpty()) {
      Node node = new Node(val);
      tail.next = node;
      tail = node;
    } else {
      Node node = new Node(val);
      head = node;
      tail = node;
    }
    size++;
  }

  /**
   * Add a node of value val before the index<sup>th</sup> node in the linked list. If index equals the length of the
   * linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node
   * will not be inserted.
   */
  public void addAtIndex(int index, int val) {
    if (index > size || index < 0) {
      return;
    }

    if (!isEmpty()) {
      if (index == size) {
        Node node = new Node(val);
        tail.next = node;
        tail = node;
      } else if (index == 0) {
        Node node = new Node(val, head);
        head = node;
      } else {
        Node cur = head;
        // 找到index的前一个点即可
        index = index - 1;
        while (index > 0) {
          cur = cur.next;
          index--;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
      }
    } else {
      Node node = new Node(val);
      head = node;
      tail = node;
    }
    size++;
  }

  /**
   * Delete the index<sup>th</sup> node in the linked list, if the index is valid.
   */
  public void deleteAtIndex(int index) {
    if (index >= size || index < 0) {
      return;
    }

    if (index == 0) {
      if (size == 1) {
        head = null;
        tail = null;
      } else {
        head = head.next;
      }
    } else {
      Node cur = head;
      int idx = index - 1;
      while (idx > 0) {
        cur = cur.next;
        idx--;
      }
      if (index + 1 == size) {
        cur.next = null;
        tail = cur;
      } else {
        cur.next = cur.next.next;
      }
    }
    size--;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private static class Node {

    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }
}
