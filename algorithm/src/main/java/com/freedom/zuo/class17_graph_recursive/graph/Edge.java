package com.freedom.zuo.class17_graph_recursive.graph;

public class Edge {

    /**
     * 权重
     */
    public int weight;
    /**
     * 从哪里开始
     */
    public Node from;
    /**
     * 去哪里
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
