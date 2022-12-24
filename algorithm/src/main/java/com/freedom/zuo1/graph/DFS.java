package com.freedom.zuo1.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * DFS
 *
 * @author tobebetter9527
 * @create 2022/12/24 16:45
 */
public class DFS {

  public static void dfsUsingStack(Node start) {
    if (start == null) {
      return;
    }
    Set<Node> set = new HashSet<>();
    set.add(start);
    Stack<Node> stack = new Stack<>();
    stack.push(start);
    System.out.println(start.value);
    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      for (Node next : cur.nexts) {
        if (!set.contains(next)) {
          System.out.println(next.value);
          set.add(next);
          stack.push(cur);
          stack.push(next);
          break;
        }
      }
    }
  }

  public static void dfsUsingRecursive(Node start) {
    if (start == null) {
      return;
    }

    Set<Node> set = new HashSet<>();
    set.add(start);
    System.out.println(start.value);
    recursive(start, set);
  }

  private static void recursive(Node start, Set<Node> set) {
    if (start == null) {
      return;
    }
    for (Node next : start.nexts) {
      if (!set.contains(next)) {
        System.out.println(next.value);
        set.add(next);
        recursive(next, set);
      }
    }
  }


  public static void main(String[] args) {
    Node root = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);

    root.nexts.add(node2);
    root.nexts.add(node3);
    root.nexts.add(node4);

    node2.nexts.add(node5);
    node2.nexts.add(node3);

    node3.nexts.add(node6);

    node5.nexts.add(node3);

    node6.nexts.add(node5);
    node6.nexts.add(node7);

    dfsUsingStack(root);
    System.out.println("============");
    dfsUsingRecursive(root);
  }
}
