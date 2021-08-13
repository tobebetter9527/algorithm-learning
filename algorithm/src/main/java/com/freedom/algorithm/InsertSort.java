package com.freedom.algorithm;

import static com.freedom.algorithm.AlgorithmUtils.*;


/**
 * 插入排序
 *
 * @author tobebetter9527
 * @create 2021/08/12 23:00
 */
public class InsertSort {

  public static void main(String[] args) {
    int[] arr = new int[]{9, 3, 2, 9, 1};
    insertSort(arr);
    printin(arr);
  }

  public static void insertSort(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length - 1; i++) {
      int j = i + 1;
      while (j >= 1 && arr[j - 1] > arr[j]) {
        swap(arr, j, j - 1);
        j--;
      }
    }
  }


}
