package com.freedom.zuo.class17_graph_recursive.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    /**
     * 点的值和点的映射关系
     */
    public HashMap<Integer, Node> nodes;
    /**
     * 边的结合
     */
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}

