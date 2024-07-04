package com.freedom.zuo.class03_linkedlist_queue_stack;

/**
 * 删除给点值
 *
 * @author tobebetter9527
 * @create 2022/06/06 22:15
 */
public class Code02_DeleteGivenValue {

    public static Node removeValue(Node head, int value) {
        // head来到第一个不等于value的节点
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }


    public static DoubleNode removeValue(DoubleNode head, int value) {


        return null;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(3);
        Node node4 = new Node(1);
        Node node5 = new Node(1);
        Node node6 = new Node(4);
        Node node7 = new Node(1);

        Node head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Node node = removeValue(head, 1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }

    private static class Node {

        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class DoubleNode {

        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }

        public DoubleNode(int value, DoubleNode pre, DoubleNode next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }
}
