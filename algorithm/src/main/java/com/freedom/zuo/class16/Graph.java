package com.freedom.zuo.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author tobebetter9527
 * @create 2022/01/29 22:00
 */
public class Graph {

  /**
   * 节点结合，key节点的值
   */
  public HashMap<Integer, Node> nodes;
  /**
   * 边集合
   */
  public HashSet<Edge> edges;

  public Graph() {
    nodes = new HashMap<>();
    edges = new HashSet<>();
  }
}
