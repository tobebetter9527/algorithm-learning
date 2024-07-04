package com.freedom.leetcode.linked_list;

/**
 * 707. Design Linked List
 * <p>
 * 双链表法，模仿Java的LinkedList实现
 *
 * @author tobebetter9527
 * @create 2022/10/16 10:12
 */
public class MyLinkedList3_707 {

    /**
     * 首节点
     */
    private Node head;
    /**
     * 尾节点
     */
    private Node tail;

    private int size;

    public MyLinkedList3_707() {
    }

    /**
     * Get the value of the index<sup>th</sup> node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            return -1;
        }

        return node(index).val;
    }

    private Node node(int index) {
        // 从头开始查
        if (index < (size >> 1)) {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
            // 从尾巴查
        } else {
            Node cur = tail;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            }
            return cur;
        }
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
        if (index == size) {
            linkLast(val);
        } else {
            linkBefore(val, node(index));
        }
    }

    private void linkBefore(int val, Node succ) {
        Node prev = succ.prev;
        Node newNode = new Node(prev, val, succ);
        succ.prev = newNode;
        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    private void linkLast(int val) {
        Node l = tail;
        Node newNode = new Node(l, val, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
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
        unlink(node(index));
    }

    private int unlink(Node node) {
        int val = node.val;
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        size--;
        return val;
    }

    private static class Node {

        public int val;
        public Node prev, next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node prev, int val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }
}
