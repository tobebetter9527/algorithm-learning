package com.freedom.zuo.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 图的拓扑排序算法，根据node的入度排
 *
 * @author tobebetter9527
 * @create 2022/01/30 14:41
 */
public class Code03_TopologySort {

  // directed graph and no loop
  public static List<Node> sortedTopology(Graph graph) {
    Map<Node, Integer> inMap = new HashMap<>(graph.nodes.values().size());
    Queue<Node> zeroInQueue = new LinkedList<>();
    for (Node value : graph.nodes.values()) {
      inMap.put(value, value.in);
      if (value.in == 0) {
        zeroInQueue.add(value);
      }
    }

    List<Node> result = new ArrayList<>(graph.nodes.values().size());
    while (!zeroInQueue.isEmpty()) {
      Node cur = zeroInQueue.poll();
      result.add(cur);
      for (Node next : cur.nexts) {
        inMap.put(next, inMap.get(next) - 1);
        if (inMap.get(next) == 0) {
          zeroInQueue.add(next);
        }
      }
    }
    return result;
  }

}
