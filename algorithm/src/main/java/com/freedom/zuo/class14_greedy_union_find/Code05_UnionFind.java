package com.freedom.zuo.class14_greedy_union_find;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * union find
 *
 * @author tobebetter9527
 * @create 2022/07/09 9:31
 */
public class Code05_UnionFind {

  static class UnionFind<T> {

    /**
     * 集合和其包装类的关系
     */
    Map<T, Node<T>> nodes;
    /**
     * 保存集合和关联的父集合关系
     */
    Map<Node<T>, Node<T>> parents;
    /**
     * 集合数量关系
     */
    Map<Node<T>, Integer> sizeMap;

    public UnionFind(List<T> values) {
      nodes = new HashMap<>();
      parents = new HashMap<>();
      sizeMap = new HashMap<>();
      for (T value : values) {
        Node node = new Node(value);
        nodes.put(value, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    /**
     * 给定节点，一直往上找父节点，同时把那条链上的节点全部关联到最上的节点
     */
    public Node<T> findFather(Node<T> cur) {
      Stack<Node<T>> stack = new Stack();
      // 寻找父节点及之上的节点
      while (cur != parents.get(cur)) {
        stack.push(cur);
        cur = parents.get(cur);
      }
      // 全部关联最上的节点
      while (!stack.isEmpty()) {
        parents.put(stack.pop(), cur);
      }
      return cur;
    }

    /**
     * 判断两个是不是在同个集合，看是否能找到相同的父节点
     */
    public boolean isSameSet(T a, T b) {
      return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    /**
     * a和b合并
     */
    public void union(T a, T b) {
      Node<T> aNode = findFather(nodes.get(a));
      Node<T> bNode = findFather(nodes.get(b));
      if (aNode != bNode) {
        Integer aSize = sizeMap.get(aNode);
        Integer bSize = sizeMap.get(bNode);
        Node<T> bigNode = aSize >= bSize ? aNode : bNode;
        Node<T> smallNode = aSize < bSize ? aNode : bNode;
        parents.put(smallNode, bigNode);
        sizeMap.put(bigNode, aSize + bSize);
        sizeMap.remove(smallNode);
      }
    }

    public int sets() {
      return sizeMap.size();
    }

  }


  static class Node<T> {

    T value;

    public Node(T value) {
      this.value = value;
    }
  }
}
