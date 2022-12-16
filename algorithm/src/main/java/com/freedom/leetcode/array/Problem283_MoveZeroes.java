package com.freedom.leetcode.array;

/**
 * 283. Move Zeroes
 *
 * @author tobebetter9527
 * @create 2022/12/16 21:31
 */
public class Problem283_MoveZeroes {

  /**
   * time complexity is O(n^2), space complexity is O(1).
   *
   * @param nums
   */
  public static void moveZeroes(int[] nums) {
    int n = nums.length;
    int index = n - 1;
    for (int i = n - 1; i >= 0; i--) {
      if (nums[i] == 0) {
        swapToIndex(nums, i, index--);
      }
    }
  }

  private static void swapToIndex(int[] nums, int i, int index) {
    while (i + 1 <= index) {
      int temp = nums[i];
      nums[i] = nums[i + 1];
      nums[i + 1] = temp;
      i++;
    }
  }

  /**
   * time complexity is O(n), space complexity is O(1)
   *
   * @param nums
   */
  public static void moveZeroes2(int[] nums) {
    int n = nums.length;
    int index = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] != 0) {
        nums[index++] = nums[i];
      }
    }

    for (int i = index; i < n; i++) {
      nums[i] = 0;
    }
  }

  /**
   * 双指针
   *
   * @param nums
   */
  public static void moveZeroes3(int[] nums) {
    int n = nums.length, left = 0, right = 0;
    while (right < n) {
      if (nums[right] != 0) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;

        left++;
      }
      right++;
    }
  }


  public static void main(String[] args) {
    int[] nums = {0,1,0,3,12};
    moveZeroes3(nums);
    for (int num : nums) {
      System.out.println(num);
    }
  }
}
