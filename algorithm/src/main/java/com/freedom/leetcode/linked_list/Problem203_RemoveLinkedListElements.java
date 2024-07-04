package com.freedom.leetcode.linked_list;

/**
 * 203. Remove Linked List Elements
 *
 * @author tobebetter9527
 * @create 2022/10/16 9:10
 */
public class Problem203_RemoveLinkedListElements {

    /**
     * time complexxity is O(n), space complexity is O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode preHead = new ListNode(0, head);
        ListNode pre = preHead, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return preHead.next;
    }

    /**
     * 更简洁
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode preHead = new ListNode(0, head);
        ListNode cur = preHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return preHead.next;
    }

    /**
     * recursion
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(3, node1);
        ListNode node3 = new ListNode(6, node2);
        ListNode node = removeElements3(node3, 3);
        System.out.println(node);

    }
}
