package com.freedom.wangzheng;

/**
 * heap sort
 *
 * @author tobebetter9527
 * @create 2022/06/18 21:40
 */
public class Class28_Heap {

  static int[] arr;
  // 存储最大个数
  static int n;
  // 当前存储的个数
  static int size;

  public Class28_Heap(int capacity) {
    arr = new int[capacity];
    n = capacity;
  }

  /**
   * 构建大顶堆
   *
   * @param value
   */
  public static void insert(int value) {
    if (size > n) {
      return;
    }

    arr[size] = value;
    size++;

    int i = size - 1;
    while (i >= 0 && arr[i] > arr[(i - 1) / 2]) {
      swap(arr, i, (i - 1) / 2);
      i = (i - 1) / 2;
    }
  }

  public static void removeMax() {
    if (size == 0) {
      return;
    }
    arr[0] = arr[size - 1];
    // 该位置的值需要移除
    arr[size - 1] = 0;
    size--;
    heapify(arr, size, 0);
  }

  private static void heapify(int[] arr, int size, int i) {
    while (true) {
      int maxPos = i;
      if ((i * 2 + 1) < size && arr[i] < arr[(i * 2 + 1)]) {
        maxPos = 2 * i + 1;
      }
      if ((i * 2 + 2) < size && arr[maxPos] < arr[(i * 2 + 2)]) {
        maxPos = 2 * i + 2;
      }
      // 加入i节点的值比左右节点都大，就可以终止了
      if (maxPos == i) {
        break;
      }
      swap(arr, maxPos, i);
      // 继续往下堆化
      i = maxPos;
    }
  }

  public static void buildHeap(int[] arr) {
    int n = arr.length;
    for (int i = ((n - 1) - 1) / 2; i >= 0; i--) {
      heapify(arr, n, i);
    }
  }

  public static void sort(int[] arr) {
    buildHeap(arr);
    int k = arr.length - 1;
    while (k > 0) {
      swap(arr, 0, k);
      k--;
      heapify(arr, k + 1, 0);
    }
  }


  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 10, 5, 6, 3,3};
    sort(arr);
    System.out.println("");
  }
}
