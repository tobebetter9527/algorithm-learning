package com.freedom.sword_offer;

public class Offer42 {

  public int maxSubArray(int[] nums) {
    Integer max = Integer.MIN_VALUE;
    int sum = 0;
    for (int num : nums) {
      sum += num;
      if (sum > max) {
        max = sum;
      }
      if (sum < 0) {
        sum = 0;
      }
    }
    return max;
  }

  public static int maxSubArray2(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      max = Math.max(max, recursive(nums, i));
    }
    return max;
  }

  private static int recursive(int[] nums, int i) {
    if (i == 0) {
      return nums[i];
    }
    int recursive = recursive(nums, i - 1);
    if (recursive < 0) {
      return nums[i];
    } else {
      return recursive + nums[i];
    }
  }

  public static int maxSubArray3(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (dp[i - 1] < 0) {
        dp[i] = nums[i];
      } else {
        dp[i] = dp[i - 1] + nums[i];
      }
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  public static int maxSubArray4(int[] nums) {
    int max = nums[0];
    int dp = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (dp < 0) {
        dp = nums[i];
      } else {
        dp = nums[i] + dp;
      }
      max = Math.max(max, dp);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int i = maxSubArray2(nums);
    System.out.println(i);

  }
}
