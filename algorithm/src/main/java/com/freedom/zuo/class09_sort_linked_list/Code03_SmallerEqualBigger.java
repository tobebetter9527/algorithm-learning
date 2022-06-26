package com.freedom.zuo.class09_sort_linked_list;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式l
 *
 * @author tobebetter9527
 * @create 2022/06/26 14:17
 */
public class Code03_SmallerEqualBigger {

  public static Node listPartition2(Node head, int pivot) {
    if (head == null) {
      return null;
    }

    Node lessHead = null;
    Node lessTail = null;
    Node equalHead = null;
    Node equalTail = null;
    Node largerHead = null;
    Node largerTail = null;

    Node cur = head;
    while (cur != null) {
      if (cur.value < pivot) {
        if (lessHead == null) {
          lessHead = cur;
          lessTail = cur;
        } else {
          lessTail.next = cur;
          lessTail = cur;
        }
      } else if (cur.value == pivot) {
        if (equalHead == null) {
          equalHead = cur;
          equalTail = cur;
        } else {
          equalTail.next = cur;
          equalTail = cur;
        }
      } else {
        if (largerHead == null) {
          largerHead = cur;
          largerTail = cur;
        } else {
          largerTail.next = cur;
          largerTail = cur;
        }
      }
      cur = cur.next;
    }

    // 特别注意
    if (largerTail != null) {
      largerTail.next = null;
    }

    if (lessTail != null) {
      if (equalHead != null) {
        lessTail.next = equalHead;
        equalTail.next = largerHead;
      } else {
        lessTail.next = largerHead;
      }
    } else {
      if (equalTail != null) {
        equalTail.next = largerHead;
      }
    }

    return lessHead != null ? lessHead : (equalHead != null ? equalHead : largerHead);
  }

  public static void main(String[] args) {
    Node head = null;
    head = new Node(1);
    head.next = new Node(1);
    head.next.next = new Node(4);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(2);
    head.next.next.next.next.next = new Node(2);

    Node temp = listPartition3(head, 3);
    while (temp != null) {
      System.out.println(temp.value);
      temp = temp.next;
    }
  }

  public static Node listPartition3(Node head, int pivot) {
    Node sH = null; // small head
    Node sT = null; // small tail
    Node eH = null; // equal head
    Node eT = null; // equal tail
    Node mH = null; // big head
    Node mT = null; // big tail
    Node next = null; // save next node
    // every node distributed to three lists
    while (head != null) {
      next = head.next;
      head.next = null;
      if (head.value < pivot) {
        if (sH == null) {
          sH = head;
          sT = head;
        } else {
          sT.next = head;
          sT = head;
        }
      } else if (head.value == pivot) {
        if (eH == null) {
          eH = head;
          eT = head;
        } else {
          eT.next = head;
          eT = head;
        }
      } else {
        if (mH == null) {
          mH = head;
          mT = head;
        } else {
          mT.next = head;
          mT = head;
        }
      }
      head = next;
    }
    // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
    if (sT != null) { // 如果有小于区域
      sT.next = eH;
      eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
    }
    // 下一步，一定是需要用eT 去接 大于区域的头
    // 有等于区域，eT -> 等于区域的尾结点
    // 无等于区域，eT -> 小于区域的尾结点
    // eT 尽量不为空的尾巴节点
    if (eT != null) { // 如果小于区域和等于区域，不是都没有
      eT.next = mH;
    }
    return sH != null ? sH : (eH != null ? eH : mH);
  }


  private static class Node {

    public int value;
    public Node next;

    public Node(int data) {
      this.value = data;
    }
  }
}
