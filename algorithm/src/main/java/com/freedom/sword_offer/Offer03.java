package com.freedom.sword_offer;

import java.util.HashSet;
import java.util.Set;

public class Offer03 {

  /**
   * time complexity is O(n), space complexity is O(n).
   * @param nums
   * @return
   */
  public int findRepeatNumber(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int num : nums) {
      if (set.contains(num)) {
        return num;
      } else {
        set.add(num);
      }
    }
    return -1;
  }

  public int findRepeatNumber1(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    for (int num : nums) {
      if (ans[num] > 0) {
        return num;
      } else {
        ans[num]++;
      }
    }
    return -1;
  }

}
