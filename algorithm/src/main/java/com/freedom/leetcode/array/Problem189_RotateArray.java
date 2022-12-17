package com.freedom.leetcode.array;

/**
 * 189. Rotate Array
 *
 * @author tobebetter9527
 * @create 2022/12/16 23:07
 */
public class Problem189_RotateArray {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param nums
   * @param k
   */
  public static void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    if (k == 0) {
      return;
    }

    int[] copy = new int[n];
    int dividePoint = n - 1 - (k - 1);
    for (int i = 0; i < k; i++) {
      copy[i] = nums[i + dividePoint];
    }
    for (int i = k; i < n; i++) {
      copy[i] = nums[i - k];
    }

    for (int i = 0; i < n; i++) {
      nums[i] = copy[i];
    }
  }

  public static void rotate2(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    rotateArr(nums, 0, n - 1);
    rotateArr(nums, 0, k - 1);
    rotateArr(nums, k, n - 1);
  }

  private static void rotateArr(int[] nums, int left, int right) {
    while (left < right) {
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;
      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    int[] nums = {1,2,3,4,5,6};
    int k = 2;
    rotate2(nums, k);
    for (int num : nums) {
      System.out.println(num);
    }
  }
}
