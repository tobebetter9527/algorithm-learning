package com.freedom.leetcode.array;

/**
 * Binary Search.
 *
 * @author tobebetter9527
 * @create 2022/10/10 21:13
 */
public class Problem704_BinarySearch {

  public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    int mid;
    while (low <= high) {
      mid = low + ((high - low) >> 1);
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

}
