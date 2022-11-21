package com.freedom.leetcode.greedy;

/**
 * 376. Wiggle Subsequence
 *
 * @author tobebetter9527
 * @create 2022/11/21 21:33
 */
public class Problem376_WiggleSubsequence {

  /**
   * time complexity is O(n), space complexity is O(1)
   *
   * @param nums
   * @return
   */
  public int wiggleMaxLength(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }

    int preDiff = 0;
    int curDiff = 0;
    int count = 0;
    for (int i = 1; i < nums.length; i++) {
      curDiff = nums[i] - nums[i - 1];
      if ((preDiff <= 0 && curDiff > 0) || (preDiff > 0 && curDiff <= 0)) {
        count++;
        preDiff = curDiff;
      }
    }

    return count;
  }

}
