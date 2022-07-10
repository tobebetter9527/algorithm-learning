package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Node;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth-First-Search
 *
 * @author tobebetter9527
 * @create 2022/07/10 16:55
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

    Node cur;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      System.out.println(cur.value);
      //      if (set.add(cur)) {
      //        for (Node next : cur.nexts) {
      //          queue.add(next);
      //        }
      //      }

      if (!set.contains(cur)) {
        set.add(cur);
        for (Node next : cur.nexts) {
          queue.add(next);
        }
      }
    }
  }

}
