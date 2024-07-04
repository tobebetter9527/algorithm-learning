package com.freedom.leetcode.linked_list;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the
 * linked list sorted as well.
 *
 * @author tobebetter9527
 * @create 2022/06/08 21:48
 */
public class Problem83_RemoveDuplicates {

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 时间复杂度：O(n)O(n)，空间复杂度：O(1)O(1)。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                pre.next = cur.next;
                pre = cur.next;
            }
            cur = cur.next;
        }
        pre.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;

        ListNode listNode = deleteDuplicates(node1);
        System.out.println(listNode);

    }

}
