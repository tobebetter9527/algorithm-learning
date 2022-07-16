package com.freedom.zuo.class19_dynamic_programming2;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 *
 * @author tobebetter9527
 * @create 2022/07/16 21:00
 */
public class Code04_LongestCommonSubsequence {

  /**
   * 暴力递归
   *
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
      return 0;
    }
    return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
  }

  private int process(char[] text1, char[] text2, int i, int j) {
    if (i == 0 && j == 0) {
      return text1[i] == text2[j] ? 1 : 0;
    } else if (i == 0) {
      if (text1[i] == text2[j]) {
        return 1;
      } else {
        return process(text1, text2, i, j - 1);
      }
    } else if (j == 0) {
      if (text1[i] == text2[j]) {
        return 1;
      } else {
        return process(text1, text2, i - 1, j);
      }
    } else {
      int p1 = process(text1, text2, i - 1, j);
      int p2 = process(text1, text2, i, j - 1);
      int p3 = text1[i] == text2[j] ? (1 + process(text1, text2, i - 1, j - 1)) : 0;
      return Math.max(p1, Math.max(p2, p3));
    }
  }

  // -------------------------------------//

  public int longestCommonSubsequence2(String text1, String text2) {
    if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
      return 0;
    }
    int m = text1.length();
    int n = text2.length();
    char[] chars1 = text1.toCharArray();
    char[] chars2 = text2.toCharArray();
    int[][] dp = new int[m][n];

    dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;

    for (int i = 1; i < n; i++) {
      dp[0][i] = chars1[0] == chars2[i] ? 1 : dp[0][i-1];
    }

    for (int i = 1; i < m; i++) {
      dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i-1][0];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        int p1 = dp[i - 1][j];
        int p2 = dp[i][j - 1];
        int p3 = chars1[i] == chars2[j] ? (1 + dp[i - 1][j - 1]) : 0;
        dp[i][j] = Math.max(p1, Math.max(p2, p3));
      }
    }

    return dp[m - 1][n - 1];
  }

}
