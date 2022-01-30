package com.freedom.zuo.class16;

import java.util.ArrayList;

/**
 * 图的节点
 *
 * @author tobebetter9527
 * @create 2022/01/29 21:57
 */
public class Node {

  /**
   * 值
   */
  public int value;
  /**
   * 入度
   */
  public int in;
  /**
   * 出度
   */
  public int out;
  /**
   * 相邻的点
   */
  public ArrayList<Node> nexts;
  /**
   * 相邻的边
   */
  public ArrayList<Edge> edges;

  public Node(int value) {
    this.value = value;
    in = 0;
    out = 0;
    nexts = new ArrayList<>();
    edges = new ArrayList<>();
  }
}
