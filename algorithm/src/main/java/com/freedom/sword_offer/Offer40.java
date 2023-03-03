package com.freedom.sword_offer;

import java.util.PriorityQueue;

public class Offer40 {

  public int[] getLeastNumbers(int[] arr, int k) {
    if (k == 0) {
      return new int[0];
    }
    PriorityQueue<Integer> heap = new PriorityQueue<>(k, (x, y) -> y.compareTo(x));
    for (int i = 0; i < k; i++) {
      heap.add(arr[i]);
    }
    for (int i = k; i < arr.length; i++) {
      if (arr[i] < heap.peek()) {
        heap.poll();
        heap.add(arr[i]);
      }
    }

    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
      ans[i] = heap.poll();
    }
    return ans;
  }

}
