package com.freedom.zuo.class09_sort_linked_list;

import java.util.ArrayList;
import java.util.List;

/**
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * <p>
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * <p>
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * <p>
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 * @author tobebetter9527
 * @create 2022/06/26 9:23
 */
public class Code01_LinkedListMiddle {


    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidNode1(Node head) {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node getRight1(Node head) {
        if (head == null) {
            return null;
        }
        int count = 0;
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            count++;
            list.add(cur);
            cur = cur.next;
        }

        return list.get((count - 1) / 2);
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidNode2(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node getRight2(Node head) {
        if (head == null) {
            return null;
        }
        int count = 0;
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            count++;
            list.add(cur);
            cur = cur.next;
        }

        return list.get(count / 2);
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node getRight3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        int count = 0;
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            count++;
            list.add(cur);
            cur = cur.next;
        }

        return list.get((count - 3) / 2);
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node getRight4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        int count = 0;
        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            count++;
            list.add(cur);
            cur = cur.next;
        }

        return list.get((count - 2) / 2);
    }

    public static void main(String[] args) {
        Node head = null;
        head = new Node(0);
        //   head.next = new Node(1);
        //   head.next.next = new Node(2);
        //  head.next.next.next = new Node(3);
        // head.next.next.next.next = new Node(4);
        //  head.next.next.next.next.next = new Node(5);

        Node node1 = midOrDownMidPreNode4(head);
        Node node2 = getRight4(head);
        if (node1 == node2) {
            System.out.println("right!");
        } else {
            System.out.println("wrong!");
        }


    }


    private static class Node {

        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }
}
