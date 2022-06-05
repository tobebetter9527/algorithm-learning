package com.freedom.zuo.class01_sort_binary_search;

import java.util.Arrays;

/**
 * 二分查找
 *
 * @author tobebetter9527
 * @create 2022/06/04 14:34
 */
public class Code4_BinarySearch {

  public static int binarySearch2(int[] arr, int num) {
    return binarySearchInternally(arr, num, 0, arr.length - 1);
  }

  private static int binarySearchInternally(int[] arr, int num, int low, int high) {
    if (low > high) {
      return -1;
    }
    int mid = low + ((high - low) >> 1);
    if (arr[mid] == num) {
      return mid;
    } else if (arr[mid] > num) {
      return binarySearchInternally(arr, num, low, mid - 1);
    } else {
      return binarySearchInternally(arr, num, mid + 1, high);
    }
  }


  public static int binarySearch(int[] arr, int num) {
    int high = arr.length - 1;
    int low = 0;
    while (low <= high) {
      int mid = low + ((high - low) >> 1);
      if (arr[mid] > num) {
        high = mid - 1;
      } else if (arr[mid] < num) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int test(int[] arr, int num) {
    if (arr == null) {
      return -1;
    }
    for (int i = 0; i < arr.length; i++) {
      if (num == arr[i]) {
        return i;
      }
    }
    return -1;
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
    return (int) ((Math.random() * range) - (int) (Math.random() * range));
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
      Arrays.sort(arr);
      int num = generateRandomNum(range);

      int index1 = binarySearch(arr, num);
      int index2 = test(arr, num);

      if (index1 != index2) {
        flag = true;
        System.out.println("selection sort error");
        print(arr);
        System.out.println("num=" + num + ", index1=" + index1 + " , index2=" + index2);
      }
    }
    System.out.println(flag ? "bad" : "good");
  }

}
