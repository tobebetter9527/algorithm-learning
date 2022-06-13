package com.freedom.leetcode.sort;

/**
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower,
 * upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 * @author tobebetter9527
 * @create 2022/06/13 22:13
 */
public class Problem327_CountOfRangeSum {


  public int countRangeSum(int[] nums, int lower, int upper) {

    return -1;

  }

  /**
   * 暴力迭代算个数
   *
   * @param nums
   * @param lower
   * @param upper
   * @return
   */
  public int testCountRangeSum(int[] nums, int lower, int upper) {
    if (nums == null) {
      return 0;
    }
    int n = nums.length;

    long[] sums = new long[n];
    sums[0] = nums[0];
    for (int i = 1; i < n; i++) {
      sums[i] = sums[i - 1] + nums[i];
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        long value = sums[j];
      }
    }



    return -1;

  }


}
