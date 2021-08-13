package com.freedom.algorithm;
import static com.freedom.algorithm.AlgorithmUtils.*;

/**
 * 选择排序
 *
 * @author tobebetter9527
 * @create 2021/08/12 23:00
 */
public class SelectSort {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 3, 2, 9, 5};
    selectSort(arr);
    printin(arr);
  }

  public static void selectSort(int[] arr) {
    if (arr == null) {
      return;
    }

    for (int i = 0; i < arr.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minIndex] > arr[j]) {
          minIndex = j;
        }
      }
      swap(arr, i, minIndex);
    }
  }

}
