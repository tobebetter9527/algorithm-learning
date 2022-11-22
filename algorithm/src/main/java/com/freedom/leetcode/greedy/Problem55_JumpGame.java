package com.freedom.leetcode.greedy;

/**
 * 55. Jump Game
 *
 * @author tobebetter9527
 * @create 2022/11/22 21:37
 */
public class Problem55_JumpGame {

  /**
   * time complexity is O(n), space complexity is O(1).
   *
   * @param nums
   * @return
   */
  public boolean canJump(int[] nums) {
    if (nums.length < 2) {
      return true;
    }
    int preIndex = 0;
    int n = nums.length;
    int lessN = n - 1;
    for (int i = 1; i < n; i++) {
      int diff = nums[preIndex] - (i - preIndex);
      if (diff >= 0 && diff <= nums[i]) {
        preIndex = i;
      }

      if (preIndex + nums[preIndex] >= lessN) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 0, 4};
    Problem55_JumpGame problem = new Problem55_JumpGame();
    boolean b = problem.canJump(nums);
    System.out.println(b);
  }
}
