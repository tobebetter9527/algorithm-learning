package com.freedom.zuo.class09_sort_linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 *
 * @author tobebetter9527
 * @create 2022/06/26 15:16
 */
public class Code04_CopyListWithRandom {

    public static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }

        // 哈希表缓存,提前创建
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }


    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 先复制并连接
        Node node = head;
        while (node != null) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
            node = node.next.next;
        }

        // 复制random指针
        node = head;
        while (node != null) {
            Node newNode = node.next;
            newNode.random = node.random != null ? node.random.next : null;
            node = node.next.next;
        }

        // 拆连接
        Node newHead = head.next;
        node = head;
        while (node != null) {
            Node next = node.next;
            node.next = next != null ? next.next : null;
            node = next;
        }

        return newHead;
    }

    private static class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
