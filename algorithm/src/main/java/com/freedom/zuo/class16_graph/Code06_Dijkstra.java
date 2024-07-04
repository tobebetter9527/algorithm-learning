package com.freedom.zuo.class16_graph;

import com.freedom.zuo.class16_graph.graph.Edge;
import com.freedom.zuo.class16_graph.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 最小路径算法
 *
 * @author tobebetter9527
 * @create 2022/07/11 22:41
 */
public class Code06_Dijkstra {

    public static Map<Node, Integer> dijkstra1(Node from) {
        // from节点到该节点的距离，如果distanceMap.get(node) == null ,表示距离正无穷
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);

        // 已经处理过的点
        Set<Node> processedSet = new HashSet<>();

        Node minDistanceNode = getMinDistanceNode(distanceMap, processedSet);
        while (minDistanceNode != null) {
            // 原来点的距离
            Integer distance = distanceMap.get(minDistanceNode);

            // 所有的边
            for (Edge edge : minDistanceNode.edges) {
                Node toNode = edge.to;
                // 如果没有，表示正无穷，只需新增即可
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, edge.weight + distance);
                } else {
                    // 跟原来的值比较，取比较小的值
                    distanceMap.put(toNode, Math.min(edge.weight + distance, distanceMap.get(toNode)));
                }
            }
            processedSet.add(minDistanceNode);

            minDistanceNode = getMinDistanceNode(distanceMap, processedSet);
        }
        return distanceMap;
    }

    /**
     * 在未处理过的点中，取距离最小的点
     */
    private static Node getMinDistanceNode(Map<Node, Integer> distanceMap, Set<Node> processedSet) {
        int minDistance = Integer.MAX_VALUE;
        Node minDistanceNode = null;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            if (!processedSet.contains(node) && minDistance > entry.getValue()) {
                minDistanceNode = node;
                minDistance = entry.getValue();
            }
        }
        return minDistanceNode;
    }

}
