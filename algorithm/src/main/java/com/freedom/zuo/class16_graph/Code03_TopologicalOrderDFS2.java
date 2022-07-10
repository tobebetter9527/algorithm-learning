package com.freedom.zuo.class16_graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://www.lintcode.com/problem/topological-sorting
 *
 * @author tobebetter9527
 * @create 2022/07/10 21:27
 */
public class Code03_TopologicalOrderDFS2 {

  /**
   * 总体的思想是：根据点的深度，从高到低
   * <p>
   * 拓扑序：有向图，无环
   *
   * @param graph
   * @return 返回拓扑序
   */
  public static List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
    Map<DirectedGraphNode, Record> map = new HashMap<>();
    for (DirectedGraphNode node : graph) {
      f(node, map);
    }

    List<Record> records = map.values().stream().collect(Collectors.toList());
    records.sort(new MyComparator());

    return records.stream().map(x -> x.node).collect(Collectors.toList());
  }

  private static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
    if (map.containsKey(node)) {
      return map.get(node);
    }

    int nodes = 0;
    for (DirectedGraphNode neighbor : node.neighbors) {
      nodes += f(neighbor, map).nodes;
    }

    Record record = new Record(node, nodes + 1);
    map.put(node, record);
    return record;
  }

  static class MyComparator implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
      return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
    }
  }

  static class Record {

    // 节点
    DirectedGraphNode node;
    // 节点之后的个数，可以重复算
    int nodes;

    public Record(DirectedGraphNode node, int nodes) {
      this.node = node;
      this.nodes = nodes;
    }
  }

  static class DirectedGraphNode {

    public int label;
    public List<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }
  }
}
