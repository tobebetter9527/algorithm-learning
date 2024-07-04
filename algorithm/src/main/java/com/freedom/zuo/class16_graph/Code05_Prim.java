package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Edge;
import com.freedom.zuo.class16_graph.graph.Graph;
import com.freedom.zuo.class16_graph.graph.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树：无向有权图，n个点，用n-1条边连起来，权和最小
 *
 * @author tobebetter9527
 * @create 2022/07/11 21:28
 */
public class Code05_Prim {

    public static Set<Edge> primMST(Graph graph) {

        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
        Set<Node> set = new HashSet<>();

        Set<Edge> ans = new HashSet<>();
        // 从任意一个节点出发
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                // 节点所有的边加入小根堆
                for (Edge edge : node.edges) {
                    heap.add(edge);
                }

                while (!heap.isEmpty()) {
                    // 弹出最小边
                    Edge edge = heap.poll();
                    Node toNode = edge.to;
                    // 如果边的to点，没有处理过，则边加入ans，并且将to点的边加入小根堆
                    if (!set.contains(toNode)) {
                        ans.add(edge);
                        set.add(toNode);
                        for (Edge edge1 : toNode.edges) {
                            heap.add(edge1);
                        }
                    }
                }
            }
            // 图可能是深林，如果不是深林，可以break;
            // break;
        }

        return ans;
    }


    static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

}
