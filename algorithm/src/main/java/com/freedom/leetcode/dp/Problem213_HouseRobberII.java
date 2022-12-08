package com.freedom.leetcode.dp;

/**
 * 213. House Robber II
 *
 * @author tobebetter9527
 * @create 2022/12/07 21:54
 */
public class Problem213_HouseRobberII {

  public static int rob(int[] nums) {
    if (nums.length < 2) {
      return nums[0];
    }
    // 考虑首位，不考虑末位; 不考虑首位，考虑末位
    return Math.max(recursive(0, nums.length - 1, nums), recursive(1, nums.length, nums));
  }

  private static int recursive(int index, int end, int[] nums) {
    if (index >= end) {
      return 0;
    }
    // 当前不取值
    int p1 = recursive(index + 1, end, nums);
    // 当前取值
    int p2 = nums[index] + recursive(index + 2, end, nums);

    return Math.max(p1, p2);
  }


  public static int rob2(int[] nums) {
    if (nums.length < 2) {
      return nums[0];
    }

    int end = nums.length;
    // 考虑首位，不考虑末位
    return Math.max(dp(nums, 0, end - 1), dp(nums, 1, end));
  }

  private static int dp(int[] nums, int start, int end) {
    int[] dp = new int[end + 2];
    dp[end] = 0;
    dp[end + 1] = 0;

    for (int index = end - 1; index >= start; index--) {
      // 当前不取值
      int p1 = dp[index + 1];
      // 当前取值
      int p2 = nums[index] + dp[index + 2];

      dp[index] = Math.max(p1, p2);
    }
    return dp[start];
  }


  public static void main(String[] args) {
    int[] nums = {1,2,3,1};
    System.out.println(rob2(nums));

  }

}
