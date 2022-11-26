package com.freedom.leetcode.greedy;

/**
 * 135. Candy
 *
 * @author tobebetter9527
 * @create 2022/11/25 21:52
 */
public class Problem135_Candy {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param ratings
   * @return
   */
  public int candy(int[] ratings) {
    int len = ratings.length;
    int[] nums = new int[len];
    nums[0] = 1;
    // 保证i分到的糖果大于等于i-1分到的糖果,即当前大于等于左边的
    for (int i = 1; i < len; i++) {
      if (ratings[i] > ratings[i - 1]) {
        nums[i] = nums[i - 1] + 1;
      } else {
        nums[i] = 1;
      }
    }
    // 保证当前的要大于右边的,也要大于左边的
    for (int i = len - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) {
        nums[i] = Math.max(nums[i], nums[i + 1] + 1);
      }
    }

    int sum = 0;
    for (int i = 0; i < len; i++) {
      sum += nums[i];
    }

    return sum;
  }
}
