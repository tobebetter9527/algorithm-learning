package com.freedom.leetcode.dp;

/**
 * 392. Is Subsequence
 *
 * @author tobebetter9527
 * @create 2022/12/10 20:11
 */
public class Problem392_IsSubsequence {

  public static boolean isSubsequence(String s, String t) {
    int n = s.length(), m = t.length();
    int i = 0, j = 0;
    while (i < n && j < m) {
      if (s.charAt(i) == t.charAt(j)) {
        i++;
      }
      j++;
    }
    return i == n;
  }

  public static boolean isSubsequence2(String s, String t) {
    return recursive(s, t, 0, 0);
  }

  private static boolean recursive(String s, String t, int i, int j) {
    if (i == s.length()) {
      return true;
    }
    if (j >= t.length()) {
      return false;
    }
    if (s.charAt(i) == t.charAt(j)) {
      return recursive(s, t, i + 1, j + 1);
    } else {
      return recursive(s, t, i, j + 1);
    }
  }


  public static boolean isSubsequence3(String s, String t) {
    int n = s.length(), m = t.length();
    boolean[][] dp = new boolean[n + 1][m + 1];
    for (int col = 0; col <= m; col++) {
      dp[n][col] = true;
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m -1; j >= 0 ; j--) {
        dp[i][j] = s.charAt(i) == t.charAt(j) ? dp[i + 1][j + 1] : dp[i][j + 1];
      }
    }

    return dp[0][0];
  }


  public static void main(String[] args) {
    String s = "abc";
    String t = "ahbgdc";
    System.out.println(isSubsequence3(s, t));
  }

}
