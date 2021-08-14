package com.freedom.algorithm.sort;

import static com.freedom.algorithm.util.AlgorithmUtils.*;

import java.util.Arrays;


/**
 * 插入排序
 *
 * @author tobebetter9527
 * @create 2021/08/12 23:00
 */
public class InsertSort {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int testTimes = 1000000;
    int maxSize = 100;
    int maxValue = 1000;
    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      int[] arr1 = copyArray(arr);
      int[] arr2 = copyArray(arr);
      insertSort(arr1);
      Arrays.sort(arr2);
      if (!isEqual(arr1, arr2)) {
        println(arr);
        println(arr1);
        println(arr2);
      }
    }
    long end = System.currentTimeMillis();
    System.out.println((end - start) / 1000);
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
