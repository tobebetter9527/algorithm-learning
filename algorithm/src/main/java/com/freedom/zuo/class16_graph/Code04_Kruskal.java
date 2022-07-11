package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Edge;
import com.freedom.zuo.class16_graph.graph.Graph;
import com.freedom.zuo.class16_graph.graph.Node;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 最小生成树：无向有权图，n个点，用n-1条边连起来，权和最小
 *
 * @author tobebetter9527
 * @create 2022/07/11 20:34
 */
public class Code04_Kruskal {

  /**
   * 主要思想：贪心和并查集。从最小权的边开始，检查这个边的两个节点是不是在同一个集合，如果是，放弃，如果不是，则合并集合。
   *
   * @param graph
   */
  public static Set<Edge> kruskalMST(Graph graph) {
    UnionFind uf = new UnionFind();
    uf.makeSets(graph.nodes.values());

    // 小根堆
    PriorityQueue<Edge> heap = new PriorityQueue<>(new MyComparator());
    for (Edge edge : graph.edges) {
      heap.add(edge);
    }

    Set<Edge> ans = new HashSet<>();
    Edge cur;
    while (!heap.isEmpty()) {
      cur = heap.poll();
      if (!uf.isSameSet(cur.from, cur.to)) {
        ans.add(cur);
        uf.union(cur.from, cur.to);
      }
    }

    return ans;
  }

  static class UnionFind {

    Map<Node, Node> parentsMap;
    Map<Node, Integer> sizeMap;

    public UnionFind() {
      parentsMap = new HashMap<>();
      sizeMap = new HashMap<>();
    }

    public void makeSets(Collection<Node> values) {
      parentsMap.clear();
      sizeMap.clear();
      for (Node node : values) {
        parentsMap.put(node, node);
        sizeMap.put(node, 1);
      }
    }


    public boolean isSameSet(Node from, Node to) {

      return findFather(from) == findFather(to);
    }

    private Node findFather(Node cur) {
      Stack<Node> stack = new Stack<>();
      while (cur != parentsMap.get(cur)) {
        stack.push(cur);
        cur = parentsMap.get(cur);
      }

      while (!stack.isEmpty()) {
        parentsMap.put(stack.pop(), cur);
      }

      return cur;
    }

    public void union(Node a, Node b) {
      Node aDaddy = findFather(a);
      Node bDaddy = findFather(b);
      if (aDaddy != bDaddy) {
        Integer aSize = sizeMap.get(aDaddy);
        Integer bSize = sizeMap.get(bDaddy);
        if (aSize >= bSize) {
          parentsMap.put(bDaddy, aDaddy);
          sizeMap.put(aDaddy, aSize + bSize);
          sizeMap.remove(bDaddy);
        } else {
          parentsMap.put(aDaddy, bDaddy);
          sizeMap.put(bDaddy, aSize + bSize);
          sizeMap.remove(aDaddy);
        }
      }
    }
  }

  static class MyComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
      return o1.weight - o2.weight;
    }
  }


}
