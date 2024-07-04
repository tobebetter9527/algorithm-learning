package com.freedom.leetcode.linked_list;

/**
 * 707. Design Linked List
 *
 * @author tobebetter9527
 * @create 2022/10/16 10:12
 */
public class MyLinkedList2_707 {

    /**
     * 虚拟节点，或者说哨兵节点
     */
    private Node head;

    private int size;

    public MyLinkedList2_707() {
        head = new Node(0);
        size = 0;
    }

    /**
     * Get the value of the index<sup>th</sup> node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            return -1;
        }

        Node cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the
     * first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val as the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
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
        size++;

        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
    }

    /**
     * Delete the index<sup>th</sup> node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) {
            return;
        }
        size--;

        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
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
