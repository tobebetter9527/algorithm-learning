package com.freedom.zuo1.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * BFS
 *
 * @author tobebetter9527
 * @create 2022/12/22 21:29
 */
public class BFS {

  public static void bfs(Node start) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(start);
    Set<Node> set = new HashSet<>();
    while (!queue.isEmpty()) {
      Node poll = queue.poll();
      System.out.println(poll.value);
      if (!set.contains(poll)) {
        set.add(poll);
        for (Node next : poll.nexts) {
          queue.add(next);
        }
      }
    }
  }
}
