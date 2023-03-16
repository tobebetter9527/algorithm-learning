package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/03 20:15
 */
public class Offer53II {

  public static int missingNumber(int[] nums) {
    int n = nums.length + 1, low = 0, high = n - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > mid) {
        high = nums[mid] - 1;
      } else if (nums[mid] < mid) {
        // 实际没有这种情况
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  public static void main(String[] args) {
    int[] nums = {0};
    System.out.println(missingNumber(nums));
  }
}
