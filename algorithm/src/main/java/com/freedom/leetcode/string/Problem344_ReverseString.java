package com.freedom.leetcode.string;

/**
 * 344. Reverse String
 *
 * @author tobebetter9527
 * @create 2022/10/22 21:49
 */
public class Problem344_ReverseString {

  /**
   * time complexity is O(n), space complexity is O(1)
   *
   * @param s
   */
  public void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (right > left) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      left++;
      right--;
    }
  }

}
