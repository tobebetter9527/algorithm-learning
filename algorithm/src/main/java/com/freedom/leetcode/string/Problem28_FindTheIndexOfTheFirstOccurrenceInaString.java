package com.freedom.leetcode.string;

/**
 * 28. Find the Index of the First Occurrence in a String
 *
 * @author tobebetter9527
 * @create 2022/10/23 17:37
 */
public class Problem28_FindTheIndexOfTheFirstOccurrenceInaString {

  /**
   * 暴力法
   * <p>
   * time complexity is O(n*m), space complexity is O(1).l
   *
   * @param haystack length >= 1
   * @param needle   length >= 1
   * @return
   */
  public static int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }
    char[] arrN = haystack.toCharArray();
    char[] arrM = needle.toCharArray();
    int n = haystack.length(), m = needle.length();
    for (int i = 0; i < n; i++) {
      int index = i;
      boolean flag = true;
      for (int j = 0; j < m; j++) {
        if (arrN[index++] != arrM[j]) {
          flag = false;
          break;
        }
      }
      if (flag) {
        return i;
      }
    }
    return -1;
  }





  public static void main(String[] args) {
    String haystack = "leetcode";
    String needle = "leeto";
    System.out.println(strStr(haystack, needle));
  }

}
