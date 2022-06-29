package com.freedom.zuo.class11_binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化， 以下代码全部实现了。
 * <p></p>
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * <p></p>
 * 比如如下两棵树 __2 / 1 和 1__ \ 2 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 * <p></p>
 *
 * @author tobebetter9527
 * @create 2022/06/28 20:50
 */
public class Code02_SerializeAndReconstructTree {

  /**
   * 前序序列化
   *
   * @param head
   */
  public static Queue<String> preSerial(Node head) {
    Queue<String> queue = new LinkedList<>();
    pre(queue, head);
    return queue;
  }

  private static void pre(Queue<String> queue, Node head) {
    if (head == null) {
      queue.add(null);
    } else {
      queue.add(head.value);
      pre(queue, head.left);
      pre(queue, head.right);
    }
  }

  /**
   * 中序序列化
   *
   * @param head
   */
  public static Queue<String> inSerial(Node head) {
    Queue<String> queue = new LinkedList<>();
    in(queue, head);
    return queue;
  }

  private static void in(Queue<String> queue, Node head) {
    if (head != null) {
      queue.add(null);
    } else {
      in(queue, head.left);
      queue.add(head.value);
      in(queue, head.right);
    }
  }

  /**
   * 后序遍历
   *
   * @param head
   */
  public static Queue<String> postSerial(Node head) {
    Queue<String> queue = new LinkedList<>();
    post(queue, head);
    return queue;
  }

  private static void post(Queue<String> queue, Node head) {
    if (head == null) {
      queue.add(null);
    } else {
      post(queue, head.left);
      post(queue, head.right);
      queue.add(head.value);
    }
  }

  /**
   * 从前序重建
   *
   * @param prelist
   */
  public static Node buildByPreQueue(Queue<String> prelist) {
    if (prelist == null || prelist.isEmpty()) {
      return null;
    }
    return preb(prelist);
  }

  private static Node preb(Queue<String> prelist) {
    if (prelist.isEmpty()) {
      return null;
    }

    String value = prelist.poll();
    if (value == null) {
      return null;
    }

    Node node = new Node(value);
    node.left = preb(prelist);
    node.right = preb(prelist);
    return node;
  }

  /**
   * 从后续重建
   *
   * @param poslist
   * @return
   */
  public static Node buildByPosQueue(Queue<String> poslist) {
    if (poslist == null || poslist.isEmpty()) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    while (!poslist.isEmpty()) {
      stack.push(poslist.poll());
    }

    return postb(stack);
  }

  private static Node postb(Stack<String> stack) {
    String value = stack.pop();
    if (value == null) {
      return null;
    }
    Node node = new Node(value);
    node.right = postb(stack);
    node.left = postb(stack);
    return node;
  }

  /**
   * 水平序列化
   *
   * @param head
   * @return
   */
  public static Queue<String> levelSerial(Node head) {
    Queue<String> serialQueue = new LinkedList<>();
    if (head == null) {
      serialQueue.add(null);
    } else {
      Node cur = head;
      Queue<Node> queue = new LinkedList<>();
      queue.add(cur);
      serialQueue.add(cur.value);

      while (!queue.isEmpty()) {
        cur = queue.poll();
        if (cur.left != null) {
          queue.add(cur.left);
          serialQueue.add(cur.left.value);
        } else {
          serialQueue.add(null);
        }
        if (cur.right != null) {
          queue.add(cur.right);
          serialQueue.add(cur.right.value);
        } else {
          serialQueue.add(null);
        }
      }
    }
    return serialQueue;
  }

  public static Node buildByLevelQueue(Queue<String> levelList) {
    Node root = generateNode(levelList);
    if (root == null) {
      return null;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    Node cur;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      cur.left = generateNode(levelList);
      cur.right = generateNode(levelList);
      if (cur.left != null) {
        queue.add(cur.left);
      }
      if (cur.right != null) {
        queue.add(cur.right);
      }
    }

    return root;
  }

  private static Node generateNode(Queue<String> levelList) {
    String value = levelList.poll();
    if (value == null) {
      return null;
    }
    return new Node(value);
  }

  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBinaryTree(maxLevel, maxValue);
      Queue<String> preQueue = preSerial(head);
      Queue<String> postQueue = postSerial(head);
      Queue<String> levelQueue = levelSerial(head);
      Node preHead = buildByPreQueue(preQueue);
      Node postHead = buildByPosQueue(postQueue);
      Node levelHead = buildByLevelQueue(levelQueue);
      if (!isSameValueBT(head, preHead)) {
        System.out.println("preHead something wrong!");
      }
      if (!isSameValueBT(head, postHead)) {
        System.out.println("postHead something wrong!");
      }
      if (!isSameValueBT(head, levelHead)) {
        System.out.println("levelHead something wrong!");
      }
    }
    System.out.println("done!");
  }

  private static boolean isSameValueBT(Node head, Node preHead) {
    if (head == null && preHead != null) {
      return false;
    }
    if (head != null && preHead == null) {
      return false;
    }
    if (head == null && preHead == null) {
      return true;
    }
    if (head.value != preHead.value) {
      return false;
    }
    return isSameValueBT(head.left, head.left) && isSameValueBT(head.right, preHead.right);
  }

  private static Node generateRandomBinaryTree(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  private static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5D) {
      return null;
    }
    Node head = new Node(String.valueOf((int) (Math.random() * maxLevel)));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }


  private static class Node {

    String value;
    Node left;
    Node right;

    public Node(String data) {
      this.value = data;
    }
  }

}
