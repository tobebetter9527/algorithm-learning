package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Offer48 {

  /**
   * sliding window, time complexity is O(n), space complexity is 字符集长度
   *
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring(String s) {
    int res = 0, length = s.length();
    Set<Character> set = new HashSet<>();
    for (int left = 0, right = 0; left < length; left++) {
      while (right < length && !set.contains(s.charAt(right))) {
        set.add(s.charAt(right));
        right++;
      }
      res = Math.max(res, right - left);
      set.remove(s.charAt(left));
    }
    return res;
  }

  public static int lengthOfLongestSubstring2(String s) {
    int res = 0, length = s.length();
    Map<Character, Integer> map = new HashMap<>();
    for (int left = -1, right = 0; right < length; right++) {
      if (map.containsKey(s.charAt(right))) {
        left = Math.max(left, map.get(s.charAt(right)));
      }
      res = Math.max(res, right - left);
      map.put(s.charAt(right), right);
    }
    return res;
  }

  public static void main(String[] args) {
    String s = "pwwkew";
    System.out.println(lengthOfLongestSubstring2(s));
  }

}
