package com.freedom.zuo1.union_find;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * union find.
 *
 * @author tobebetter9527
 * @create 2022/12/19 20:35
 */
public class UnionFind<T> {

  private Map<T, Node<T>> nodes;
  private Map<Node<T>, Node<T>> parents;
  private Map<Node<T>, Integer> sizeMap;

  public UnionFind(List<T> values) {
    nodes = new HashMap<>(values.size());
    parents = new HashMap<>(values.size());
    sizeMap = new HashMap<>(values.size());
    for (T value : values) {
      Node node = new Node(value);
      nodes.put(value, node);
      parents.put(node, node);
      sizeMap.put(node, 1);
    }
  }

  public Node<T> findFather(Node<T> cur) {
    Stack<Node<T>> stack = new Stack<>();
    while (cur != parents.get(cur)) {
      stack.push(cur);
      cur = parents.get(cur);
    }

    while (!stack.isEmpty()) {
      parents.put(stack.pop(), cur);
    }
    return cur;
  }

  public boolean isSameSet(T a, T b) {
    return findFather(nodes.get(a)) == findFather(nodes.get(b));
  }

  public void union(T a, T b) {
    Node<T> aNode = findFather(nodes.get(a));
    Node<T> bNode = findFather(nodes.get(b));
    if (aNode != bNode) {
      Integer aSize = sizeMap.get(aNode);
      Integer bSize = sizeMap.get(bNode);
      Node<T> bigNode = aSize > bSize ? aNode : bNode;
      Node<T> smallNode = bigNode == aNode ? bNode : aNode;
      parents.put(smallNode, bigNode);
      sizeMap.put(bigNode, aSize + bSize);
      sizeMap.remove(smallNode);
    }
  }

  private static class Node<T> {

    T value;

    public Node(T value) {
      this.value = value;
    }
  }

}
