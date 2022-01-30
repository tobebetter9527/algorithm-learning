package com.freedom.zuo.class16;

/**
 * 边
 *
 * @author tobebetter9527
 * @create 2022/01/29 21:58
 */
public class Edge {

  /**
   * 权重
   */
  public int weight;
  /**
   * 开始节点
   */
  public Node from;
  /**
   * 结束节点
   */
  public Node to;

  public Edge(int weight, Node from, Node to) {
    this.weight = weight;
    this.from = from;
    this.to = to;
  }

}
