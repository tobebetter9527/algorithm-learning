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
}
