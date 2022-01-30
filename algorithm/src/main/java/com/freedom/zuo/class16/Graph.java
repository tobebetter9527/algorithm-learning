package com.freedom.zuo.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/01/29 22:00
 */
public class Graph {

  public HashMap<Integer, Node> nodes;
  public HashSet<Edge> edges;

  public Graph() {
    nodes = new HashMap<>();
    edges = new HashSet<>();
  }
}
