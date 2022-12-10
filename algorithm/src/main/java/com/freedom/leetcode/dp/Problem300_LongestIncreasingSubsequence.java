package com.freedom.leetcode.dp;

/**
 * 300. Longest Increasing Subsequence
 *
 * @author tobebetter9527
 * @create 2022/12/10 10:10
 */
public class Problem300_LongestIncreasingSubsequence {

  public static int lengthOfLIS(int[] nums) {
    int[] arr = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      arr[i + 1] = nums[i];
    }
    return recursive(arr, 1, 0);
  }

  private static int recursive(int[] nums, int index, int preIndex) {
    if (index == nums.length) {
      return 0;
    }
    // pick up
    int p1 = 0;
    if (nums[index] > nums[preIndex]) {
      p1 = 1 + recursive(nums, index + 1, index);
    }

    // not pick up
    int p2 = recursive(nums, index + 1, preIndex);
    return Math.max(p1, p2);
  }

  public static int lengthOfLIS2(int[] nums) {
    int n = nums.length + 1;
    int[] arr = new int[n];
    arr[0] = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      arr[i + 1] = nums[i];
    }

    int[][] dp = new int[n + 1][n + 1];
    // 从下到上，从左到右
    for (int index = n - 1; index >= 1; index--) {
      for (int preIndex = 0; preIndex <= index; preIndex++) {
        // pick up
        int p1 = 0;
        if (arr[index] > arr[preIndex]) {
          p1 = 1 + dp[index + 1][index];
        }

        // not pick up
        int p2 = dp[index + 1][preIndex];
        dp[index][preIndex] = Math.max(p1, p2);
      }
    }
    return dp[1][0];
  }


  public static void main(String[] args) {
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lengthOfLIS2(nums));
  }
}
