package com.freedom.sword_offer;

public class Offer50 {

  /**
   * time complexity is O(n), space complexity is O(k),k字符集的大小
   *
   * @param s
   * @return
   */
  public char firstUniqChar(String s) {
    if (s.length() == 0) {
      return ' ';
    }
    int[] res = new int[26];
    int length = s.length();
    for (int i = 0; i < length; i++) {
      res[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < length; i++) {
      if (res[s.charAt(i) - 'a'] == 1) {
        return s.charAt(i);
      }
    }
    return ' ';
  }

}

