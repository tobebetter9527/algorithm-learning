package com.freedom.leetcode.dp;

/**
 * 198. House Robber
 *
 * @author tobebetter9527
 * @create 2022/12/07 21:15
 */
public class Problem198_HouseRobber {

  public static int rob(int[] nums) {
    return recursive(0, nums);
  }

  private static int recursive(int index, int[] nums) {
    if (index >= nums.length) {
      return 0;
    }
    // 当前不取值
    int p1 = recursive(index + 1, nums);
    // 当前取值
    int p2 = nums[index] + recursive(index + 2, nums);

    return Math.max(p1, p2);
  }

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param nums
   * @return
   */
  public static int rob2(int[] nums) {
    int n = nums.length;
    // 比上面的多一位
    int[] dp = new int[n + 2];
    dp[n] = 0;
    dp[n + 1] = 0;

    for (int index = n - 1; index >= 0; index--) {
      // 当前不取值
      int p1 = dp[index + 1];
      // 当前取值
      int p2 = nums[index] + dp[index + 2];
      dp[index] = Math.max(p1, p2);
    }
    return dp[0];
  }


  public static void main(String[] args) {
    int[] nums = {2, 7, 9, 3, 1};
    System.out.println(rob2(nums));

  }
}
