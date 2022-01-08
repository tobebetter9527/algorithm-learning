package com.freedom.algorithm.sort;

import static com.freedom.algorithm.util.AlgorithmUtils.println;
import static com.freedom.algorithm.util.AlgorithmUtils.swap;

/**
 * heap sort
 *
 * @author tobebetter9527
 * @create 2022/01/08 17:57
 */
public class HeapSort {

  public static void sort(int[] arr) {
    if (arr == null) {
      return;
    }

    int n = arr.length;
    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(arr, n, i);
    }

    // One by one extract an element from heap
    for (int i = n - 1; i > 0; i--) {
      // Move current root to end
      swap(arr, 0, i);
      // call max heapify on the reduced heap
      heapify(arr, i, 0);
    }
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  static void heapify(int arr[], int n, int i) {
    // Initialize largest as root
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    // If left child is larger than root
    if (left < n && arr[left] > arr[largest]) {
      largest = left;
    }

    // If right child is larger than largest so far
    if (right < n && arr[right] > arr[largest]) {
      largest = right;
    }

    // If largest is not root
    if (largest != i) {
      swap(arr, i, largest);
      // Recursively heapify the affected sub-tree
      heapify(arr, n, largest);
    }
  }

  public static void main(String[] args) {
    int[] arr = {4, 10, 3, 5, 1};
    sort(arr);
    println(arr);
  }


}
