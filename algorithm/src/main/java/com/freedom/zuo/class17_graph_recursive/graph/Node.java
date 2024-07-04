package com.freedom.zuo.class17_graph_recursive.graph;

import java.util.ArrayList;

/**
 * 点结构的描述
 */
public class Node {
    /**
     * 点的值
     */
    public int value;
    /**
     * 点的入度
     */
    public int in;
    /**
     * 点的出度
     */
    public int out;
    /**
     * 从点出发，直接相邻的点
     */
    public ArrayList<Node> nexts;
    /**
     * 从点出发的边
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
