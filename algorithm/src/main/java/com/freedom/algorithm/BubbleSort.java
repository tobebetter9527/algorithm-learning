package com.freedom.algorithm;

import static com.freedom.algorithm.AlgorithmUtils.*;

/**
 * 冒泡排序
 *
 * @author tobebetter9527
 * @create 2021/08/12 23:00
 */
public class BubbleSort {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 3, 2, 9, 5};
    bubbleSort(arr);
    printin(arr);
  }

  public static void bubbleSort(int[] arr) {
    if (arr == null) {
      return;
    }

    for (int end = arr.length - 1; end >= 0; end--) {
      for (int i = 0; i < end; i++) {
        if (arr[i] > arr[i + 1]) {
          swap(arr, i, i + 1);
        }
      }
    }
  }


}
