package com.freedom.leetcode.array;

import java.util.Arrays;

/**
 * Squares of a Sorted Array
 *
 * @author tobebetter9527
 * @create 2022/10/12 20:25
 */
public class Problem977_SquaresOfaSortedArray {


  public static int[] right(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }

    int[] arr = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      arr[i] = nums[i] * nums[i];
    }
    Arrays.sort(arr);

    return arr;
  }

  /**
   * time complexity is O(n), space complexity is O(n)
   */
  public static int[] sortedSquares(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }

    int[] arr = new int[nums.length];
    int left = 0;
    int right = nums.length - 1;
    int index = nums.length - 1;
    while (left <= right) {
      if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
        arr[index--] = nums[left] * nums[left];
        left++;
      } else {
        arr[index--] = nums[right] * nums[right];
        right--;
      }
    }

    return arr;
  }

  public static void main(String[] args) {
    int testTimes = 100000;
    int maxLength = 20;
    int maxValue = 20;
    for (int i = 0; i < testTimes; i++) {
      int[] nums = generateNums(maxLength, maxValue);
      Arrays.sort(nums);

      int[] rights = right(nums);
      int[] arr = sortedSquares(nums);
      if (rights != null && arr != null && rights.length == arr.length) {
        for (int j = 0; j < arr.length; j++) {
          if (rights[j] != arr[j]) {
            System.out.println("wrong");
            printArray(nums);
            break;
          }
        }
      } else {
        System.out.println("wrong");
        printArray(nums);
      }
    }
    System.out.println("Done!");
  }

  private static int[] generateNums(int maxLength, int maxValue1) {
    int length = generateValue(maxLength);
    int[] nums = new int[length];
    for (int i = 0; i < length; i++) {
      nums[i] = generateValue(maxValue1) - generateValue(maxValue1);
    }
    return nums;
  }


  private static int generateValue(int maxValue2) {
    return (int) (Math.random() * (maxValue2 + 1));
  }

  private static void printArray(int[] nums) {
    if (nums != null) {
      for (int num : nums) {
        System.out.print(num + " ");
      }

    }
  }
}
