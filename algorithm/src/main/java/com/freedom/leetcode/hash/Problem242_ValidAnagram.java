package com.freedom.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram
 *
 * @author tobebetter9527
 * @create 2022/10/19 21:30
 */
public class Problem242_ValidAnagram {

  /**
   * time complexity is O(n), space complexity is O(1).
   *
   * @param s
   * @param t
   */
  public static boolean isAnagram(String s, String t) {
    int[] arr = new int[26];

    for (char c : s.toCharArray()) {
      int index = c - 'a';
      arr[index]++;
    }

    for (char c : t.toCharArray()) {
      int index = c - 'a';
      arr[index]--;
    }

    for (int num : arr) {
      if (num != 0) {
        return false;
      }
    }

    return true;
  }


  /**
   * Constraints:
   * <p>
   * 1 <= s.length, t.length <= 5 * 104
   * <p>
   * s and t consist of lowercase English letters.
   */
  public static boolean isAnagram2(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) - 1);
    }

    for (Integer value : map.values()) {
      if (value != 0) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    boolean anagram = isAnagram("anagram", "nagaram");
    System.out.println(anagram);
  }

}
