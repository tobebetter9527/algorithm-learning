package com.freedom.zuo.class06_heap_sort;

import java.util.Arrays;

public class Code03_HeapSort {

  public static void heapSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int length = arr.length;
    buildHeap(arr, length);

    int k = length - 1;
    while (k > 0) {
      swap(arr, 0, k);
      heapify(arr, k, 0);
      k--;
    }
  }

  private static void buildHeap(int[] arr, int n) {
    // 叶子节点只有自己，不需要处理
    for (int i = ((n - 1) - 1) / 2; i >= 0; i--) {
      // 从节点往下进行堆化
      heapify(arr, n, i);
    }
  }

  private static void heapify(int[] arr, int n, int i) {
    while (true) {
      int maxPos = i;
      if ((2 * i + 1) < n && arr[i] < arr[2 * i + 1]) {
        maxPos = 2 * i + 1;
      }
      if ((2 * i + 2) < n && arr[maxPos] < arr[2 * i + 2]) {
        maxPos = 2 * i + 2;
      }
      if (maxPos == i) {
        break;
      }
      swap(arr, maxPos, i);
      i = maxPos;
    }
  }

  private static void swap(int[] arr, int j, int i) {
    int temp = arr[j];
    arr[j] = arr[i];
    arr[i] = temp;
  }

  public static void main(String[] args) {
    int range = 100;
    int maxSize = 100;
    int testTimes = 10000000;
    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateArray(range, maxSize);
      int[] copyArr = copyArray(arr);
      heapSort(arr);
      Arrays.sort(copyArr);
      if (!isEqual(arr, copyArr)) {
        System.out.println("wrong");
        printArray(arr);
      }
    }
  }

  private static void printArray(int[] arr) {
    if (arr != null) {
      for (int i = 0; i < arr.length; i++) {
        System.out.print(i + " ");
      }
    }
    System.out.println("---------");
  }

  private static boolean isEqual(int[] arr, int[] copyArr) {
    if (arr == null && copyArr != null) {
      return false;
    }
    if (arr != null && copyArr == null) {
      return false;
    }
    if (arr == null && arr == null) {
      return true;
    }
    if (arr.length != copyArr.length) {
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != copyArr[i]) {
        return false;
      }
    }
    return true;
  }

  private static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int length = arr.length;
    int[] copyArr = new int[length];
    for (int i = 0; i < length; i++) {
      copyArr[i] = arr[i];
    }
    return copyArr;
  }

  private static int[] generateArray(int range, int maxSize) {
    int length = (int) (Math.random() * maxSize);
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
    }

    return arr;
  }
}
