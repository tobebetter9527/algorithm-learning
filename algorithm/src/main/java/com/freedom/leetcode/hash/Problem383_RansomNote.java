package com.freedom.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Ransom Note
 *
 * @author tobebetter9527
 * @create 2022/10/20 21:13
 */
public class Problem383_RansomNote {

  /**
   * time complexity is O(n), space complexity is O(n).
   *
   * @param ransomNote
   * @param magazine
   */
  public boolean canConstruct(String ransomNote, String magazine) {
    Map<Character, Integer> map = new HashMap<>(magazine.length());
    for (char c : magazine.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (Character c : ransomNote.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) - 1);
      if (map.get(c) < 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * Constraints:
   * <p>
   * 1 <= ransomNote.length, magazine.length <= 105
   * <p>
   * ransomNote and magazine consist of lowercase English letters.
   * <p>
   * time complexity is O(n), space complexity is O(1).
   *
   * @param ransomNote
   * @param magazine
   */
  public boolean canConstruct2(String ransomNote, String magazine) {
    int[] nums = new int[26];
    for (char c : magazine.toCharArray()) {
      nums[c - 'a']++;
    }

    for (char c : ransomNote.toCharArray()) {
      if (--nums[c - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

}
