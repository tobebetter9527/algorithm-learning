package com.freedom.leetcode.greedy;

import java.util.Arrays;

/**
 * 455. Assign Cookies
 *
 * @author tobebetter9527
 * @create 2022/11/19 19:59
 */
public class Problem455_AssignCookies {

  /**
   * time complexity is O(nlogn) + O(nlogn),space complexity is O(logn) + O(logm).
   *
   * @param g
   * @param s
   * @return
   */
  public int findContentChildren(int[] g, int[] s) {
    if (g == null || s == null) {
      return 0;
    }
    Arrays.sort(g);
    Arrays.sort(s);
    int count = 0;
    int sIndex = 0;
    for (int i = 0; i < g.length; i++) {
      if (sIndex >= s.length) {
        break;
      }
      while (sIndex < s.length && s[sIndex] < g[i]) {
        sIndex++;
      }
      if (sIndex < s.length && s[sIndex] >= g[i]) {
        count++;
        sIndex++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int testTimes = 1000000;
    int maxLength = 3 * 10 * 10 * 10 * 10;
    int maxValue = Integer.MAX_VALUE;
    for (int i = 0; i < testTimes; i++) {
      int[] g = generateArr(maxLength, maxValue, false);
      int[] s = generateArr(maxLength, maxValue, true);
      Problem455_AssignCookies problem = new Problem455_AssignCookies();
      int contentChildren = problem.findContentChildren(g, s);
      System.out.println(contentChildren);
    }
  }

  private static int[] generateArr(int maxLength, int maxValue, boolean isEmpty) {
    int len = (int) (Math.random() * maxLength) + 1;
    len = isEmpty ? len : len + 1;
    int[] g = new int[len];
    for (int i = 0; i < len; i++) {
      g[i] = generateValue(maxValue);
    }
    return g;
  }

  private static int generateValue(int maxValue) {
    return (int) (Math.random() * maxValue);
  }

}
