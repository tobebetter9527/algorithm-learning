package com.freedom.leetcode.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any
 * order.
 *
 * @author tobebetter9527
 * @create 2022/06/23 20:53
 */
public class Problem347_TopKFrequentElements {

  /**
   * time complexity is O(nlogk),space complexity is O(n).
   *
   * @param nums
   * @param k
   * @return
   */
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // 创建大小为k的小根堆
    PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2) -> o1[1] - o2[1]);
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      Integer num = entry.getKey();
      Integer count = entry.getValue();
      // 先填满heap
      if (heap.size() == k) {
        if (heap.peek()[1] < count) {
          heap.poll();
          heap.add(new int[]{num, count});
        }
      } else {
        heap.add(new int[]{num, count});
      }
    }

    // 有可能k比heap的size大。
    int[] arr = new int[heap.size()];
    for (int i = 0; i < k; i++) {
      arr[i] = heap.poll()[0];
    }

    return arr;
  }
}
