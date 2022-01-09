package com.freedom.algorithm.sort;

import static com.freedom.algorithm.util.AlgorithmUtils.println;
import static com.freedom.algorithm.util.AlgorithmUtils.swap;

/**
 * quick sort
 *
 * @author tobebetter9527
 * @create 2022/01/09 19:55
 */
public class QuickSort {

  public static void sort(int[] arr, int low, int high) {
    if (arr == null || low >= high) {
      return;
    }

    int partition = partition(arr, low, high);

    sort(arr, 0, partition - 1);
    sort(arr, partition + 1, high);
  }


  public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int index = low - 1;

    for (int j = low; j <= high - 1; j++) {
      if (arr[j] < pivot) {
        index++;
        swap(arr, index, j);
      }
    }

    swap(arr, index + 1, high);
    return index + 1;
  }

  public static void main(String[] args) {
    int[] arr = {10,20};
    sort(arr, 0, arr.length - 1);
    println(arr);
  }


}
