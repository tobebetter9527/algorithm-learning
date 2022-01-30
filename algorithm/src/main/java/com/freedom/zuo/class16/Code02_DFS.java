package com.freedom.zuo.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 深度有限遍历
 *
 * @author tobebetter9527
 * @create 2022/01/30 10:32
 */
public class Code02_DFS {

  public static void dfs(Node start) {
    if (start == null) {
      return;
    }

    Stack<Node> stack = new Stack<>();
    Set<Node> set = new HashSet<>();
    stack.push(start);
    set.add(start);
    System.out.println(start.value);
    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      for (Node next : cur.nexts) {
        if (!set.contains(next)) {
          stack.push(cur);
          stack.push(next);
          set.add(next);
          System.out.println(next.value);
          break;
        }
      }
    }
  }

}
