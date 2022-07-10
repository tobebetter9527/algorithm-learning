package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Graph;
import com.freedom.zuo.class16_graph.graph.Node;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 图拓扑排序算法
 * <p>
 * 1）在图中找到所有入度为0的点输出
 * <p>
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * <p>
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 * <p>
 * 要求：有向图且其中没有环
 *
 * @author tobebetter9527
 * @create 2022/07/10 18:09
 */
public class Code03_TopologySort {

  public static List<Node> sortedTopology(Graph graph) {
    Map<Node, Integer> map = new HashMap<>();
    Queue<Node> zeroInQueue = new LinkedList<>();
    for (Node value : graph.nodes.values()) {
      map.put(value, value.in);
      if (value.in == 0) {
        zeroInQueue.add(value);
      }
    }

    List<Node> list = new LinkedList<>();
    Node cur;
    while (!zeroInQueue.isEmpty()) {
      cur = zeroInQueue.poll();
      list.add(cur);
      for (Node next : cur.nexts) {
        map.put(next, map.get(next) - 1);
        if (map.get(next) == 0) {
          zeroInQueue.add(next);
        }
      }
    }

    return list;
  }

}
