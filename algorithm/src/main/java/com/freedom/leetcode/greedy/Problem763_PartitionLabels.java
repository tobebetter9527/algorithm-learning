package com.freedom.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 *
 * @author tobebetter9527
 * @create 2022/11/28 20:18
 */
public class Problem763_PartitionLabels {

  /**
   * time complexity is O(n), space complexity is O(∣Σ∣),其中 Σ\SigmaΣ 是字符串中的字符集
   *
   * @param s
   * @return
   */
  public List<Integer> partitionLabels(String s) {
    // 保存字母在最右边的索引
    int[] nums = new int[26];
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      nums[chars[i] - 'a'] = i;
    }

    List<Integer> list = new ArrayList<>();
    int left = 0;
    int right = 0;
    for (int i = 0; i < chars.length; i++) {
      right = Math.max(right, nums[chars[i] - 'a']);
      if (right == i) {
        list.add(right - left + 1);
        left = right + 1;
      }
    }

    return list;
  }

}
