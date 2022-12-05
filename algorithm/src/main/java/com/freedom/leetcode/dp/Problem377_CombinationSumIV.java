package com.freedom.leetcode.dp;

/**
 * 377. Combination Sum IV
 *
 * @author tobebetter9527
 * @create 2022/12/05 21:32
 */
public class Problem377_CombinationSumIV {

  public static int combinationSum4(int[] nums, int target) {
    return recursive(nums, target);
  }

  private static int recursive(int[] nums, int restTarget) {
    if (restTarget == 0) {
      return 1;
    }
    int ways = 0;
    for (int i = 0; i < nums.length; i++) {
      if (restTarget >= nums[i]) {
        ways += recursive(nums, restTarget - nums[i]);
      }
    }
    return ways;
  }

  /**
   * time complexity is O(n * target), space complexity is O(target)
   *
   * @param nums
   * @param target
   * @return
   */
  public static int combinationSum5(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int restTarget = 1; restTarget <= target; restTarget++) {
      int ways = 0;
      for (int i = 0; i < nums.length; i++) {
        if (restTarget >= nums[i]) {
          ways += dp[restTarget - nums[i]];
        }
      }
      dp[restTarget] = ways;
    }
    return dp[target];
  }


  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    int target = 4;
    System.out.println(combinationSum5(nums, target));
  }
}
