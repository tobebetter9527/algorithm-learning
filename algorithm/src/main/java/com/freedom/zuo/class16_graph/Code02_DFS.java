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

  public static void dfs(Node start) {
    if (start == null) {
      return;
    }
    Set<Node> set = new HashSet<>();
    Stack<Node> stack = new Stack<>();
    set.add(start);
    stack.push(start);
    System.out.println(start.value);

    Node cur;
    while (!stack.isEmpty()) {
      cur = stack.pop();
      for (Node next : cur.nexts) {
        if (!set.contains(next)) {
          set.add(next);
          stack.push(cur);
          stack.push(next);
          System.out.println(next.value);
          break;
        }
      }
    }
  }


}
