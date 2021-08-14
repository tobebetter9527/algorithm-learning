package com.freedom.algorithm.search;

/**
 * 二分法
 *
 * @author tobebetter9527
 * @create 2021/08/14 10:06
 */
public class BinarySearch {

  public static boolean isExist(int[] sortedArr, int target) {
    if (sortedArr == null || sortedArr.length == 0) {
      return false;
    }
    int left = 0;
    int right = sortedArr.length - 1;
    int mid;
    while (left <= right) {
      mid = left + ((right - left) >> 1);
      if (sortedArr[mid] == target) {
        return true;
      } else if (sortedArr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 3, 5, 8, 10, 90};
    System.out.println(isExist(arr, 10));


  }

}
