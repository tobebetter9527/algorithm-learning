package com.freedom.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
 * target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 *
 * @author tobebetter9527
 * @create 2022/06/11 15:04
 */
public class Problem1_TwoSum {

  /**
   * 时间复杂度：O(N^2),空间复杂度：O(1)
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int num1 = nums[i];
      for (int j = i + 1; j < n; j++) {
        if ((num1 + nums[j]) == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

  public int[] twoSum2(int[] nums, int target) {
    int n = nums.length;
    Map<Integer, Integer> map = new HashMap<>(n);
    for (int i = 0; i < n; i++) {
      int num = nums[i];
      if (map.containsKey(target - num)) {
        return new int[]{map.get(target - num), i};
      }
      map.put(num, i);
    }
    return null;
  }
}
