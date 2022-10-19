package com.freedom.leetcode.hash;

import java.util.Arrays;
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
    if (s.length() != t.length()) {
      return false;
    }

    int[] arr = new int[26];

    for (char c : s.toCharArray()) {
      arr[c - 'a']++;
    }

    for (char c : t.toCharArray()) {
      if (--arr[c - 'a'] < 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * time complexity is O(nlogn) + O(n) , 总体是O(nlogn)。
   *
   * @param s
   * @param t
   * @return
   */
  public static boolean isAnagram3(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    char[] chars1 = s.toCharArray();
    char[] chars2 = t.toCharArray();
    Arrays.sort(chars1);
    Arrays.sort(chars2);
    return Arrays.equals(chars1, chars2);
  }

  /**
   * time complexity is O(n), space complexity is O(m), m is length of s string.
   * <p>
   * Constraints:
   * <p>
   * 1 <= s.length, t.length <= 5 * 104
   * <p>
   * s and t consist of lowercase English letters.
   */
  public static boolean isAnagram2(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) - 1);
      if (map.get(c) < 0) {
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
