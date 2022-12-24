package com.freedom.zuo1.graph;

/**
 * 边
 *
 * @author tobebetter9527
 * @create 2022/12/22 21:07
 */
public class Edge {

  public int weight;
  public Node from;
  public Node to;

  public Edge(int weight, Node from, Node to) {
    this.weight = weight;
    this.from = from;
    this.to = to;
  }
}
