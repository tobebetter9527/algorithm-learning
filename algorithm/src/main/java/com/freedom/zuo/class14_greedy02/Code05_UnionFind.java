package com.freedom.zuo.class14_greedy02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Disjoint-set data structure
 *
 * @author tobebetter9527
 * @create 2022/01/29 10:57
 */
public class Code05_UnionFind {

  public static class Node<V> {

    private V value;

    public Node(V value) {
      this.value = value;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

  public static class UnionFind<V> {

    Map<V, Node<V>> nodes;
    Map<Node<V>, Node<V>> parents;
    Map<Node<V>, Integer> sizeMap;

    public UnionFind(List<V> values) {
      nodes = new HashMap<>();
      parents = new HashMap<>();
      sizeMap = new HashMap<>();
      for (V value : values) {
        Node<V> node = new Node<>(value);
        nodes.put(value, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    public Node<V> findFather(Node<V> cur) {
      Stack<Node> stack = new Stack<>();

      while (cur != parents.get(cur)) {
        stack.push(cur);
        cur = parents.get(cur);
      }

      while (!stack.isEmpty()) {
        parents.put(stack.pop(), cur);
      }

      return cur;
    }

    public boolean isSameSet(V a, V b) {
      return findFather(new Node<>(a)) == findFather(new Node<>(b));
    }

    public void union(V a, V b) {
      Node<V> aNode = findFather(new Node<>(a));
      Node<V> bNode = findFather(new Node<>(b));
      if (aNode != bNode) {
        Integer aSize = sizeMap.get(aNode);
        Integer bSize = sizeMap.get(bNode);
        Node<V> big = aSize >= bSize ? aNode : bNode;
        Node<V> small = big == aNode ? bNode : aNode;
        parents.put(small, big);
        sizeMap.put(big, aSize + bSize);
        sizeMap.remove(small);
      }
    }

    public int sets() {
      return sizeMap.size();
    }
  }
}