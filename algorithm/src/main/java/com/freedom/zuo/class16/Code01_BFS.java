package com.freedom.zuo.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 宽度优先遍历
 *
 * @author tobebetter9527
 * @create 2022/01/29 22:30
 */
public class Code01_BFS {

  public static void bfs(Node start) {
    if (start == null) {
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    Set<Node> set = new HashSet<>();
    queue.add(start);
    set.add(start);
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      System.out.println(node.value);
      for (Node next : node.nexts) {
        if (set.contains(next)) {
          queue.add(start);
          set.add(next);
        }
      }
    }
  }

}
