package com.freedom.zuo.class16_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * https://www.lintcode.com/problem/topological-sorting
 *
 * @author tobebetter9527
 * @create 2022/07/10 22:04
 */
public class Code03_TopologicalOrderBFS {


  public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    // value 为入度
    Map<DirectedGraphNode, Integer> map = new HashMap<>();
    for (DirectedGraphNode node : graph) {
      map.put(node, 0);
    }

    // 计算每个顶点的入度
    for (DirectedGraphNode node : graph) {
      for (DirectedGraphNode neighbor : node.neighbors) {
        map.put(neighbor, map.get(neighbor) + 1);
      }
    }

    // 找到入度为0的顶点
    Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
    for (Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 0) {
        zeroInQueue.add(entry.getKey());
      }
    }
    // 返回拓扑排序，可能有好几个，返回一个即可
    ArrayList<DirectedGraphNode> ans = new ArrayList<>();
    while (!zeroInQueue.isEmpty()) {
      DirectedGraphNode cur = zeroInQueue.poll();
      ans.add(cur);

      // 遍历到入度减1
      for (DirectedGraphNode neighbor : cur.neighbors) {
        map.put(neighbor, map.get(neighbor) - 1);
        if (map.get(neighbor) == 0) {
          zeroInQueue.add(neighbor);
        }
      }
    }

    return ans;
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
