package com.freedom.leetcode.unoin_find;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 *
 * @author tobebetter9527
 * @create 2022/12/20 20:15
 */
public class Problem128_LongestConsecutiveSequence {

  /**
   * time complexity is O(n),space complexity is O(n）
   *
   * @param nums
   * @return
   */
  public static int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    Set<Integer> set = new HashSet<>(nums.length);
    for (int num : nums) {
      set.add(num);
    }

    int longest = 0;
    for (Integer num : set) {
      // 没有比它小的，否则不计算
      if (!set.contains(num - 1)) {
        int ans = 1;
        int curNum = num;
        while (set.contains(curNum + 1)) {
          ans += 1;
          curNum += 1;
        }
        longest = Math.max(longest, ans);
      }
    }
    return longest;
  }


  public static void main(String[] args) {
    int[] nums = {100, 4, 200, 1, 3, 2};
    System.out.println(longestConsecutive(nums));
  }
}
