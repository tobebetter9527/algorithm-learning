package com.freedom.zuo1.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 点
 *
 * @author tobebetter9527
 * @create 2022/12/22 21:08
 */
public class Node {

  public int value;
  public int inDegree;
  public int outDegree;
  public List<Node> nexts;
  public List<Edge> edges;

  public Node(int value) {
    this.value = value;
    inDegree = 0;
    outDegree = 0;
    nexts = new LinkedList<>();
    edges = new LinkedList<>();
  }
}
