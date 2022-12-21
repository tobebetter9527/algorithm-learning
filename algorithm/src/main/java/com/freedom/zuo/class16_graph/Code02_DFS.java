package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Node;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Depth-First-Search
 *
 * @author tobebetter9527
 * @create 2022/07/10 17:37
 */
public class Code02_DFS {

  /**
   * 用栈实现dfs
   *
   * @param start
   */
  public static void dfs(Node start) {
    if (start == null) {
      return;
    }
    Set<Node> set = new HashSet<>();
    Stack<Node> stack = new Stack<>();
    set.add(start);
    stack.push(start);
    System.out.print(start.value + " ");

    Node cur;
    while (!stack.isEmpty()) {
      cur = stack.pop();
      for (Node next : cur.nexts) {
        if (!set.contains(next)) {
          set.add(next);
          stack.push(cur);
          stack.push(next);
          System.out.print(next.value + " ");
          break;
        }
      }
    }
  }

  /**
   * 用递归实现dfs
   *
   * @param start
   */
  public static void dfs2(Node start) {
    if (start == null) {
      return;
    }

    Set<Node> set = new HashSet<>();
    recursive(start, set);
  }

  private static void recursive(Node start, Set<Node> set) {
    if (start == null) {
      return;
    }
    set.add(start);
    System.out.print(start.value + " ");
    for (Node next : start.nexts) {
      if (!set.contains(next)) {
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

    dfs(root);
    System.out.println("============");
    dfs2(root);
  }

}
