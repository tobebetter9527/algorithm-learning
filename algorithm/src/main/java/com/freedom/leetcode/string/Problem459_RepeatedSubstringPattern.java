package com.freedom.leetcode.string;

/**
 * 459. Repeated Substring Pattern
 *
 * @author tobebetter9527
 * @create 2022/10/31 20:53
 */
public class Problem459_RepeatedSubstringPattern {

  /**
   * time complexity is O(n^2), space complexity is O(1).
   * <p>
   * 暴力法
   *
   * @param s
   * @return
   */
  public static boolean repeatedSubstringPattern(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }

    int n = s.length();
    char[] chars = s.toCharArray();
    int halfN = n / 2;
    loop1:
    for (int i = 0; i < halfN; i++) {
      // 重复子串的起点都是0索引位置
      int subStrLength = i - 0 + 1;
      // 如果不能等分n，则直接跳过
      if (subStrLength > 1 && n % subStrLength != 0) {
        continue;
      }
      boolean flag = true;
      loop2:
      for (int j = 0; j < n; j += subStrLength) {
        for (int k = 0; k < subStrLength; k++) {
          if (chars[k] != chars[j + k]) {
            flag = false;
            break loop2;
          }
        }
      }
      if (flag) {
        return true;
      }
    }

    return false;
  }

  /**
   * time complexity is O(n), space comlexity is n(n).
   * @param s
   * @return
   */
  public static boolean repeatedSubstringPattern2(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }

    int n = s.length();
    int[] nexts = getNexts(s.toCharArray(), n);
    return nexts[n - 1] != -1 && (n % (n - nexts[n - 1] - 1) == 0);
  }

  private static int[] getNexts(char[] chars, int n) {
    int[] nexts = new int[n];
    nexts[0] = -1;
    int k = -1;
    for (int i = 1; i < n; i++) {
      while (k != -1 && chars[k + 1] != chars[i]) {
        k = nexts[k];
      }

      if (chars[k + 1] == chars[i]) {
        k++;
      }
      nexts[i] = k;
    }
    return nexts;
  }


  public static void main(String[] args) {
    String s = "acdacdacd";
    boolean b = repeatedSubstringPattern2(s);
    System.out.println(b);
  }
}
