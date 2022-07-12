package com.freedom.zuo.class17_graph_recursive;

import com.freedom.zuo.class17_graph_recursive.graph.Edge;
import com.freedom.zuo.class17_graph_recursive.graph.Node;
import java.util.HashMap;

/**
 * 最小路径算法：改进
 *
 * @author tobebetter9527
 * @create 2022/07/12 21:25
 */
public class Code01_Dijkstra {

  /**
   * 改进后的dijkstra算法
   * <p>
   * 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
   */
  public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
    HashMap<Node, Integer> result = new HashMap<>();

    // 处理头节点
    NodeHeap nodeHeap = new NodeHeap(size);
    nodeHeap.addOrUpdateOrIgnore(head, 0);

    while (!nodeHeap.isEmpty()) {
      // 弹出距离最小的点
      NodeRecord record = nodeHeap.pop();
      Node cur = record.node;
      int distance = record.distance;
      // 处理每条边的节点的距离
      for (Edge edge : cur.edges) {
        nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
      }

      result.put(cur, distance);
    }
    return result;
  }

  static class NodeHeap {

    // 堆！
    private Node[] nodes;
    // node -> 堆上的什么位置？
    private HashMap<Node, Integer> heapIndexMap;
    // 节点距离
    private HashMap<Node, Integer> distanceMap;
    private int size;

    public NodeHeap(int size) {
      nodes = new Node[size];
      heapIndexMap = new HashMap<>();
      distanceMap = new HashMap<>();
      size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
    // 判断要不要更新，如果需要的话，就更新
    public void addOrUpdateOrIgnore(Node node, int distance) {
      // 如果还在堆，更新距离
      if (inHeap(node)) { // update
        distanceMap.put(node, Math.min(distanceMap.get(node), distance));
        insertHeapify(node, heapIndexMap.get(node));
      }
      // 如果没有入过堆
      if (!isEntered(node)) { // add
        nodes[size] = node;
        heapIndexMap.put(node, size);
        distanceMap.put(node, distance);
        insertHeapify(node, size++);
      }
      // ignore
    }

    public NodeRecord pop() {
      NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
      swap(0, size - 1); // 0 > size - 1    size - 1 > 0
      heapIndexMap.put(nodes[size - 1], -1);
      distanceMap.remove(nodes[size - 1]);
      // free C++同学还要把原本堆顶节点析构，对java同学不必
      nodes[size - 1] = null;
      heapify(0, --size);
      return nodeRecord;
    }

    private void insertHeapify(Node node, int index) {
      while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
        swap(index, (index - 1) / 2);
        index = (index - 1) / 2;
      }
    }

    private void heapify(int index, int size) {
      int left = index * 2 + 1;
      while (left < size) {
        int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
            ? left + 1 : left;

        smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
        if (smallest == index) {
          break;
        }

        swap(smallest, index);
        index = smallest;
        left = index * 2 + 1;
      }
    }

    /**
     * 判断节点是否曾经入过堆
     */
    private boolean isEntered(Node node) {
      return heapIndexMap.containsKey(node);
    }

    /**
     * 判断节点入过堆，并且还在堆上
     */
    private boolean inHeap(Node node) {
      return isEntered(node) && heapIndexMap.get(node) != -1;
    }

    private void swap(int index1, int index2) {
      heapIndexMap.put(nodes[index1], index2);
      heapIndexMap.put(nodes[index2], index1);
      Node tmp = nodes[index1];
      nodes[index1] = nodes[index2];
      nodes[index2] = tmp;
    }
  }

  static class NodeRecord {

    public Node node;
    public int distance;

    public NodeRecord(Node node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }
}
