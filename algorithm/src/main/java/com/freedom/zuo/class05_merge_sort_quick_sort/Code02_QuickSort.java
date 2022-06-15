package com.freedom.zuo.class05_merge_sort_quick_sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Code02_QuickSort {

  public static void quickSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }

    process(arr, 0, arr.length - 1);
  }

  private static void process(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int pivot = partition(arr, left, right);
    process(arr, left, pivot - 1);
    process(arr, pivot + 1, right);
  }

  private static int partition(int[] arr, int left, int right) {
    int pivot = right;
    int index = left;
    int i = left - 1;
    while (index < right) {
      if (arr[index] <= arr[pivot]) {
        swap(arr, index, ++i);
      }
      index++;
    }
    swap(arr, ++i, pivot);
    return i;
  }

  private static void swap(int[] arr, int index, int i) {
    int temp = arr[index];
    arr[index] = arr[i];
    arr[i] = temp;
  }

  public static void main(String[] args) {
//    int rang = 100;
//    int maxSize = 100;
//    int testTime = 10000000;
//    for (int i = 0; i < testTime; i++) {
//      int[] arr = generateRandomArray(rang, maxSize);
//      int[] copyArr = copy(arr);
//      quickSort(arr);
//      Arrays.sort(copyArr);
//      if (!isEqual(arr, copyArr)) {
//        System.out.println("wrong");
//      }
//    }
//    System.out.println("done!");

    int[] arr = {3, 2, 1, 3, 6, 4, 3};
    quickSort(arr);
  }

  private static int[] generateRandomArray(int rang, int maxSize) {
    int size = (int) (Math.random() * (maxSize + 1));
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
    }
    return arr;
  }

  private static int[] copy(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] copyArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      copyArr[i] = arr[i];
    }

    return copyArr;
  }

  // for test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }
}
