package com.freedom.leetcode.array;

/**
 * 941. Valid Mountain Array
 *
 * @author tobebetter9527
 * @create 2022/12/16 20:09
 */
public class Problem941_ValidMountainArray {


  public static boolean validMountainArray(int[] arr) {
    int n = arr.length;
    if (n < 3) {
      return false;
    }
    boolean flag = false;
    int index = 0;
    while (index < n - 2 && arr[index] < arr[index + 1]) {
      flag = true;
      index++;
    }

    while (index < n - 1 && arr[index] > arr[index + 1]) {
      index++;
    }

    return flag && index == n - 1;
  }

  public static void main(String[] args) {
    int[] arr = {9,8,7,6,5,4,3,2,1,0};
    System.out.println(validMountainArray(arr));
  }
}
