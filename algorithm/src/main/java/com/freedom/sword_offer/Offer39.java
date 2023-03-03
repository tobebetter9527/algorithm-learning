package com.freedom.sword_offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Offer39 {

  /**
   * time complexity is O(n),space complexity is O(n)
   *
   * @param nums
   * @return
   */
  public int majorityElement(int[] nums) {
    int len = nums.length;
    int half = len >> 1;
    Map<Integer, Integer> map = new HashMap<>(len);
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > half) {
        return entry.getKey();
      }
    }
    return -1;
  }

  public int majorityElement2(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  /**
   * time complexity is O(n),space complexity is O(1)
   *
   * @param nums
   * @return
   */
  public int majorityElement3(int[] nums) {
    int candidate = 0;
    int count = 0;
    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }
      count += (num == candidate ? 1 : -1);
    }
    return candidate;
  }


}