package com.freedom.zuo1.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图
 *
 * @author tobebetter9527
 * @create 2022/12/22 21:14
 */
public class Graph {

  public Map<Integer, Node> nodes;

  public Set<Edge> edges;

  public Graph() {
    this.nodes = new HashMap<>();
    this.edges = new HashSet<>();
  }


  /**
   * matrix 所有的边，N*3 的矩阵
   * <p>
   * [weight, from节点上面的值，to节点上面的值]
   * <p>
   * [ 5 , 0 , 7]
   */
  public static Graph createGraph(int[][] matrix) {
    Graph graph = new Graph();
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      int weight = matrix[i][0];
      int fromValue = matrix[i][0];
      int toValue = matrix[i][0];

      if (!graph.nodes.containsKey(fromValue)) {
        graph.nodes.put(fromValue, new Node(fromValue));
      }
      if (!graph.nodes.containsKey(toValue)) {
        graph.nodes.put(toValue, new Node(toValue));
      }

      Node fromNode = graph.nodes.get(fromValue);
      Node toNode = graph.nodes.get(toValue);
      Edge newEdge = new Edge(weight, fromNode, toNode);

      fromNode.nexts.add(toNode);
      fromNode.outDegree++;
      fromNode.edges.add(newEdge);

      toNode.inDegree++;

      graph.edges.add(newEdge);
    }
    return graph;
  }
}
