package com.freedom.zuo.class01_sort_binary_search;

import java.util.Arrays;

/**
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order)
 * from unsorted part and putting it at the beginning. The algorithm maintains two subarrays in a given array.
 * <p>
 * The subarray which is already sorted. Remaining subarray which is unsorted.
 * <p>
 * In every iteration of selection sort, the minimum element (considering ascending order) from the unsorted subarray is
 * picked and moved to the sorted subarray.
 * <p>
 * 时间复杂度分析： 最好最坏都是O(n^2)
 *
 * @author tobebetter9527
 * @create 2022/06/04 14:34
 */
public class Code2_SelectionSort {

  public static void selectionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
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

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static int[] generateRandomArray(int maxSize, int range) {
    int length = (int) (Math.random() * (maxSize + 1));
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = generateRandomNum(range);

    }
    return arr;
  }

  private static int generateRandomNum(int range) {
    return (int) (Math.random() * range - Math.random() * range);
  }

  private static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] copy = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      copy[i] = arr[i];
    }
    return arr;
  }

  private static boolean isEqual(int[] arr1, int[] arr2) {
    if (arr1 == null && arr2 != null) {
      return false;
    }
    if (arr1 != null && arr2 == null) {
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

  private static void print(int[] arr) {
    if (arr == null) {
      System.out.println("arr is null");
    }
    System.out.println("--------");
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }


  public static void main(String[] args) {
    int size = 20, range = 100, testTimes = 1000000;
    boolean flag = false;
    for (int i = 0; i < testTimes; i++) {
      int[] arr = generateRandomArray(size, range);
      int[] copyArray = copyArray(arr);
      selectionSort(arr);
      Arrays.sort(copyArray);

      if (!isEqual(arr, copyArray)) {
        flag = true;
        System.out.println("selection sort error");
        print(arr);
        print(copyArray);
      }
    }
    System.out.println(flag ? "bad" : "good");
  }

}
