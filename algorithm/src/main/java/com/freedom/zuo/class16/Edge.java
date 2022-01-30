package com.freedom.zuo.class16;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/01/29 21:58
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
