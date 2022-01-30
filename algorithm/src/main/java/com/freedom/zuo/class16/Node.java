package com.freedom.zuo.class16;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/01/29 21:57
 */
public class Node {

  public int value;
  public int in;
  public int out;
  public ArrayList<Node> nexts;
  public ArrayList<Edge> edges;

  public Node(int value) {
    this.value = value;
    in = 0;
    out = 0;
    nexts = new ArrayList<>();
    edges = new ArrayList<>();
  }
}
