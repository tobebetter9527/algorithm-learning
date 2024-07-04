package com.freedom.zuo.class03_linkedlist_queue_stack;

/**
 * 反转链表
 *
 * @author tobebetter9527
 * @create 2022/06/06 20:52
 */
public class Code01_ReverseLinkedList {

    /**
     * 反转单链表
     * <p>
     * head
     * <p>
     * a    ->   b    ->  c  ->  null
     * <p>
     * c    ->   b    ->  a  ->  null
     *
     * @param head
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            // 先保存下个节点
            next = head.next;
            // 处理head节点内的next变量
            head.next = pre;
            // 此时pre变量移动到head
            pre = head;
            // head往后移动
            head = next;
        }
        return pre;
    }

    /**
     * 反转双链表
     *
     * @param head
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            // 先保存下个节点
            next = head.next;
            // 处理head节点内的pre和next
            head.pre = next;
            head.next = pre;
            // pre和next往后移动
            pre = head;
            head = next;
        }
        return pre;
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

        public DoubleNode(int value, DoubleNode pre,
                          DoubleNode next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

}
